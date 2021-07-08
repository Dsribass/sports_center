package org.example.domain.usecases.sports_court_rental;

import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;

import java.util.List;
import java.util.Optional;

public class FindSportsCourtRentalUseCase {
    private SportsCourtRentalDAO sportsCourtRentalDAO;

    public FindSportsCourtRentalUseCase(SportsCourtRentalDAO sportsCourtRentalDAO) {
        this.sportsCourtRentalDAO = sportsCourtRentalDAO;
    }

    public Optional<SportsCourtRental> findOne(Integer id) {
        if(id == null)
            throw new IllegalArgumentException("ID cannot be null");
        return sportsCourtRentalDAO.findOne(id);
    }

    public List<SportsCourtRental> fetchAll() {
        return sportsCourtRentalDAO.fetchAll();
    }

    public List<SportsCourtRental> fetchAllByClient(Client client){
        if(client == null)
            throw new IllegalArgumentException("Client cannot be null");
        return fetchAllByClient(client.getCpf());
    }

    public List<SportsCourtRental> fetchAllByClient(String cpf){
        if(cpf == null)
            throw new IllegalArgumentException("CPF cannot be null");
        return sportsCourtRentalDAO.fetchAllByClient(cpf);
    }
}
