package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.utils.DAO;

import java.util.List;

public interface SportsCourtRentalDAO extends DAO<Integer, SportsCourtRental> {
    List<SportsCourtRental> fetchAllByClient(String cpf);
}
