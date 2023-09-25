package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;

import java.util.Scanner;

public class SignOutMenu implements Menu {

	private ApplicationContext context;
	private Scanner scan = new Scanner(System.in);
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		String confirm;
		while(!(confirm = scan.nextLine()).equals("yes") && !confirm.equals("no")) {
			System.out.println("\nPlease type yes or no");
			System.out.print(">>> ");
		}
		if(confirm.equals("yes")) {
			context.setLoggedInUser(null);
			Menu m = new MainMenu();
			context.setMainMenu(m);
			context.getMainMenu().start();
		}
		else {
			context.getMainMenu().start();
		}


	}

	@Override
	public void printMenuHeader() {
		System.out.println("Would you like to sign out? Type yes or no to confirm");
		System.out.print(">>> ");
	}

}
