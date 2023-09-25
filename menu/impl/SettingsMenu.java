package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
			+ "2. Change Email";
	private Scanner scan = new Scanner(System.in);
	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		System.out.print(">>> ");
		String s;
		User user = context.getLoggedInUser();
		Menu m;
		if(user != null) {
			while (!(s = scan.nextLine()).equals("1") && !s.equals("2") && !s.equals(MainMenu.MENU_COMMAND) && !s.equals(Main.EXIT_COMMAND)) {
				System.out.println("Choice must be 1 or 2");
				System.out.print(">>> ");
			}
			if(s.equals(Main.EXIT_COMMAND)) exit(0);
			else if(s.equals(MainMenu.MENU_COMMAND)) {
				System.out.println("Going back to main menu...");
				context.getMainMenu().start();
			}
			else {
				int choice = Integer.parseInt(s);
				if (choice == 1) {
					m = new ChangePasswordMenu();
					m.start();
				} else {
					m = new ChangeEmailMenu();
					m.start();

				}
			}
		}
		else {
			System.out.println("Not logged in, going back to main menu...");
			context.getMainMenu().start();
		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("Settings");
		System.out.println(SETTINGS);


	}


}
