package repositories.customer;

import models.customer.Customer;
import utilities.cryptography.Cryptography;
import utilities.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for performing CRUD and authentication operations on
 * Customer data.
 */
public class CustomerRepository {

    /**
     * Inserts a new customer into the database.
     */
    public static boolean create(Customer customer) {
        String sql = "INSERT INTO `Customer` (first_name, last_name, address, email, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPhoneNumber());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating customer", e);
        }
    }

    /**
     * Retrieves an customer by ID.
     */
    public static Customer findById(int customerId) {
        String sql = "SELECT * FROM `Customer` WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all customers.
     */
    public static List<Customer> findAll() {
        String sql = "SELECT * FROM `Customer`";
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customers.add(mapCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Updates an customer's details.
     */
    public static boolean update(Customer customer) {
        String sql = "UPDATE `Customer` SET first_name = ?, last_name = ?, address = ?, email = ?, phone_number = ? WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPhoneNumber());
            statement.setInt(6, customer.getCustomerId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an customer by ID.
     */
    public static boolean delete(int customerId) {
        String sql = "DELETE FROM `Customer` WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Authenticates an customer by username and password hash.
     */
    public static Customer authenticate(String username, String password) {
        String sql = "SELECT * FROM `Customer` WHERE username = ? AND password_hash = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, Cryptography.hashPassword(password));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if a username is already taken.
     */
    public static boolean isUsernameTaken(String username) {
        String sql = "SELECT 1 FROM `Customer` WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Extracts an Customer object from a ResultSet row.
     */
    private static Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
    }
}
