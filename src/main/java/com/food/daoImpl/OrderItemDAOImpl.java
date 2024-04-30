package com.food.daoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.food.dao.OrderItemDAO;
import com.food.models.OrderItem;
import com.food.servlets.DataBase_Connection;

public class OrderItemDAOImpl implements OrderItemDAO {

	 private static Connection connection;
	    private static PreparedStatement prepareStatement;
	    private static ResultSet resultSet;
	    private static Statement statement;

	    private static final String INSERT_QUERY = "INSERT INTO `orderitem`(`OrderID`, `MenuID`, `Quantity`, `TotalAmount`) VALUES(?, ?, ?, ?)";
	    private static final String SELECT_QUERY = "SELECT * FROM `orderitem` WHERE `OrderItemID` = ?";
	    private static final String UPDATE_QUERY = "UPDATE `orderitem` SET `OrderItemID` = ?, `MenuID` = ?, `Quantity` = ?, `TotalAmount` = ? WHERE `OrderItemID` = ?";
	    private static final String DELETE_QUERY = "DELETE FROM `orderitem` WHERE `OrderItemID` = ?";
	    private static final String SELECT_BY_ORDER_QUERY = "SELECT * FROM OrderItem WHERE `OrderID` = ?";
	    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderitem`";

	    public OrderItemDAOImpl()
	    {
	    	DataBase_Connection dataBase_Connection = new DataBase_Connection();
			connection  = dataBase_Connection.getConnection();
	    }

	    @Override
	    public void addOrderitem(OrderItem orderItem) {
	        try {
	            prepareStatement = connection.prepareStatement(INSERT_QUERY);

	            prepareStatement.setInt(1, orderItem.getOrderId());
	            prepareStatement.setInt(2, orderItem.getMenuId());
	            prepareStatement.setInt(3, orderItem.getQuantity());
	            prepareStatement.setDouble(4, orderItem.getTotalAmount());

	            prepareStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public OrderItem getOrderItem(int orderItemId) {
	        try {
	            prepareStatement = connection.prepareStatement(SELECT_QUERY);
	            prepareStatement.setInt(1, orderItemId);
	            resultSet = prepareStatement.executeQuery();

	            if (resultSet.next()) {
	                return mapResultSetToOrderItem(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public void updateOrderItem(OrderItem orderItem) {
	        try {
	            prepareStatement = connection.prepareStatement(UPDATE_QUERY);

	            prepareStatement.setInt(1, orderItem.getOrderId());
	            prepareStatement.setInt(2, orderItem.getMenuId());
	            prepareStatement.setInt(3, orderItem.getQuantity());
	            prepareStatement.setDouble(4, orderItem.getTotalAmount());
	            prepareStatement.setInt(5, orderItem.getOrderItemId());

	            prepareStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteOrderItem(int orderItemId) {
	        try {
	            prepareStatement = connection.prepareStatement(DELETE_QUERY);
	            prepareStatement.setInt(1, orderItemId);
	            prepareStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public List<OrderItem> getOrderItemsByOrder(int orderId) {
	        List<OrderItem> orderItems = new ArrayList<>();
	        try {
	            prepareStatement = connection.prepareStatement(SELECT_BY_ORDER_QUERY);
	            prepareStatement.setInt(1, orderId);
	            resultSet = prepareStatement.executeQuery();

	            while (resultSet.next()) {
	                orderItems.add(mapResultSetToOrderItem(resultSet));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return orderItems;
	    }

	    private OrderItem mapResultSetToOrderItem(ResultSet resultSet) throws SQLException {
	        int orderItemId = resultSet.getInt("OrderItemID");
	        int orderId = resultSet.getInt("OrderID");
	        int menuId = resultSet.getInt("MenuID");
	        int quantity = resultSet.getInt("Quantity");
	        double totalAmount = resultSet.getDouble("TotalAmount");

	        return new OrderItem(orderItemId, orderId, menuId, quantity, totalAmount);
	    }

	    // Close resources method
	    private void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
