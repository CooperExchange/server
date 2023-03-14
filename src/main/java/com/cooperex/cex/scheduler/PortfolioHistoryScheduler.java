package com.cooperex.cex.scheduler;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.dao.AccountPortfolioHistoryDAO;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import com.cooperex.cex.model.Asset;
import com.cooperex.cex.model.Trade;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PortfolioHistoryScheduler {
    private Connection connection;
    public PortfolioHistoryScheduler() {
        System.out.println("Portfolio History Scheduler object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public Boolean savePortfolioValue() {
        AccountPortfolioHistoryDAO accountPortfolioHistoryDAO = new AccountPortfolioHistoryDAO();
        AccountPortfolioDAO accountPortfolioDAO = new AccountPortfolioDAO();

        // Access all user_ids from the ACCOUNTS table
        ArrayList<Integer> userIdArrayList = new ArrayList<Integer>();
        String SQL = "SELECT user_id " +
                "FROM accounts";

        try (PreparedStatement statement = this.connection.prepareStatement(SQL);) {
            ;
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {;
                userIdArrayList.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < userIdArrayList.size(); i++) {
            String userId = String.valueOf(userIdArrayList.get(i));
            accountPortfolioHistoryDAO.savePortfolioValueById(userId);
        }

        System.out.println("Portfolio values for all users have been saved.");
        return true;
    }
}
