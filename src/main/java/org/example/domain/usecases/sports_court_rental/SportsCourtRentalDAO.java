package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.utils.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SportsCourtRentalDAO extends DAO<Integer, SportsCourtRental> {
    List<SportsCourtRental> fetchAllByClient(String cpf);
    Optional<SportsCourtRental> findOneBySportsCourtAndDate(Integer idSportsCourt, LocalDate date);
}
