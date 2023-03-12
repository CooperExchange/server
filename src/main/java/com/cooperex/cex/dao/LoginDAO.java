package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import com.cooperex.cex.model.Login;
import java.sql.ResultSet;
import java.sql.*;

public class LoginDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public LoginDAO() {
        System.out.println("LoginDAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    public String getUserIdByEmailAndPassword(Login login) {
        String SQL = "SELECT user_id, username, pass_word " +
                "FROM accounts WHERE email=? and pass_word=?";
        String userId = null;
        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setString(1, login.getEmail());
            statement.setString(2, login.getPassword());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userId = Long.toString(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}

