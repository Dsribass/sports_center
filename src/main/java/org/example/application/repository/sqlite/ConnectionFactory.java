package org.example.application.repository.sqlite;
import java.sql.*;
import org.sqlite.SQLiteDataSource;

public class ConnectionFactory implements AutoCloseable{
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection connect(){
        try {
            instantiateConnectionIfNull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void instantiateConnectionIfNull() throws SQLException {
        if(connection == null){
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:database.db");
            connection = dataSource.getConnection();
        }
    }

    public static PreparedStatement createPreparedStatement(String sql) {
        try{
            preparedStatement = connect().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Statement createStatement() {
        try{
            statement = connect().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void close() throws Exception {
        closeStatements();
        closeConnection();
    }

    private void closeConnection() throws SQLException {
        if(connection != null) connection.close();
    }

    private void closeStatements() throws SQLException {
        if(statement != null) statement.close();
        if(preparedStatement != null) preparedStatement.close();
    }
}
