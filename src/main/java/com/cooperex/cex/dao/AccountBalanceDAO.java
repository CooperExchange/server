package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.DatabaseSQLExecutor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountBalanceDAO {
    private DatabaseSQLExecutor databaseSQLExecutor;
    private Connection connection;

    public AccountBalanceDAO() {
        System.out.println("AccountBalanceDAO object has been initialized with successful DB connection!");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        DatabaseSQLExecutor databaseSQLExecutor = new DatabaseSQLExecutor(connection);
        this.databaseSQLExecutor = databaseSQLExecutor;
        this.connection = connection;
    }

    // To-do: Implement error handling if there is no user
    public String addBalanceById(String userId, Double amount) {
        System.out.println("Deposit func called");
        String SQL = "UPDATE accounts SET total_deposit " +
                "= total_deposit + ?,  remaining_cash = remaining_cash + ? where user_id = ?;";

        int userIdInt = Integer.parseInt(userId);

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            statement.setDouble(1, amount);
            statement.setDouble(2, amount);
            statement.setInt(3, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User deposit has been increased by " + amount.toString();
    }

    // To-do: Prevent withdrawal if total_deposit > total_withdrawal
    public String withdrawBalanceById(String userId, Double amount) {

        // Check user remaining cash
        String GET_REMAINING_CASH = "SELECT remaining_cash " +
                "FROM accounts WHERE user_id=?";

        double remainingCash = 0;

        try (PreparedStatement statement = this.connection.prepareStatement(GET_REMAINING_CASH);) {
            statement.setInt(1, Integer.parseInt(userId));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                remainingCash = rs.getDouble("remaining_cash");
                System.out.println(remainingCash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Prevent over withdrawal
        if (remainingCash < amount) {
            return "User failed to withdraw. Please check the remaining balance.";
        }

        String UPDATE_REMAINING_CASH = "UPDATE accounts SET total_withdrawal " +
                "= total_withdrawal + ?, remaining_cash = remaining_cash - ? where user_id = ?";

        int userIdInt = Integer.parseInt(userId);
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_REMAINING_CASH);) {
            statement.setDouble(1, amount);
            statement.setDouble(2, amount);
            statement.setInt(3, userIdInt);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User withdrawal has been increased by " + amount.toString();

    }

}



