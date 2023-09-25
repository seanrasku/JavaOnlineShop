package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.OrderManagementService;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;

	private Order[] orders;
	private int orderCount;
	private DefaultOrderManagementService() {
		orders = new Order[DEFAULT_ORDER_CAPACITY];
		orderCount = 0;
	}
	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if(order != null && orderCount < DEFAULT_ORDER_CAPACITY) {
			Order[] tempOrders = orders;
			orders = new Order[orderCount + 1];
			System.arraycopy(tempOrders, 0, orders, 0, orderCount);
			orders[orderCount] = order;
			orderCount++;
		}
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		List<Integer> l = new ArrayList<>();
		for(int i = 0; i < orderCount; i++) {
			if(orders[i].getCustomerId() == userId) {
				l.add(i);
			}
		}
		Order[] filtered = new Order[l.size()];
		for(int i = 0; i < filtered.length; i++) {
			filtered[i] = orders[l.get(i)];
		}
		return filtered;
	}

	@Override
	public Order[] getOrders() {
		return orders;
	}
	
	void clearServiceState() {
		orders = new Order[DEFAULT_ORDER_CAPACITY];
		orderCount = 0;
	}

}
