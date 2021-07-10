package org.example.domain.usecases.sports_court_rental;

public class SportsCourtAlreadyRentedException extends RuntimeException{
    public SportsCourtAlreadyRentedException(String message) {
        super(message);
    }
}
