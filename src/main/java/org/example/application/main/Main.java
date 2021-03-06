package org.example.application.main;

import org.example.application.repository.sqlite.DatabaseBuilder;
import org.example.application.repository.sqlite.SqliteClientDAO;
import org.example.application.repository.sqlite.SqliteSportCourtDAO;
import org.example.application.repository.sqlite.SqliteSportsCourtRentalDAO;
import org.example.application.view.Window;
import org.example.domain.usecases.client.ClientDAO;
import org.example.domain.usecases.client.ClientUseCase;
import org.example.domain.usecases.sports_court.SportCourtDAO;
import org.example.domain.usecases.sports_court.SportCourtUseCase;
import org.example.domain.usecases.sports_court_rental.CancelRentSportCourtUseCase;
import org.example.domain.usecases.sports_court_rental.FindSportsCourtRentalUseCase;
import org.example.domain.usecases.sports_court_rental.RentSportCourtUseCase;
import org.example.domain.usecases.sports_court_rental.SportsCourtRentalDAO;

import java.util.List;

public class Main {
    private static SportsCourtRentalDAO sportsCourtRentalDAO;
    private static ClientDAO clientDAO;
    private static SportCourtDAO sportCourtDAO;

    public static ClientUseCase clientUseCase;
    public static SportCourtUseCase sportsCourtUseCase;

    public static FindSportsCourtRentalUseCase findSportsCourtRentalUseCase;
    public static RentSportCourtUseCase rentSportCourtUseCase;
    public static CancelRentSportCourtUseCase cancelRentSportCourtUseCase;

    public static void main(String[] args) {
        buildDatabaseIfMissing();
        injectDependencies();
        Window.main(args);
    }

    private static void buildDatabaseIfMissing() {
        DatabaseBuilder db = new DatabaseBuilder();
        db.buildDatabase();
    }

    private static void injectDependencies() {
        sportsCourtRentalDAO = new SqliteSportsCourtRentalDAO();
        clientDAO = new SqliteClientDAO();
        sportCourtDAO = new SqliteSportCourtDAO();

        clientUseCase = new ClientUseCase(clientDAO);
        sportsCourtUseCase = new SportCourtUseCase(sportCourtDAO);

        findSportsCourtRentalUseCase = new FindSportsCourtRentalUseCase(sportsCourtRentalDAO);
        cancelRentSportCourtUseCase = new CancelRentSportCourtUseCase(sportsCourtRentalDAO);
        rentSportCourtUseCase = new RentSportCourtUseCase(sportsCourtRentalDAO,sportCourtDAO,clientDAO);
    }
}
