package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class MyOrdersMenu implements Menu {
	private Scanner scan = new Scanner(System.in);
	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		if(context.getLoggedInUser() == null) {
			System.out.println("Not logged in, returning to main menu...");
			context.getMainMenu().start();
		}
		else {
			printMenuHeader();
			MainMenu.returnOrExit(scan);
			context.getMainMenu().start();
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("My Orders");
		Order[] orders = orderManagementService.getOrders();
		if(orders.length == 0) {
			System.out.println("No orders available, purchase something to see orders here");
		}
		for(Order o : orders) {
			if(o == null) break;
			System.out.println(o + ",");
		}
	}

}
