package com.food.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.models.Restaurant;
import com.food.servlets.DataBase_Connection;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static Connection connection ;
    private static PreparedStatement prepareStatement ;
    private static ResultSet resultSet ;
    private static Statement statement ;

    private static final String INSERT_QUERY = "INSERT INTO `restaurant`(`Name`, `Address`, `DeliveryTime`, `Rating`, `CuisineType`, `IsActive`, `AdminUserID`, `ImagePath`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `RestaurantID` = ?";
    private static final String UPDATE_QUERY = "UPDATE `restaurant` SET `Name` = ?, `Address` = ?,`DeliveryTime` = ?, `Rating` = ?, `CuisineType` = ?, `IsActive` = ?, `AdminUserID` = ?, `ImagePath` = ? WHERE `RestaurantID` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `RestaurantID` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `restaurant`";
    private static final String DeleTE_MENU_QUERY_BY_RESURANT= "DELETE FROM `menu` WHERE `RestaurantID` = ?";
    

	
	public RestaurantDAOImpl() 
	{
		DataBase_Connection dataBase_Connection = new DataBase_Connection();
		connection  = dataBase_Connection.getConnection();
	}

	@Override
	public void addRestaurant(Restaurant restaurant)
	{
		 try
		 {
			 prepareStatement = connection.prepareStatement(INSERT_QUERY);
			 
			 prepareStatement.setString(1, restaurant.getName());
			 prepareStatement.setString(2, restaurant.getAddress());
			 prepareStatement.setInt(3, restaurant.getDeliveryTime());
			 prepareStatement.setDouble(4, restaurant.getRating());
			 prepareStatement.setString(5, restaurant.getCuisineType());
			 prepareStatement.setString(6, restaurant.getIsActive());
			 prepareStatement.setInt(7, restaurant.getAdminUserId());
			 prepareStatement.setString(8, restaurant.getImagePath());
			 
			 prepareStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
	        e.printStackTrace();
		 }

	}

	@Override
	public Restaurant getRestaurant(int restaurantId)
	{
		 try
		 {
			 prepareStatement = connection.prepareStatement(SELECT_QUERY);
			 prepareStatement.setInt(1, restaurantId);
			 resultSet = prepareStatement.executeQuery();
			 
			 if(resultSet.next())
			 {
				 return mapResultSetToRestaurant(resultSet);
			 }
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		return null;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		 try
		 {
			 prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			 
			 prepareStatement.setString(1, restaurant.getName());
			 prepareStatement.setString(2, restaurant.getAddress());
			 prepareStatement.setInt(3, restaurant.getDeliveryTime());
			 prepareStatement.setDouble(4, restaurant.getRating());
			 prepareStatement.setString(5, restaurant.getCuisineType());
			 prepareStatement.setString(6, restaurant.getIsActive());
			 prepareStatement.setInt(7, restaurant.getAdminUserId());
			 prepareStatement.setString(8, restaurant.getImagePath());
			 prepareStatement.setInt(9, restaurant.getRestaurantId());
			 
			 prepareStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try
		 {
			 prepareStatement = connection.prepareStatement(DELETE_QUERY);
			 prepareStatement.setInt(1, restaurantId);
			 prepareStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try
		 {
			  statement = connection.createStatement();
			  resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			  while(resultSet.next())
			  {
				  restaurants.add(mapResultSetToRestaurant(resultSet));
			  }
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		
		return restaurants;
	}
	
	 private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
	        int restaurantId = resultSet.getInt("RestaurantID");
	        String name = resultSet.getString("Name");
	        String address = resultSet.getString("Address");
	        int deliveryTime = resultSet.getInt("DeliveryTime");
	        double rating = resultSet.getDouble("Rating");
	        String cuisineType = resultSet.getString("CuisineType");
	        String isActive = resultSet.getString("IsActive");
	        int adminUserId = resultSet.getInt("AdminUserID");
	        String imagePath = resultSet.getString("ImagePath");

	        return new Restaurant(restaurantId, name, address, rating, cuisineType, isActive, deliveryTime, adminUserId, imagePath);
	    }

	@Override
	public void deleteMenu(int restaurantId) {

		try {
	        prepareStatement = connection.prepareStatement(DeleTE_MENU_QUERY_BY_RESURANT);
	        prepareStatement.setInt(1, restaurantId);
	        prepareStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
