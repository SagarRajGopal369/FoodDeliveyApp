package com.food.dao;

import java.util.List;

import com.food.models.Order;

public interface OrderDAO {

	void addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getALLOrdersByUser(int userId);
}
