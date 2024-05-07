package com.hei.petroliere.repository;

import com.hei.petroliere.dbconnection.ConnectionDB;
import com.hei.petroliere.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    private static final String TABLE_NAME = "product";

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve product", e);
        }
        return products;
    }
    public Product findById(Integer productId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_product = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve product by ID", e);
        }
        return null;
    }
    public void save(Product product) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (product.getProductId() != null) {
                throw new IllegalArgumentException("Product number should not be provided for new product.");
            }
            product.setProductId(null);

            String insertQuery = "INSERT INTO " + TABLE_NAME + " (product_name, product_type) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getProductName());
            insertStatement.setString(2, product.getProductType());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setProductId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product", e);
        }
    }
    public void delete(Integer productId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id_product = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product", e);
        }
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("id_product"),
                resultSet.getString("product_name"),
                resultSet.getString("product_type")
        );
    }
}

