package com.food.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.models.Menu;
import com.food.servlets.DataBase_Connection;

public class MenuDAOImpl implements MenuDAO {

    private static Connection connection;
    private static PreparedStatement prepareStatement;
    private static ResultSet resultSet;
    private static Statement statement;

    private static final String INSERT_QUERY = "INSERT INTO `menu`(`RestaurantID`, `ItemName`, `Description`, `Price`, `Rating`, `IsAvailable`, `ImagePath`) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM `menu` WHERE `MenuID` = ?";
    private static final String UPDATE_QUERY = "UPDATE `menu` SET `RestaurantID` = ?, `ItemName` = ?, `Description` = ?, `Price` = ?, `Rating` = ?, `IsAvailable` = ?, `ImagePath` = ? WHERE `MenuID` = ?";
    private static final String DELETE_QUERY = "DELETE FROM `menu` WHERE `MenuID` = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM `menu`";

    public MenuDAOImpl() {
        DataBase_Connection dataBase_Connection = new DataBase_Connection();
        connection = dataBase_Connection.getConnection();
    }

    @Override
    public void addMenu(Menu menu) {
        try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, menu.getRestaurantId());
            prepareStatement.setString(2, menu.getItemName());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setDouble(4, menu.getPrice());
            prepareStatement.setDouble(5, menu.getRating());
            prepareStatement.setString(6, menu.getIsAvailable());
            prepareStatement.setString(7, menu.getImagepath());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, prepareStatement, null);
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        try {
            prepareStatement = connection.prepareStatement(SELECT_QUERY);
            prepareStatement.setInt(1, menuId);
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToMenu(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, prepareStatement, null);
        }
        return null;
    }

    @Override
    public void updateMenu(Menu menu) {
        try {
            prepareStatement = connection.prepareStatement(UPDATE_QUERY);

            prepareStatement.setInt(1, menu.getRestaurantId());
            prepareStatement.setString(2, menu.getItemName());
            prepareStatement.setString(3, menu.getDescription());
            prepareStatement.setDouble(4, menu.getPrice());
            prepareStatement.setDouble(5, menu.getRating());
            prepareStatement.setString(6, menu.getIsAvailable());
            prepareStatement.setString(7, menu.getImagepath());
            prepareStatement.setInt(8, menu.getMenuId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, prepareStatement, null);
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try {
            prepareStatement = connection.prepareStatement(DELETE_QUERY);
            prepareStatement.setInt(1, menuId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, prepareStatement, null);
        }
    }

    @Override
    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        try {
            prepareStatement = connection.prepareStatement("SELECT * FROM Menu WHERE RestaurantID = ?");
            prepareStatement.setInt(1, restaurantId);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                menus.add(mapResultSetToMenu(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, prepareStatement, null);
        }

        return menus;
    }

    private Menu mapResultSetToMenu(ResultSet resultSet) throws SQLException {
        int menuId = resultSet.getInt("MenuID");
        int restaurantId = resultSet.getInt("RestaurantID");
        String itemName = resultSet.getString("ItemName");
        String description = resultSet.getString("Description");
        double price = resultSet.getDouble("Price");
        double rating = resultSet.getDouble("Rating");
        String isAvailable = resultSet.getString("IsAvailable");
        String imagepath = resultSet.getString("ImagePath");

        return new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagepath);
    }

    // Close resources method using try-with-resources
    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
