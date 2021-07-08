package org.example.application.repository.sqlite;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.sports_court_rental.SportsCourtRentalDAO;

import java.util.List;
import java.util.Optional;

public class SqliteSportsCourtRentalDAO implements SportsCourtRentalDAO {

    @Override
    public Integer insert(SportsCourtRental type) {
        return null;
    }

    @Override
    public boolean update(Integer key, SportsCourtRental type) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public Optional<SportsCourtRental> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<SportsCourtRental> fetchAll() {
        return null;
    }

    @Override
    public List<SportsCourtRental> fetchAllByClient(String cpf) {
        return null;
    }
}
