package com.cooperex.cex;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseExecutor {

    public DatabaseExecutor() {}

    public Connection connect(){
        try {
            DatabaseConnectionManager dcm = new DatabaseConnectionManager("db",
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
