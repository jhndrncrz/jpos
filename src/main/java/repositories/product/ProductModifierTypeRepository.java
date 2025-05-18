package repositories.product;

import java.sql.*;
import java.util.*;

import models.product.ProductModifierType;
import models.product.ProductModifier;
import utilities.database.DatabaseConnection;

public class ProductModifierTypeRepository {
    

    private ProductModifierTypeRepository() {
    }

    public static ProductModifierType findById(int modifierTypeId) {
        String sql = "SELECT * FROM `ProductModifierType` WHERE modifier_type_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, modifierTypeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ProductModifierType type = mapModifierType(resultSet);
                type.getModifiers().addAll(ProductModifierRepository.findByModifierTypeId(modifierTypeId));
                return type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProductModifierType> findAll() {
        List<ProductModifierType> types = new ArrayList<>();
        String sql = "SELECT * FROM `ProductModifierType`";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductModifierType type = mapModifierType(resultSet);
                type.getModifiers().addAll(ProductModifierRepository.findByModifierTypeId(type.getModifierTypeId()));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    public static List<ProductModifierType> findByProductId(int productId) {
        List<ProductModifierType> types = new ArrayList<>();
        String sql = "SELECT * FROM `ProductModifierType` WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductModifierType type = mapModifierType(rs);
                type.getModifiers().addAll(ProductModifierRepository.findByModifierTypeId(type.getModifierTypeId()));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    public static boolean insert(ProductModifierType type) {
        String sql = "INSERT INTO `ProductModifierType` (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, type.getName());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    type.setModifierTypeId(keys.getInt(1));
                }

                for (ProductModifier modifier : type.getModifiers()) {
                    modifier.setModifierTypeId(type.getModifierTypeId());
                    ProductModifierRepository.insert(modifier);
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean update(ProductModifierType type) {
        String sql = "UPDATE `ProductModifierType` SET name = ? WHERE modifier_type_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, type.getName());
            statement.setInt(2, type.getModifierTypeId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                List<ProductModifier> existing = ProductModifierRepository
                        .findByModifierTypeId(type.getModifierTypeId());

                // Delete removed modifiers
                for (ProductModifier old : existing) {
                    if (!type.getModifiers().contains(old)) {
                        ProductModifierRepository.delete(old.getModifierId());
                    }
                }

                // Insert or update current modifiers
                for (ProductModifier modifier : type.getModifiers()) {
                    modifier.setModifierTypeId(type.getModifierTypeId());

                    if (modifier.getModifierId() == null || modifier.getModifierId() == 0) {
                        ProductModifierRepository.insert(modifier);
                    } else {
                        ProductModifierRepository.update(modifier);
                    }
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean delete(int modifierTypeId) {
        try {
            for (ProductModifier modifier : ProductModifierRepository.findByModifierTypeId(modifierTypeId)) {
                ProductModifierRepository.delete(modifier.getModifierId());
            }

            String sql = "DELETE FROM `ProductModifierType` WHERE modifier_type_id = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, modifierTypeId);
                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static ProductModifierType mapModifierType(ResultSet rs) throws SQLException {
        ProductModifierType type = new ProductModifierType();
        type.setModifierTypeId(rs.getInt("modifier_type_id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
