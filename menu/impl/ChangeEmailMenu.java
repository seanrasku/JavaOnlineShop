package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities.Utility;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {
	private Scanner scan = new Scanner(System.in);
	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		User user = context.getLoggedInUser();
		System.out.println("Enter New Email");
		System.out.print(">>> ");
		String oldEmail = user.getEmail();
		String email;
		while ((email = scan.nextLine()).isEmpty() || Utility.isMalformedString(email)) {
			if(Utility.isMalformedString(email) && email.contains("@") && email.contains(".")) break; //in real life, many more checks
			System.out.println("Email must be valid");
			System.out.print(">>> ");
		}
		user.setEmail(email);
		String updateResponse = userManagementService.updateUser(user.getId(), oldEmail, email, userManagementService.EMAIL);
		if (updateResponse.isEmpty()) {
			System.out.println("User Email Updated Successfully!");
		} else {
			user.setEmail(oldEmail);
			System.out.println("Management Service could not find user, reverting changes...");
		}
		MainMenu.returnOrExit(scan);
		context.getMainMenu().start();
	}
	@Override
	public void printMenuHeader() {
		// <write your code here>
	}

}
