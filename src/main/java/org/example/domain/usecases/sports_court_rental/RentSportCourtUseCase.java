package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.client.ClientDAO;
import org.example.domain.usecases.sports_court.SportCourtDAO;
import org.example.domain.usecases.utils.EntityAlreadyExistsException;
import org.example.domain.usecases.utils.EntityNotFoundException;

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
        if(sportsCourtRental == null)
            throw new IllegalArgumentException("SportsCourtRental cannot be null");
        if(sportsCourtRental.getDate() == null)
            throw new IllegalArgumentException("Date cannot be null");
        if(sportsCourtRental.getSportCourt() == null)
            throw new IllegalArgumentException("Sport Court cannot be null");

        if(sportsCourtRentalDAO.findOne(sportsCourtRental.getId()).isPresent()){
            throw new EntityAlreadyExistsException("Sport Court is already rented");
        }

        if(sportCourtDAO.findOne(sportsCourtRental.getSportCourt().getId()).isEmpty())
            throw new EntityNotFoundException("Sport Court was not founded");


        if(clientDAO.findOne(sportsCourtRental.getClient().getCpf()).isPresent()){
            throw new EntityAlreadyExistsException("Client was not founded");
        }

        return sportsCourtRentalDAO.insert(sportsCourtRental);
    }
}
