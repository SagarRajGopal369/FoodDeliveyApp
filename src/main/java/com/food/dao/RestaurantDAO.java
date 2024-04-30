package com.food.dao;

import java.util.List;

import com.food.models.Restaurant;
import com.food.models.User;

public interface RestaurantDAO {

	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	void deleteMenu(int restaurantId);
	List<Restaurant> getAllRestaurants();
}
