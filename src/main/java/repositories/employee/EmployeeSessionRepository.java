package repositories.employee;

import models.employee.EmployeeSession;
import utilities.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSessionRepository {
    

    /**
     * Inserts a new employee session into the database.
     */
    public static boolean create(EmployeeSession session) {
        String sql = "INSERT INTO `EmployeeSession` (session_id, employee_id, is_expired, expires_at, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, session.getSessionId());
            statement.setInt(2, session.getEmployeeId());
            statement.setBoolean(3, session.isExpired());
            statement.setTimestamp(4, session.getExpiresAt());
            statement.setTimestamp(5, session.getCreatedAt());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an employee session by session ID.
     */
    public static EmployeeSession findById(String sessionId) {
        String sql = "SELECT * FROM `EmployeeSession` WHERE session_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sessionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapEmployeeSession(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates an employee session's details.
     */
    public static boolean update(EmployeeSession session) {
        String sql = "UPDATE `EmployeeSession` SET employee_id = ?, is_expired = ?, expires_at = ? WHERE session_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, session.getEmployeeId());
            statement.setBoolean(2, session.isExpired());
            statement.setTimestamp(3, session.getExpiresAt());
            statement.setString(4, session.getSessionId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an employee session by session ID.
     */
    public static boolean delete(String sessionId) {
        String sql = "DELETE FROM `EmployeeSession` WHERE session_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sessionId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all active sessions for a given employee ID.
     */
    public static List<EmployeeSession> findActiveSessionsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM `EmployeeSession` WHERE employee_id = ? AND is_expired = FALSE AND expires_at > CURRENT_TIMESTAMP";
        List<EmployeeSession> sessions = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sessions.add(mapEmployeeSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    /**
     * Retrieves all sessions for a given employee ID, including expired ones.
     */
    public static List<EmployeeSession> findAllSessionsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM `EmployeeSession` WHERE employee_id = ?";
        List<EmployeeSession> sessions = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sessions.add(mapEmployeeSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    /**
     * Marks an employee session as expired.
     */
    public static boolean expireSession(String sessionId) {
        String sql = "UPDATE `EmployeeSession` SET is_expired = TRUE WHERE session_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sessionId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Marks all active sessions for a given employee as expired.
     */
    public static boolean expireAllSessionsForEmployee(int employeeId) {
        String sql = "UPDATE `EmployeeSession` SET is_expired = TRUE WHERE employee_id = ? AND is_expired = FALSE";

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
     * Deletes all sessions for a given employee ID.
     */
    public static boolean deleteAllSessionsForEmployee(int employeeId) {
        String sql = "DELETE FROM `EmployeeSession` WHERE employee_id = ?";

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
     * Extracts an EmployeeSession object from a ResultSet row.
     */
    private static EmployeeSession mapEmployeeSession(ResultSet rs) throws SQLException {
        return new EmployeeSession(
                rs.getString("session_id"),
                rs.getInt("employee_id"),
                rs.getBoolean("is_expired"),
                rs.getTimestamp("expires_at"),
                rs.getTimestamp("created_at"));
    }
}
