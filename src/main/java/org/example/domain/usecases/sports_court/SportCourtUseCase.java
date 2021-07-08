package org.example.domain.usecases.sports_court;

import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.usecases.utils.EntityAlreadyExistsException;
import org.example.domain.usecases.utils.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class SportCourtUseCase {
    SportCourtDAO sportCourtDAO;

    public SportCourtUseCase(SportCourtDAO sportCourtDAO) {
        this.sportCourtDAO = sportCourtDAO;
    }

    public Integer insert(SportsCourt sportsCourt) {
        validatorSportCourt(sportsCourt);

        if(sportCourtDAO.findOne(sportsCourt.getId()).isPresent())
            throw new EntityAlreadyExistsException("SportsCourt already exists in database");

        return sportCourtDAO.insert(sportsCourt);
    }

    public boolean update(Integer id, SportsCourt sportsCourt) {
        if(id == null)
            throw new IllegalArgumentException("ID cannot be null");

        validatorSportCourt(sportsCourt);

        if(sportCourtDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("SportsCourt was not found");

        return sportCourtDAO.update(id,sportsCourt);
    }

    public boolean delete(SportsCourt sportsCourt){
        if(sportsCourt == null)
            throw new IllegalArgumentException("SportsCourt cannot be null");
        return deleteById(sportsCourt.getId());
    }

    public boolean deleteById(Integer id) {
        if(id == null)
            throw new IllegalArgumentException("ID cannot be null");

        if(sportCourtDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("SportsCourt was not found");

        return sportCourtDAO.delete(id);
    }

    public List<SportsCourt> fetchAll() {
        return sportCourtDAO.fetchAll();
    }

    public Optional<SportsCourt> findOne(Integer id) {
        if(id == null)
            throw new IllegalArgumentException("ID cannot be null");
        return sportCourtDAO.findOne(id);
    }

    private void validatorSportCourt(SportsCourt sportsCourt) {
        if(sportsCourt == null)
            throw new IllegalArgumentException("SportsCourt cannot be null");
        if(sportsCourt.getCourtSize() == null)
            throw new IllegalArgumentException("Court Size cannot be null");
        if(sportsCourt.getCourtValue() == null)
            throw new IllegalArgumentException("Court Value cannot be null");
        if(sportsCourt.getIndoorCourt() == null)
            throw new IllegalArgumentException("Indoor Court cannot be null");
    }
}
