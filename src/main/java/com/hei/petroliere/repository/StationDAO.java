package com.hei.petroliere.repository;

import com.hei.petroliere.dbconnection.ConnectionDB;
import com.hei.petroliere.model.Station;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationDAO {

    private static final String TABLE_NAME = "station";

    public List<Station> findAll() {
        List<Station> stocks = new ArrayList<>();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                stocks.add(mapResultSetToStation(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve station", e);
        }
        return stocks;
    }
    public Station findById(Integer stationId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_station = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToStation(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve station by ID", e);
        }
        return null;
    }
    public void save(Station station) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (station.getStationId() != null) {
                throw new IllegalArgumentException("Station number should not be provided for new station.");
            }
            station.setStationId(null);

            String insertQuery = "INSERT INTO " + TABLE_NAME + " (station_name, address, evaporation_rate_gasoline, evaporation_rate_diesel, evaporation_rate_kerosene) VALUES (?,?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1,station.getStationName());
            insertStatement.setString(2, station.getAddress());
            insertStatement.setDouble(3, station.getEvaporationRateGasoline());
            insertStatement.setDouble(4, station.getEvaporationRateDiesel());
            insertStatement.setDouble(5, station.getEvaporationRateKerosene());
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                station.setStationId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Station", e);
        }
    }
    public void delete(Integer stationId) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id_station = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, stationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete station", e);
        }
    }

    private Station mapResultSetToStation(ResultSet resultSet) throws SQLException {
        return new Station(
                resultSet.getInt("id_station"),
                resultSet.getString("station_name"),
                resultSet.getString("address"),
                resultSet.getDouble("evaporation_rate_gasoline"),
                resultSet.getDouble("evaporation_rate_diesel"),
                resultSet.getDouble("evaporation_rate_kerosene")
        );
    }
}