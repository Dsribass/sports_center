package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.client.ClientDAO;
import org.example.domain.usecases.sports_court.SportCourtDAO;
import org.example.domain.usecases.utils.EntityNotFoundException;
import org.example.domain.usecases.utils.InvalidDateException;

import java.time.LocalDate;

public class RentSportCourtUseCase {
    private SportsCourtRentalDAO sportsCourtRentalDAO;
    private SportCourtDAO sportCourtDAO;
    private ClientDAO clientDAO;

    public RentSportCourtUseCase(SportsCourtRentalDAO sportsCourtRentalDAO, SportCourtDAO sportCourtDAO, ClientDAO clientDAO) {
        this.sportsCourtRentalDAO = sportsCourtRentalDAO;
        this.sportCourtDAO = sportCourtDAO;
        this.clientDAO = clientDAO;
    }

    public Integer rentSportCourt(SportsCourtRental sportsCourtRental) {
        if (sportsCourtRental == null)
            throw new IllegalArgumentException("SportsCourtRental cannot be null");
        if (sportsCourtRental.getDate() == null)
            throw new IllegalArgumentException("Date cannot be null");
        if (sportsCourtRental.getSportCourt() == null)
            throw new IllegalArgumentException("Sport Court cannot be null");
        if (sportsCourtRental.getClient() == null)
            throw new IllegalArgumentException("Client cannot be null");

        if (sportCourtDAO.findOne(sportsCourtRental.getIdSportCourt()).isEmpty())
            throw new EntityNotFoundException("Sport Court was not founded");

        if (clientDAO.findOne(sportsCourtRental.getCPFClient()).isEmpty()) {
            throw new EntityNotFoundException("Client was not founded");
        }

        if (isRentedSportsCourt(sportsCourtRental.getSportCourt(),sportsCourtRental.getDate())) {
            throw new SportsCourtAlreadyRentedException("Sport Court is already rented in this Date");
        }

        return sportsCourtRentalDAO.insert(sportsCourtRental);
    }

    public boolean isRentedSportsCourt(SportsCourt sportsCourt, LocalDate date){
        if(sportsCourt == null)
            throw new IllegalArgumentException("SportsCourt cannot be null");
        if(date == null)
            throw new IllegalArgumentException("Date cannot be null");
        if(date.isBefore(LocalDate.now())){
            throw new InvalidDateException("Date cannot be before today");
        }

        return sportsCourtRentalDAO.findOneBySportsCourtAndDate(sportsCourt.getId(), date).isPresent();
    }
}
