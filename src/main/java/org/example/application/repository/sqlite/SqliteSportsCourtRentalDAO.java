package org.example.application.repository.sqlite;

import javafx.scene.control.TableRow;
import org.example.domain.entities.client.Client;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court_rental.SportsCourtRental;
import org.example.domain.usecases.sports_court_rental.SportsCourtRentalDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.application.main.Main.clientUseCase;
import static org.example.application.main.Main.sportsCourtUseCase;

public class SqliteSportsCourtRentalDAO implements SportsCourtRentalDAO {

    @Override
    public Integer insert(SportsCourtRental sportsCourtRental) {
        String sql = "INSERT INTO sports_court_rental(id_sport_court,date,cpf_client) VALUES(?,?,?);";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,sportsCourtRental.getSportCourt().getId());
            stmt.setString(2,sportsCourtRental.getDate().toString());
            stmt.setString(3,sportsCourtRental.getClient().getCpf());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Integer id, SportsCourtRental sportsCourtRental) {
        String sql = "UPDATE sports_court_rental SET id_sport_court= ?,date= ?,cpf_client= ? WHERE id = ?;";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,sportsCourtRental.getSportCourt().getId());
            stmt.setString(2,sportsCourtRental.getDate().toString());
            stmt.setString(3,sportsCourtRental.getClient().getCpf());

            stmt.setInt(4,id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM sports_court_rental WHERE id = ?;";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<SportsCourtRental> findOne(Integer id) {
        if(id == null) return Optional.ofNullable(null);

        String sql = "SELECT * FROM sports_court_rental WHERE id = ?;";
        SportsCourtRental sportsCourtRental = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,id);
            final ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
                sportsCourtRental = getSportsCourtRentalFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(sportsCourtRental);
    }

    @Override
    public Optional<SportsCourtRental> findOneBySportsCourtAndDate(Integer idSportsCourt,LocalDate date) {
        if(idSportsCourt == null) return Optional.ofNullable(null);

        String sql = "SELECT * FROM sports_court_rental WHERE id_sport_court = ? AND date = ?;";
        SportsCourtRental sportsCourtRental = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,idSportsCourt);
            stmt.setString(2,date.toString());

            final ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
                sportsCourtRental = getSportsCourtRentalFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(sportsCourtRental);
    }

    @Override
    public List<SportsCourtRental> fetchAll() {
        String sql = "SELECT * FROM sports_court_rental;";
        List<SportsCourtRental> sportsCourtRentals = new ArrayList<>();

        try(Statement stmt = ConnectionFactory.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next())
                sportsCourtRentals.add(getSportsCourtRentalFromResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sportsCourtRentals;
    }

    @Override
    public List<SportsCourtRental> fetchAllByClient(String cpf) {
        String sql = "SELECT * FROM sports_court_rental WHERE cpf_client = ?;";
        List<SportsCourtRental> sportsCourtRentals = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,cpf);
            final ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
                sportsCourtRentals.add(getSportsCourtRentalFromResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sportsCourtRentals;
    }

    private SportsCourtRental getSportsCourtRentalFromResultSet(ResultSet rs) throws SQLException {
        Client client = clientUseCase.findOne(rs.getString("cpf_client")).get();
        SportsCourt sportsCourt = sportsCourtUseCase.findOne(rs.getInt("id_sport_court")).get();

        return new SportsCourtRental(
                rs.getInt("id"),
                sportsCourt,
                client,
                LocalDate.parse(rs.getString("date"))
        );
    }
}
