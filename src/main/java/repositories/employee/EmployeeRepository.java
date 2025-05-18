package repositories.employee;

import models.employee.Employee;
import utilities.cryptography.Cryptography;
import utilities.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for performing CRUD and authentication operations on
 * Employee data.
 */
public class EmployeeRepository {

    /**
     * Inserts a new employee into the database.
     */
    public static boolean create(Employee employee) {
        String sql = "INSERT INTO `Employee` (username, password_hash, first_name, last_name, role, salary email, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getUsername());
            statement.setString(2, employee.getPasswordHash());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getLastName());
            statement.setString(5, employee.getRole());
            statement.setBigDecimal(6, employee.getSalary());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getPhoneNumber());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an employee by ID.
     */
    public static Employee findById(int employeeId) {
        String sql = "SELECT * FROM `Employee` WHERE employee_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all employees.
     */
    public static List<Employee> findAll() {
        String sql = "SELECT * FROM `Employee`";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(mapEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * Updates an employee's details.
     */
    public static boolean update(Employee employee) {
        String sql = "UPDATE `Employee` SET username = ?, password_hash = ?, first_name = ?, last_name = ?, role = ?, salary = ?, email = ?, phone_number = ? WHERE employee_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getUsername());
            statement.setString(2, employee.getPasswordHash());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getLastName());
            statement.setString(5, employee.getRole());
            statement.setBigDecimal(6, employee.getSalary());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getPhoneNumber());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an employee by ID.
     */
    public static boolean delete(int employeeId) {
        String sql = "DELETE FROM `Employee` WHERE employee_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Authenticates an employee by username and password hash.
     */
    public static Employee authenticate(String username, String password) {
        String sql = "SELECT * FROM `Employee` WHERE username = ? AND password_hash = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, Cryptography.hashPassword(password));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapEmployee(resultSet);
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
        String sql = "SELECT 1 FROM `Employee` WHERE username = ?";

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
     * Extracts an Employee object from a ResultSet row.
     */
    private static Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("role"),
                rs.getBigDecimal("salary"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
    }
}
