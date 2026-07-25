package com.abdullahalmutairi.emspro.repository;

import com.abdullahalmutairi.emspro.model.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PositionRepository {

    void save(Position position) throws SQLException;

    Optional<Position> findById(int positionId) throws SQLException;

    List<Position> findAll() throws SQLException;

    void update(Position position) throws SQLException;

    void deleteById(int positionId) throws SQLException;
}
