package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

import static java.lang.System.exit;

public class ChangePasswordMenu implements Menu {
	private UserManagementService userManagementService;
	private ApplicationContext context;
	private Scanner scan = new Scanner(System.in);
	
	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		User user = context.getLoggedInUser();
		String resp = "tempForFirstLoop";
		String old = "";
		String pass = "";
		while(!resp.isEmpty()) {
			System.out.println("Enter Old Password");
			System.out.print(">>> ");

			while ((old = scan.nextLine()).isEmpty()) {
				System.out.println("Old password cannot be empty");
				System.out.print(">>> ");
			}
			if(old.equals(MainMenu.MENU_COMMAND) || old.equals(Main.EXIT_COMMAND)) break;
			System.out.println("Enter New Password");
			System.out.print(">>> ");
			while ((pass = scan.nextLine()).isEmpty()) {
				System.out.println("New password cannot be empty");
				System.out.print(">>> ");
			}
			if(pass.equals(MainMenu.MENU_COMMAND) || pass.equals(Main.EXIT_COMMAND)) break;


			resp = user.changePassword(old, pass);
			if(!resp.isEmpty()) System.out.println("Incorrect password, try again");
		}
		if(old.equals(Main.EXIT_COMMAND) || pass.equals(Main.EXIT_COMMAND)) exit(0);
		else if(old.equals(MainMenu.MENU_COMMAND) || pass.equals(MainMenu.MENU_COMMAND)) {
			System.out.println("Returning to main menu...");
		}
		else {
			String updateResponse = userManagementService.updateUser(user.getId(), old, pass, userManagementService.PASSWORD);
			if (updateResponse.isEmpty()) {
				System.out.println("User Password Updated Successfully!");
			} else {
				user.changePassword(pass, old);
				System.out.println("Management Service could not find user, reverting changes...");
			}
		}
        MainMenu.returnOrExit(scan);
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		// <write your code here>		
	}

}
