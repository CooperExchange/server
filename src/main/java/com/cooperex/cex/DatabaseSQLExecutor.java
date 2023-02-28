package com.cooperex.cex;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSQLExecutor {
    public Connection connection;

    public DatabaseSQLExecutor(Connection connection) {
        this.connection = connection;
        System.out.println("Database SQL Executor Function Called");
    }

    public String executeSQLStatement(String SQL) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            return "Request Success";
        }
        catch(SQLException e) {
            e.printStackTrace();
            return "Request Fail";
        }
    }
}
