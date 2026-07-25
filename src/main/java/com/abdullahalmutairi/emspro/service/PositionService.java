package com.abdullahalmutairi.emspro.service;

import com.abdullahalmutairi.emspro.model.Position;
import com.abdullahalmutairi.emspro.repository.PositionRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void addPosition(Position position) throws SQLException {
        positionRepository.save(position);
    }

    public Optional<Position> getPositionById(int positionId) throws SQLException {
        return positionRepository.findById(positionId);
    }

    public List<Position> getAllPositions() throws SQLException {
        return positionRepository.findAll();
    }

    public void updatePosition(Position position) throws SQLException {
        positionRepository.update(position);
    }

    public void deletePosition(int positionId) throws SQLException {
        positionRepository.deleteById(positionId);
    }
}
