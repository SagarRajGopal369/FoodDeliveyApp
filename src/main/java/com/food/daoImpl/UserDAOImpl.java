package com.food.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.UserDAO;
import com.food.models.User;
import com.food.servlets.DataBase_Connection;

public class UserDAOImpl implements UserDAO {
	
	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static ResultSet resultSet;
	private static Statement statement;
	
	private static final String INSERT_QUERY = "INSERT INTO `user`(`Username`, `Password`, `Email`, `phone`, `Address`, `Role`) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `user` WHERE `UserID` = ? ";
	private static final String UPDATE_QUERY = "UPDATE `user` SET `Username` = ?, `Password` = ?, `Email` = ?, `phone` = ?, `Address` = ?, `Role` = ? WHERE `UserID` = ? ";
	private static final String DELETE_QUERY = "DELETE FROM `user` WHERE `UserID` = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM `user`";
	private static final String SELECT_BY_USERNAME ="SELECT * FROM User WHERE Username = ?";
	
	public UserDAOImpl() 
	{
		DataBase_Connection dataBase_Connection = new DataBase_Connection();
		connection  = dataBase_Connection.getConnection();
	}

	@Override
	public void addUser(User user)
	{
		 try 
		 {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setString(4, user.getPhoneNo());
			prepareStatement.setString(5, user.getAddress());
			prepareStatement.setString(6, user.getRole());
			
			 prepareStatement.executeUpdate();
		 } 
		 catch (SQLException e)
		 {
			e.printStackTrace();
		 }
	}
	
	
	
	
	@Override
	public User getUserByUserName(String username) {
	    try {
	        prepareStatement = connection.prepareStatement(SELECT_BY_USERNAME);
	        prepareStatement.setString(1, username);
	        resultSet = prepareStatement.executeQuery();

	        if (resultSet.next()) {
	            return mapResultSetToUser(resultSet);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	


	@Override
	public User getUser(int userId)
	{
		 try 
		 {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);
			
			prepareStatement.setInt(1, userId);
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next())
			{
				mapResultSetToUser(resultSet);
			}
			
		 } 
		 catch (SQLException e)
		 {
			e.printStackTrace();
		}
		 return null;
	}

	@Override
	public void updateUser(User user)
	{
		 try 
		 {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setString(4, user.getPhoneNo());
			prepareStatement.setString(5, user.getAddress());
			prepareStatement.setString(6, user.getRole());
			prepareStatement.setInt(7, user.getUserId());

			prepareStatement.executeUpdate();
		 } 
		 catch (SQLException e)
		 {
			e.printStackTrace();
		 }
	}

	@Override
	public void deleteUser(int UserId) 
	{
		 try 
		 {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, UserId);
			prepareStatement.executeUpdate();
		 } 
		 catch (SQLException e)
		 {
			e.printStackTrace();
		 }
	}

	@Override
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		 try
		 {
			statement = connection.createStatement();
			 resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			 
			 while(resultSet.next())
				{
				   users.add(mapResultSetToUser(resultSet));
				}
		 }
		 catch (SQLException e)
		 {
			e.printStackTrace();
		 }
		return users;
	}
	
	
	private User mapResultSetToUser(ResultSet resultSet) throws SQLException
	{
	    int userId = resultSet.getInt("UserID");
	    String username = resultSet.getString("Username");
	    String password = resultSet.getString("Password");
	    String email = resultSet.getString("Email");
	    String userPhone = resultSet.getString("phone");
	    String address = resultSet.getString("Address");
	    String role = resultSet.getString("Role");
	    
	    return new User(userId, username, password, email, userPhone, address, role);
	}


}
