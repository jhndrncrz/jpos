package repositories.order;

import java.sql.*;
import java.util.*;

import models.order.Order;
import models.order.OrderItem;
import utilities.database.DatabaseConnection;

public class OrderRepository {
    

    private OrderRepository() {
    }

    public static Order findById(int orderId) {
        String sql = "SELECT * FROM `Order` WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Order order = mapOrder(resultSet);
                order.getItems().addAll(OrderItemRepository.findByOrderId(orderId));

                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> findAll() {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM `Order`";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Order order = mapOrder(result);
                order.getItems().addAll(OrderItemRepository.findByOrderId(order.getOrderId()));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static boolean insert(Order order) {
        String sql = "INSERT INTO `Order` (employee_id, total_amount, payment_method, status, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, order.getEmployeeId());
            statement.setBigDecimal(2, order.getTotalAmount());
            statement.setString(3, order.getPaymentMethod());
            statement.setString(4, order.getStatus());
            statement.setTimestamp(5, order.getCreatedAt());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                }

                for (OrderItem item : order.getItems()) {
                    item.setOrderId(order.getOrderId());
                    OrderItemRepository.insert(item);
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(Order order) {
        String sql = "UPDATE `Order` SET employee_id = ?, total_amount = ?, payment_method = ?, status = ?, created_at = ? WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, order.getEmployeeId());
            statement.setBigDecimal(2, order.getTotalAmount());
            statement.setString(3, order.getPaymentMethod());
            statement.setString(4, order.getStatus());
            statement.setTimestamp(5, order.getCreatedAt());
            statement.setInt(6, order.getOrderId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                for (OrderItem oldItem : OrderItemRepository.findByOrderId(order.getOrderId())) {
                    if (!order.getItems().contains(oldItem)) {
                        OrderItemRepository.delete(oldItem.getOrderItemId());
                    }
                }

                for (OrderItem item : order.getItems()) {
                    if (item.getOrderItemId() == null || item.getOrderItemId() == 0) {
                        OrderItemRepository.insert(item);
                    } else {
                        OrderItemRepository.update(item);
                    }
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int orderId) {
        String sql = "DELETE FROM `Order` WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);

            for (OrderItem item : OrderItemRepository.findByOrderId(orderId)) {
                OrderItemRepository.delete(item.getOrderItemId());
            }

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Order mapOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("order_id"),
                rs.getInt("employee_id"),
                rs.getBigDecimal("total_amount"),
                rs.getString("payment_method"),
                rs.getString("status"),
                rs.getTimestamp("created_at"));
    }
}
