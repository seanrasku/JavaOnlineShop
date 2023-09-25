package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private Scanner scan = new Scanner(System.in);
	private UserManagementService userManagementService;
	
	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		listCustomers();
		MainMenu.returnOrExit(scan);
		context.getMainMenu().start();

	}
	private void listCustomers() {
		User[] users = userManagementService.getUsers();
		if(users.length == 0) {
			System.out.println("No Customers Found");
			return;
		}
		for(User u : users) {
			if(u == null) break;
			System.out.println(u + ",");
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("Customer List:");
	}

}
