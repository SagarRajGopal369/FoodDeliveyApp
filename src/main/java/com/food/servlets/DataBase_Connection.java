package com.food.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase_Connection {

	private static Connection connection;
	public DataBase_Connection() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_app","root","root");
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
	
	
}
