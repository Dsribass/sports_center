package org.example.application.repository.sqlite;

import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court.soccer_court.SoccerCourt;
import org.example.domain.entities.sports_court.soccer_court.TypeOfSoccer;
import org.example.domain.entities.sports_court.volleyball_court.TypeOfVolleyball;
import org.example.domain.entities.sports_court.volleyball_court.VolleyballCourt;
import org.example.domain.usecases.sports_court.SportCourtDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteSportCourtDAO implements SportCourtDAO {

    private void setStatements(SportsCourt sportsCourt, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, sportsCourt.indoorCourtToInt());
        stmt.setString(2, sportsCourt.getCourtSize().toString());
        stmt.setDouble(3, sportsCourt.getCourtValue());

        if (sportsCourt instanceof SoccerCourt) {
            SoccerCourt soccerCourt = (SoccerCourt) sportsCourt;
            stmt.setString(4, soccerCourt.getTypeOfSoccer().toString());
        } else
            stmt.setNull(4, Types.VARCHAR);

        if (sportsCourt instanceof VolleyballCourt) {
            VolleyballCourt volleyballCourt = (VolleyballCourt) sportsCourt;
            stmt.setString(5, volleyballCourt.getTypeOfVolleyball().toString());
        } else
            stmt.setNull(5, Types.VARCHAR);
    }

    @Override
    public Integer insert(SportsCourt sportsCourt) {
        String sql =
                "INSERT INTO sport_court" +
                        "(indoor_court,court_size,court_value,type_of_soccer,type_of_volleyball)" +
                        " VALUES(?,?,?,?,?);";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setStatements(sportsCourt, stmt);
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Integer id, SportsCourt sportsCourt) {
        String sql =
                "UPDATE sport_court SET " +
                        "indoor_court= ?,court_size= ?,court_value= ?,type_of_soccer= ?,type_of_volleyball= ?" +
                        " WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            setStatements(sportsCourt, stmt);
            stmt.setInt(6,id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM sport_court WHERE id = ?";

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
    public Optional<SportsCourt> findOne(Integer id) {
        if(id == null) return Optional.ofNullable(null);
        String sql = "SELECT * FROM sport_court WHERE id = ?";
        SportsCourt sportsCourt = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
                sportsCourt = getSportCourtFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(sportsCourt);
    }

    private SportsCourt getSportCourtFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Boolean indoorCourt = rs.getInt("indoor_court") == 1;
        CourtSize courtSize = CourtSize.convertToEnum(rs.getString("court_size"));
        Double value = rs.getDouble("court_value");

        if(rs.getString("type_of_soccer") != null){
            TypeOfSoccer typeOfSoccer = TypeOfSoccer.convertToEnum(rs.getString("type_of_soccer"));
            return new SoccerCourt(id,indoorCourt,courtSize,value,typeOfSoccer);
        }else{
            TypeOfVolleyball typeOfVolleyball = TypeOfVolleyball.convertToEnum(rs.getString("type_of_volleyball"));
            return new VolleyballCourt(id,indoorCourt,courtSize,value,typeOfVolleyball);
        }
    }

    @Override
    public List<SportsCourt> fetchAll() {
        String sql = "SELECT * FROM sport_court";
        List<SportsCourt> sportsCourt = new ArrayList<>();

        try(Statement stmt = ConnectionFactory.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next())
                sportsCourt.add(getSportCourtFromResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sportsCourt;
    }
}
