package com.abdullahalmutairi.emspro.repository.impl;

import com.abdullahalmutairi.emspro.database.ConnectionManager;
import com.abdullahalmutairi.emspro.model.Position;
import com.abdullahalmutairi.emspro.repository.PositionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionRepositoryImpl implements PositionRepository {

    @Override
    public void save(Position position) throws SQLException {
        String sql = "INSERT INTO Positions (PositionID, Name) VALUES (?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, position.getPositionId());
            statement.setString(2, position.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Position> findById(int positionId) throws SQLException {
        String sql = "SELECT PositionID, Name FROM Positions WHERE PositionID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, positionId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Position> findAll() throws SQLException {
        String sql = "SELECT PositionID, Name FROM Positions";
        List<Position> positions = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                positions.add(mapRow(resultSet));
            }
        }

        return positions;
    }

    @Override
    public void update(Position position) throws SQLException {
        String sql = "UPDATE Positions SET Name = ? WHERE PositionID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getName());
            statement.setInt(2, position.getPositionId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int positionId) throws SQLException {
        String sql = "DELETE FROM Positions WHERE PositionID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, positionId);
            statement.executeUpdate();
        }
    }

    private Position mapRow(ResultSet resultSet) throws SQLException {
        return new Position(
                resultSet.getInt("PositionID"),
                resultSet.getString("Name")
        );
    }
}
