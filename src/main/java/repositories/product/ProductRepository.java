package repositories.product;

import java.sql.*;
import java.util.*;

import models.product.Product;
import models.product.ProductModifierType;
import utilities.database.DatabaseConnection;

public class ProductRepository {
    private ProductRepository() {}

    public static Product findById(int productId) {
        String sql = "SELECT * FROM `Product` WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = mapProduct(resultSet);
                product.getModifierTypes().addAll(ProductModifierTypeRepository.findByProductId(productId));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM `Product`";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = mapProduct(resultSet);
                product.getModifierTypes().addAll(ProductModifierTypeRepository.findByProductId(product.getProductId()));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static boolean create(Product product) {
        String sql = "INSERT INTO `Product` (name, description, stock, threshold, base_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getStock());
            statement.setInt(4, product.getThreshold());
            statement.setBigDecimal(5, product.getBasePrice());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }

                for (ProductModifierType type : product.getModifierTypes()) {
                    type.setProductId(product.getProductId());
                    ProductModifierTypeRepository.insert(type);
                }

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean update(Product product) {
        String sql = "UPDATE `Product` SET name = ?, description = ?, stock = ?, threshold = ?, base_price = ? WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getStock());
            statement.setInt(4, product.getThreshold());
            statement.setBigDecimal(5, product.getBasePrice());
            statement.setInt(6, product.getProductId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                List<ProductModifierType> existingTypes = ProductModifierTypeRepository.findByProductId(product.getProductId());

                // Delete removed types
                for (ProductModifierType old : existingTypes) {
                    if (!product.getModifierTypes().contains(old)) {
                        ProductModifierTypeRepository.delete(old.getModifierTypeId());
                    }
                }

                // Insert or update types
                for (ProductModifierType type : product.getModifierTypes()) {
                    type.setProductId(product.getProductId());

                    if (type.getModifierTypeId() == null || type.getModifierTypeId() == 0) {
                        ProductModifierTypeRepository.insert(type);
                    } else {
                        ProductModifierTypeRepository.update(type);
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean delete(int productId) {
        try {
            List<ProductModifierType> types = ProductModifierTypeRepository.findByProductId(productId);

            for (ProductModifierType type : types) {
                ProductModifierTypeRepository.delete(type.getModifierTypeId());
            }

            String sql = "DELETE FROM `Product` WHERE product_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, productId);
                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static Product mapProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setStock(rs.getInt("stock"));
        product.setThreshold(rs.getInt("threshold"));
        product.setBasePrice(rs.getBigDecimal("base_price"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        product.setUpdatedAt(rs.getTimestamp("updated_at"));
        return product;
    }
}
