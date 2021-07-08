package org.example.application.repository.sqlite;

import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.usecases.sports_court.SportCourtDAO;

import java.util.List;
import java.util.Optional;

public class SqliteSportCourtDAO implements SportCourtDAO {
    @Override
    public Integer insert(SportsCourt type) {
        return null;
    }

    @Override
    public boolean update(Integer key, SportsCourt type) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public Optional<SportsCourt> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<SportsCourt> fetchAll() {
        return null;
    }
}
