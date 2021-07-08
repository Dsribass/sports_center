package org.example.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    public void buildDatabase() {
        if(isDatabaseMissing()) buildTables();
    }

    private void buildTables() {
        try(Statement stmt = ConnectionFactory.createStatement()){
            stmt.addBatch(createTableClient());
            stmt.addBatch(createTableSportCourt());
            stmt.addBatch(createTableSportsCourtRental());

            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String createTableClient() {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE clients (\n");
        sb.append("cpf TEXT NOT NULL primary key,\n");
        sb.append("name TEXT NOT NULL\n");
        sb.append(");\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    private String createTableSportCourt() {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE sport_court (\n");
        sb.append("id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n");
        sb.append("indoor_court INTEGER,\n");
        sb.append("court_size TEXT NOT NULL,\n");
        sb.append("court_value REAL NOT NULL,\n");
        sb.append("type_of_soccer TEXT,\n");
        sb.append("type_of_volleyball TEXT\n");
        sb.append(");\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    private String createTableSportsCourtRental() {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE sports_court_rental (\n");
        sb.append("id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n");
        sb.append("id_sport_court INTEGER NOT NULL,\n");
        sb.append("date TEXT NOT NULL,\n");
        sb.append("cpf_client TEXT NOT NULL,\n");
        sb.append("FOREIGN KEY(cpf_client) REFERENCES client(cpf),\n");
        sb.append("FOREIGN KEY(id_sport_court) REFERENCES sport_court(id)\n");
        sb.append(");\n");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
