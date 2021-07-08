package org.example.application.repository.sqlite;

import org.example.domain.entities.client.Client;
import org.example.domain.usecases.client.ClientDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.application.main.Main.findSportsCourtRentalUseCase;

public class SqliteClientDAO implements ClientDAO {
    @Override
    public String insert(Client client) {
        String sql = "INSERT INTO clients VALUES(?,?);";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,client.getCpf());
            stmt.setString(2,client.getName());
            stmt.execute();
            return client.getCpf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String cpf, Client client) {
        String sql = "UPDATE clients SET name = ? WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,client.getName());
            stmt.setString(2,cpf);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String cpf) {
        String sql = "DELETE FROM clients WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,cpf);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Client> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Client> fetchAll() {
        String sql = "SELECT cpf, name FROM clients,sports_court_rental scr WHERE cpf = cpf_client;";
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new Client(
                rs.getString("cpf"),
                rs.getString("name"),
                findSportsCourtRentalUseCase.fetchAllByClient(rs.getString("cpf"))
        );
    }
}
