package repositories.inventory;

import models.inventory.Inventory;
import utilities.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {
   
    /**
     * Inserts a new employee into the database.
     */
    public static boolean create(Inventory inventory) {
        String sql = "INSERT INTO `Inventory` (item_name, item_type, base_price, stock, floor_amount, roof_amount) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, inventory.getItemName());
            statement.setString(2, inventory.getItemType());
            statement.setBigDecimal(3, inventory.getBasePrice());
            statement.setInt(4, inventory.getStock());
            statement.setInt(5, inventory.getFloorAmount());
            statement.setInt(6, inventory.getRoofAmount());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an employee by ID.
     */
    public static Inventory findById(int itemId) {
        String sql = "SELECT * FROM `Inventory` WHERE item_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapInventory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all employees.
     */
    public static List<Inventory> findAll() {
        String sql = "SELECT * FROM `Inventory`";
        List<Inventory> inventory = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                inventory.add(mapInventory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventory;
    }

    /**
     * Updates an employee's details.
     */
    public static boolean update(Inventory inventory) {
        String sql = "UPDATE `Inventory` SET item_name = ?, item_type = ?, base_price = ?, stock = ?, floor_amount = ?, roof_amount = ? WHERE item_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, inventory.getItemName());
                    statement.setString(2, inventory.getItemType());
                    statement.setBigDecimal(3, inventory.getBasePrice());
                    statement.setInt(4, inventory.getStock());
                    statement.setInt(5, inventory.getFloorAmount());
                    statement.setInt(6, inventory.getRoofAmount());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an employee by ID.
     */
    public static boolean delete(int itemId) {
        String sql = "DELETE FROM `Inventory` WHERE item_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /**
     * Checks if a username is already taken.
     */
    public static boolean isItemNameTaken(String itemName) {
        String sql = "SELECT 1 FROM `Inventory` WHERE item_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, itemName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Extracts an Employee object from a ResultSet row.
     */
    private static Inventory mapInventory(ResultSet rs) throws SQLException {
        return new Inventory(
                rs.getInt("item_id"),
                rs.getString("item_name"),
                rs.getString("item_type"),
                rs.getBigDecimal("base_price"),
                rs.getInt("stock"),
                rs.getInt("floor_amount"),
                rs.getInt("roof_amount"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
    }
}

