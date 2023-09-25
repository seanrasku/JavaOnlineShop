package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities.Utility;

import java.util.*;

import static java.lang.System.exit;

public class MainMenu implements Menu {
	public static final String MENU_COMMAND = "menu";
	public static final String RETURN_OR_EXIT = "Enter \"menu\" to return to main menu or \"exit\" to exit: \n>>> ";
	private String OPTIONS_OR_EXIT = "Select from below options, or enter \"exit\" to exit";

	public static final void returnOrExit(Scanner scan) {
		System.out.print(MainMenu.RETURN_OR_EXIT);
		String menu;
		while (!(menu = scan.nextLine()).equals("menu") && !menu.equals(Main.EXIT_COMMAND)) {
			System.out.print(MainMenu.RETURN_OR_EXIT);
		}
		if(menu.equals(Main.EXIT_COMMAND)) exit(0);
	}
	private Scanner scan = new Scanner(System.in);
	private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign In"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
			"6. Customer List" + System.lineSeparator() + ">>> ";

	private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
			+ "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
			+ System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
			+ "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
			"6. Customer List" + System.lineSeparator() + ">>> ";;

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Menu m = context.getMainMenu();
		if(m == null) {
			m = new MainMenu();
			context.setMainMenu(m);
		}
		String choice;
		if (context.getLoggedInUser() == null) {

			while(!(choice = scan.nextLine()).equals(Main.EXIT_COMMAND) && !Utility.isValidRange(choice, 1, 2)) {
				if(Utility.isStringANumber(choice) && Utility.isValidRange(choice, 3, 6)) System.out.println("You are not logged in, please log in to select this option");
				else System.out.println("Invalid command, try again");
				printMenuHeader();
			}
			if(choice.equals(Main.EXIT_COMMAND)) exit(0);

			int selection = Integer.parseInt(choice);
			Menu nextMenu;

			if(selection == 1) {
				nextMenu = new SignUpMenu();
			}
			else {
				nextMenu = new SignInMenu();
			}
			nextMenu.start();

		} else {
			while(!(choice = scan.nextLine()).equals(Main.EXIT_COMMAND) && !Utility.isValidRange(choice, 1, 6)) {
				System.out.println("Invalid command, try again");
				printMenuHeader();
			}
			if(choice.equals(Main.EXIT_COMMAND)) exit(0);
			int selection = Integer.parseInt(choice);

			Menu nextMenu;

			if(selection == 1) {
				nextMenu = new SignUpMenu();
			}
			else if(selection == 2) {
				nextMenu = new SignOutMenu();
			}
			else if(selection == 3) {
				nextMenu = new ProductCatalogMenu();
			}
			else if(selection == 4) {
				nextMenu = new MyOrdersMenu();
			}
			else if(selection == 5) {
				nextMenu = new SettingsMenu();
			}
			else nextMenu = new CustomerListMenu();
			nextMenu.start();


		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
        if (context.getLoggedInUser() == null) {
			System.out.print(OPTIONS_OR_EXIT + "\n" + MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
		} else {
			System.out.print(OPTIONS_OR_EXIT + "\n" + MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
		}
    }

}