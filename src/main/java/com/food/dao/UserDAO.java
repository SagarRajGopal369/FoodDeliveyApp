package com.food.dao;

import java.util.List;

import com.food.models.User;

public interface UserDAO {

	void addUser(User user);
	User getUserByUserName(String username);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
}
