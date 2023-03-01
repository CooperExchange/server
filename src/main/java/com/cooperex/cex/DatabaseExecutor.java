package com.cooperex.cex;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseExecutor {

    public DatabaseExecutor() {}

    public Connection connect(){
        // Set host to "db" if Docker compose is used
        // Set host to "localhost" if a regular Docker container is used
        try {
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                    "cooper_exchange", "postgres", "password");
            Connection connection = dcm.getConnection();
            System.out.println("The database has been successfully connected");
            return connection;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
