package com.food.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.models.Order;
import com.food.servlets.DataBase_Connection;

public class OrderDAOImpl implements OrderDAO {

    private static Connection connection;
    private static PreparedStatement prepareStatement;
    private static ResultSet resultSet;
    private static Statement statement;

    private static final String INSERT_QUERY = "INSERT INTO `ordertable`(`UserID`, `RestaurantID`, `OrderDate`, `TotalAmount`, `Status`, `PaymentMethod`,`OrderID`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `ordertable` WHERE `OrderID` = ?";
    private static final String UPDATE_QUERY = "UPDATE `ordertable` SET `UserID` = ?, `RestaurantID` = ?, `OrderDate` = ?, `TotalAmount` = ?, `Status` = ?, `PaymentMethod` = ? WHERE `OrderID` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `ordertable` WHERE `OrderID` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `ordertable`";
    private static final String SELECT_ALL_ORDERS_BY_USERID = "SELECT * FROM `ordertable` WHERE UserID = ?";

    public OrderDAOImpl()
    {
    	DataBase_Connection dataBase_Connection = new DataBase_Connection();
		connection  = dataBase_Connection.getConnection();
    }

    @Override
    public void addOrder(Order order) {
        try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);

            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setDate(3, order.getOrderDate());
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMethod());
            prepareStatement.setInt(7, order.getOrderId());
            
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId) {
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderId);
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrder(Order order) {
        try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);

            prepareStatement.setInt(1, order.getUserId());
            prepareStatement.setInt(2, order.getRestaurantId());
            prepareStatement.setDate(3, order.getOrderDate());
            prepareStatement.setDouble(4, order.getTotalAmount());
            prepareStatement.setString(5, order.getStatus());
            prepareStatement.setString(6, order.getPaymentMethod());
            prepareStatement.setInt(7, order.getOrderId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, orderId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getALLOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        try {
            prepareStatement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_USERID);
            prepareStatement.setInt(1, userId);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("OrderID");
        int userId = resultSet.getInt("UserID");
        int restaurantId = resultSet.getInt("RestaurantID");
        java.sql.Date orderDate = resultSet.getDate("OrderDate");
        double totalAmount = resultSet.getDouble("TotalAmount");
        String status = resultSet.getString("Status");
        String paymentMethod = resultSet.getString("PaymentMethod");

        return new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMethod);
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
