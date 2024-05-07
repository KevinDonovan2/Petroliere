package com.hei.petroliere.repository;

import com.hei.petroliere.dbconnection.ConnectionDB;
import com.hei.petroliere.model.Stock;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockDAO {

    private static final String TABLE_NAME = "stock";

    public List<Stock> findAll() {
        List<Stock> stocks = new ArrayList<>();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                stocks.add(mapResultSetToStock(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve stock", e);
        }
        return stocks;
    }
    public Stock findById(Integer idStock) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_stock = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idStock);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToStock(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve stock by ID", e);
        }
        return null;
    }
    public void save(Stock stock) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (stock.getIdStock() != null) {
                throw new IllegalArgumentException("Stock number should not be provided for new stock.");
            }
            stock.setIdStock(null);

            String insertQuery = "INSERT INTO " + TABLE_NAME + " (stock_date, quantity_stocked, id_station, id_product) VALUES (?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setTimestamp(1, Timestamp.from(stock.getStockDate()));
            insertStatement.setDouble(2, stock.getQuantityStocked());
            insertStatement.setInt(3, stock.getIdStation());
            insertStatement.setInt(4, stock.getIdProduct());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                stock.setIdStock(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Stock", e);
        }
    }
    public void delete(Integer idStock) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id_stock = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idStock);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete stock", e);
        }
    }

    private Stock mapResultSetToStock(ResultSet resultSet) throws SQLException {
        return new Stock(
                resultSet.getInt("id_stock"),
                resultSet.getTimestamp("stock_date").toInstant(),
                resultSet.getDouble("quantity_stocked"),
                resultSet.getInt("id_station"),
                resultSet.getInt("id_product")
        );
    }
}