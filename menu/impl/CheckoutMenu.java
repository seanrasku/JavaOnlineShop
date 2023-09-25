package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl.DefaultOrder;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private Scanner scan = new Scanner(System.in);
	private OrderManagementService orderManagementService;
	
	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}
	
	@Override
	public void start() {
		Menu m;
		if(context.getLoggedInUser() == null) {
			System.out.println("Not logged in, returning to main menu...");
			context.getMainMenu().start();
		}
		else if(context.getSessionCart().getProducts().length == 0) {
			System.out.println("Empty cart, returning to Product Catalog screen...");
			m = new ProductCatalogMenu();
			m.start();
		}
		else {
			String cardString;
			Order newOrder = new DefaultOrder();
			printMenuHeader();
			while (!newOrder.isCreditCardNumberValid((cardString = scan.nextLine()))) {
				System.out.println("Card Number invalid, please try again");
				System.out.print(">>> ");
			}

			newOrder.setCreditCardNumber(cardString);
			newOrder.setProducts(context.getSessionCart().getProducts());
			newOrder.setCustomerId(context.getLoggedInUser().getId());
			orderManagementService.addOrder(newOrder);
			System.out.println("Order Created!\n returning to main menu...");
			context.getMainMenu().start();
		}


	}

	@Override
	public void printMenuHeader() {
		System.out.println("Checkout:");
		System.out.println("Enter Credit Card Number, no spaces");
		System.out.print(">>> ");

	}

}
