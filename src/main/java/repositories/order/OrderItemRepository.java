package repositories.order;

import java.sql.*;
import java.util.*;

import models.order.OrderItem;
import utilities.database.DatabaseConnection;

public class OrderItemRepository {

    private OrderItemRepository() {
    }

    public static OrderItem findById(int orderItemId) {
        String sql = "SELECT * FROM `OrderItem` WHERE order_item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderItemId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapOrderItem(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<OrderItem> findByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM `OrderItem` WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                items.add(mapOrderItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static boolean insert(OrderItem item) {
        String sql = "INSERT INTO `OrderItem` (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, item.getOrderId());
            statement.setInt(2, item.getProductId());
            statement.setInt(3, item.getQuantity());
            statement.setBigDecimal(4, item.getUnitPrice());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    item.setOrderItemId(generatedKeys.getInt(1));
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean update(OrderItem item) {
        String sql = "UPDATE `OrderItem` SET order_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE order_item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, item.getOrderId());
            statement.setInt(2, item.getProductId());
            statement.setInt(3, item.getQuantity());
            statement.setBigDecimal(4, item.getUnitPrice());
            statement.setInt(5, item.getOrderItemId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean delete(int orderItemId) {
        String sql = "DELETE FROM `OrderItem` WHERE order_item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderItemId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static OrderItem mapOrderItem(ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();

        item.setOrderItemId(rs.getInt("order_item_id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getBigDecimal("unit_price"));

        return item;
    }
}
