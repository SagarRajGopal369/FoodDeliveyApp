package com.food.dao;

import java.util.List;

import com.food.models.Menu;

public interface MenuDAO {

	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu> getAllMenusByRestaurant(int restaurantId);
}
