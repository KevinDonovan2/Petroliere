package com.hei.petroliere.repository;


import com.hei.petroliere.dbconnection.ConnectionDB;
import com.hei.petroliere.model.Price;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceDAO {

    private static final String TABLE_NAME = "price";

    public List<Price> findAll() {
        List<Price> price = new ArrayList<>();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                price.add(mapResultSetToPrice(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve price", e);
        }
        return price;
    }
    public Price findById(Integer idPrice) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_price = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPrice);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPrice(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve price by ID", e);
        }
        return null;
    }
    public void save(Price price) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (price.getIdPrice() != null) {
                throw new IllegalArgumentException("Price number should not be provided for new accounts.");
            }
            price.setIdPrice(null);

            String insertQuery = "INSERT INTO " + TABLE_NAME + " (price_date, unit_price, id_product) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setTimestamp(1, Timestamp.from(price.getPriceDate()));
            insertStatement.setDouble(2, price.getUnitPrice());
            insertStatement.setInt(3, price.getIdProduct());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                price.setIdPrice(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save price", e);
        }
    }
    public void delete(Integer idPrice) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id_price = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPrice);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete price", e);
        }
    }

    private Price mapResultSetToPrice(ResultSet resultSet) throws SQLException {
        return new Price(
                resultSet.getInt("id_price"),
                resultSet.getTimestamp("price_date").toInstant(),
                resultSet.getDouble("unit_price"),
                resultSet.getInt("id_product")
        );
    }
}

