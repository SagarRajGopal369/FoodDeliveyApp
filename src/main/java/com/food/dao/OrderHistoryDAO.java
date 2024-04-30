package com.food.dao;

import java.util.List;

import com.food.models.OrderHistory;

public interface OrderHistoryDAO {

	void addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory>getOrderHistoryByUser(int userId);
}
