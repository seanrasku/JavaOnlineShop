package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.Main;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.ProductManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultProductManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities.Utility;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class ProductCatalogMenu implements Menu {
	private Scanner scan = new Scanner(System.in);
	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		String scanned;
		while(!(scanned = scan.nextLine()).equals(CHECKOUT_COMMAND)) {

			while(!Utility.isValidRange(scanned, 1, 10) && !scanned.equals(MainMenu.MENU_COMMAND) && !scanned.equals(Main.EXIT_COMMAND) && !scanned.equals(CHECKOUT_COMMAND)) {
				printOptions();
				scanned = scan.nextLine();
			}
			if(scanned.equals(MainMenu.MENU_COMMAND) || scanned.equals(Main.EXIT_COMMAND) || scanned.equals(CHECKOUT_COMMAND)) break;

			int id = Integer.parseInt(scanned);
			Product p = productManagementService.getProductById(id);
			if(p == null) {
				System.out.println("Please enter valid product ID if you want to add product to cart. Else, enter checkout if you want to proceed with checkout. Otherwise, enter menu if you want to navigate back to main menu.");
				System.out.print(">>> ");
			}
			else {
				context.getSessionCart().addProduct(p);
				System.out.println("Added product with id " + id + " to cart!");
			}
			printMenuHeader();
		}
		if(scanned.equals(Main.EXIT_COMMAND)) {
			exit(0);
		}
		Menu m;
		if(scanned.equals(CHECKOUT_COMMAND)) {
			m = new CheckoutMenu();
		}
		else {
			m = context.getMainMenu();
		}
		m.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("Product Catalog:");
		Product[] p = productManagementService.getProducts();
		for(Product pr: p) {
			System.out.println(pr.toString());
		}
		printOptions();


	}
	private void printOptions() {
		int cartLength = context.getSessionCart().getProducts().length;
		if (cartLength > 0) {
			System.out.print("Enter Product ID to add product to the cart, or enter \"checkout\" to checkout current cart. " + MainMenu.RETURN_OR_EXIT); //Right now, returning to main does not clear cart, but could update in the future to include functionality. Depends on context, and whether company would want cart to stay as is or not.
		} else {
			System.out.println("Please Enter a valid product ID. Your cart is currently empty, so no checkout is possible");
			System.out.print(">>> ");
		}
	}

}
