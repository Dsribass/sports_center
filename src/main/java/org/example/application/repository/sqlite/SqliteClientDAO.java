package org.example.application.repository.sqlite;

import org.example.domain.entities.client.Address;
import org.example.domain.entities.client.Client;
import org.example.domain.usecases.client.ClientDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteClientDAO implements ClientDAO {
    @Override
    public String insert(Client client) {
        String sql = "INSERT INTO clients VALUES(?,?,?,?,?,?,?);";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,client.getCpf());
            stmt.setString(2,client.getName());
            stmt.setString(3,client.getAddress().getState());
            stmt.setString(4,client.getAddress().getCity());
            stmt.setString(5,client.getAddress().getRoad());
            stmt.setInt(6,client.getAddress().getNumber());
            stmt.setString(7,client.getAddress().getComplement());

            stmt.execute();
            return client.getCpf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String cpf, Client client) {
        String sql = "UPDATE clients SET name= ?,state= ?, city= ?,road= ?,number= ?, complement= ? WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,client.getName());
            stmt.setString(2,client.getAddress().getState());
            stmt.setString(3,client.getAddress().getCity());
            stmt.setString(4,client.getAddress().getRoad());
            stmt.setInt(5,client.getAddress().getNumber());
            stmt.setString(6,client.getAddress().getComplement());

            stmt.setString(7,client.getCpf());
            stmt.execute();
            return true;
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
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Client> findOne(String cpf) {
        String sql = "SELECT * FROM clients WHERE cpf = ?;";
        Client client = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,cpf);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                client = getClientFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(client);
    }

    @Override
    public List<Client> fetchAll() {
        String sql = "SELECT * FROM clients;";
        List<Client> clients = new ArrayList<>();

        try (Statement stmt = ConnectionFactory.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                clients.add(getClientFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address(
                rs.getString("cpf"),
                rs.getString("state"),
                rs.getString("city"),
                rs.getString("road"),
                rs.getInt("number"),
                rs.getString("complement")
        );

        return new Client(
                rs.getString("cpf"),
                rs.getString("name"),
                address
        );
    }
}
