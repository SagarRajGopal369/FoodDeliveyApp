package com.food.dao;

import java.util.List;

import com.food.models.OrderItem;

public interface OrderItemDAO {

	void addOrderitem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getOrderItemsByOrder(int orderId);
}
