package com.hei.petroliere.repository;

import com.hei.petroliere.dbconnection.ConnectionDB;
import com.hei.petroliere.model.Sale;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleDAO {

    private static final String TABLE_NAME = "sale";

    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                sales.add(mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve product", e);
        }
        return sales;
    }
    public Sale findById(Integer saleId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, saleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve product by ID", e);
        }
        return null;
    }
    public void save(Sale sale) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (sale.getSaleId() != null) {
                throw new IllegalArgumentException("Sale number should not be provided for new sale.");
            }
            sale.setSaleId(null);

            String insertQuery = "INSERT INTO " + TABLE_NAME + " (sale_date, quantity_sold, sale_amount, id_station, id_product) VALUES (?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setTimestamp(1, Timestamp.from(sale.getSaleDate()));
            insertStatement.setDouble(2, sale.getQuantitySold());
            insertStatement.setDouble(3, sale.getSaleAmount());
            insertStatement.setInt(4, sale.getIdStation());
            insertStatement.setInt(5, sale.getIdProduct());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                sale.setSaleId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Sale", e);
        }
    }
    public void delete(Integer saleId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, saleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete sale", e);
        }
    }

    private Sale mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Sale(
                resultSet.getInt("sale_id"),
                resultSet.getTimestamp("sale_date").toInstant(),
                resultSet.getDouble("quantity_sold"),
                resultSet.getDouble("sale_amount"),
                resultSet.getInt("id_station"),
                resultSet.getInt("id_product")
        );
    }
}