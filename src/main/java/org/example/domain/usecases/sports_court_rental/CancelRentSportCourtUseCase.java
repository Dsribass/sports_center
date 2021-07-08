package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.utils.EntityNotFoundException;

public class CancelRentSportCourtUseCase {
    private SportsCourtRentalDAO sportsCourtRentalDAO;

    public CancelRentSportCourtUseCase(SportsCourtRentalDAO sportsCourtRentalDAO) {
        this.sportsCourtRentalDAO = sportsCourtRentalDAO;
    }

    public boolean cancelRentSportCourt(SportsCourtRental sportsCourtRental) {
        if(sportsCourtRental == null)
            throw new IllegalArgumentException("SportsCourtRental cannot be null");

        return cancelRentSportCourt(sportsCourtRental.getId());
    }

    public boolean cancelRentSportCourt(Integer id) {
        if(id == null)
            throw new IllegalArgumentException("ID SportCourtRental cannot be null");

        if(sportsCourtRentalDAO.findOne(id).isEmpty()){
            throw new EntityNotFoundException("Sport Court Rental not founded");
        }

        return sportsCourtRentalDAO.delete(id);
    }
}
