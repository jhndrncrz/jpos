package repositories.product;

import java.sql.*;
import java.util.*;

import models.product.ProductModifier;
import utilities.database.DatabaseConnection;

public class ProductModifierRepository {
    

    private ProductModifierRepository() {
    }

    public static ProductModifier findById(int modifierId) {
        String sql = "SELECT * FROM `ProductModifier` WHERE modifier_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, modifierId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapModifier(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProductModifier> findAll() {
        List<ProductModifier> modifiers = new ArrayList<>();
        String sql = "SELECT * FROM `ProductModifier`";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                modifiers.add(mapModifier(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modifiers;
    }

    public static List<ProductModifier> findByModifierTypeId(int modifierTypeId) {
        List<ProductModifier> modifiers = new ArrayList<>();
        String sql = "SELECT * FROM `ProductModifier` WHERE modifier_type_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, modifierTypeId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                modifiers.add(mapModifier(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modifiers;
    }

    public static boolean insert(ProductModifier modifier) {
        String sql = "INSERT INTO `ProductModifier` (modifier_type_id, name, price_modifier) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, modifier.getModifierTypeId());
            statement.setString(2, modifier.getName());
            statement.setBigDecimal(3, modifier.getPriceModifier());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    modifier.setModifierId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean update(ProductModifier modifier) {
        String sql = "UPDATE `ProductModifier` SET modifier_type_id = ?, name = ?, price_modifier = ? WHERE modifier_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, modifier.getModifierTypeId());
            statement.setString(2, modifier.getName());
            statement.setBigDecimal(3, modifier.getPriceModifier());
            statement.setInt(4, modifier.getModifierId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean delete(int modifierId) {
        String sql = "DELETE FROM `ProductModifier` WHERE modifier_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, modifierId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static ProductModifier mapModifier(ResultSet rs) throws SQLException {
        ProductModifier modifier = new ProductModifier();

        modifier.setModifierId(rs.getInt("modifier_id"));
        modifier.setModifierTypeId(rs.getInt("modifier_type_id"));
        modifier.setName(rs.getString("name"));
        modifier.setPriceModifier(rs.getBigDecimal("price_modifier"));

        return modifier;
    }
}
