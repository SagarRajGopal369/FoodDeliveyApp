package com.food.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderHistoryDAO;
import com.food.models.OrderHistory;
import com.food.servlets.DataBase_Connection;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    private static Connection connection;
    private static PreparedStatement prepareStatement;
    private static ResultSet resultSet;
    private static Statement statement;

    private static final String INSERT_QUERY = "INSERT INTO `orderhistory`(`UserID`, `OrderID`, `OrderDate`, `TotalAmount`, `Status`) VALUES(?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `orderhistory` WHERE `OrderHistoryID` = ?";
    private static final String UPDATE_QUERY = "UPDATE `orderhistory` SET `UserID` = ?, `OrderID` = ?, `OrderDate` = ?, `TotalAmount` = ?, `Status` = ? WHERE `OrderHistoryID` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `orderhistory` WHERE `OrderHistoryID` = ?";
    private static final String SELECT_BY_USER_QUERY = "SELECT * FROM `orderhistory` WHERE `UserID` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `orderhistory`";

    public OrderHistoryDAOImpl()
    {
    	DataBase_Connection dataBase_Connection = new DataBase_Connection();
		connection  = dataBase_Connection.getConnection();
    }

    @Override
    public void addOrderHistory(OrderHistory orderHistory) {
        try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);

            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setDate(3, orderHistory.getOrderDate());
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, orderHistoryId);
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToOrderHistory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);

            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setDate(3, orderHistory.getOrderDate());
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());
            prepareStatement.setInt(6, orderHistory.getOrderHistoryId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, orderHistoryId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderHistory> getOrderHistoryByUser(int userId) {
        List<OrderHistory> orderHistories = new ArrayList<>();
        try {
            prepareStatement = connection.prepareStatement(SELECT_BY_USER_QUERY);
            prepareStatement.setInt(1, userId);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                orderHistories.add(mapResultSetToOrderHistory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderHistories;
    }


    private OrderHistory mapResultSetToOrderHistory(ResultSet resultSet) throws SQLException {
        int orderHistoryId = resultSet.getInt("OrderHistoryID");
        int userId = resultSet.getInt("UserID");
        int orderId = resultSet.getInt("OrderID");
        java.sql.Date orderDate = resultSet.getDate("OrderDate");
        double totalAmount = resultSet.getDouble("TotalAmount");
        String status = resultSet.getString("Status");

        return new OrderHistory(orderHistoryId, userId, orderId, orderDate, totalAmount, status);
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
