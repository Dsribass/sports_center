package org.example.application.main;

import org.example.application.repository.sqlite.DatabaseBuilder;
import org.example.application.repository.sqlite.SqliteClientDAO;
import org.example.application.repository.sqlite.SqliteSportCourtDAO;
import org.example.application.repository.sqlite.SqliteSportsCourtRentalDAO;
import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court.soccer_court.SoccerCourt;
import org.example.domain.entities.sports_court.soccer_court.TypeOfSoccer;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.client.ClientDAO;
import org.example.domain.usecases.sports_court.SportCourtDAO;
import org.example.domain.usecases.sports_court_rental.CancelRentSportCourtUseCase;
import org.example.domain.usecases.sports_court_rental.FindSportsCourtRentalUseCase;
import org.example.domain.usecases.sports_court_rental.RentSportCourtUseCase;
import org.example.domain.usecases.sports_court_rental.SportsCourtRentalDAO;

import java.time.LocalDate;

public class Main {
    private static SportsCourtRentalDAO sportsCourtRentalDAO;
    private static ClientDAO clientDAO;
    private static SportCourtDAO sportCourtDAO;

    public static FindSportsCourtRentalUseCase findSportsCourtRentalUseCase;
    public static RentSportCourtUseCase rentSportCourtUseCase;
    public static CancelRentSportCourtUseCase cancelRentSportCourtUseCase;

    public static void main(String[] args) {
        buildDatabaseIfMissing();
    }

    private static void buildDatabaseIfMissing() {
        DatabaseBuilder db = new DatabaseBuilder();
        db.buildDatabase();
    }

    private void injectDependencies() {
        sportsCourtRentalDAO = new SqliteSportsCourtRentalDAO();
        clientDAO = new SqliteClientDAO();
        sportCourtDAO = new SqliteSportCourtDAO();

        findSportsCourtRentalUseCase = new FindSportsCourtRentalUseCase(sportsCourtRentalDAO);
        cancelRentSportCourtUseCase = new CancelRentSportCourtUseCase(sportsCourtRentalDAO);
        rentSportCourtUseCase = new RentSportCourtUseCase(sportsCourtRentalDAO,sportCourtDAO,clientDAO);
    }
}
