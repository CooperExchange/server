package com.cooperex.cex.dao;
import com.cooperex.cex.DatabaseExecutor;
import com.cooperex.cex.dao.AccountPortfolioDAO;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.json.JSONObject;
import com.google.gson.Gson;
public class AccountPortfolioHistoryDAO {
    private Connection connection;

    public AccountPortfolioHistoryDAO() {
        System.out.println("Account Portoflio DAO object has been initialized with successful DB connection");
        DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        Connection connection = databaseExecutor.connect();
        this.connection = connection;
    }

    public String getPortfolioHistoryById(String userId) {

       return "Portfolio History is working";
    }
}



// Bug 1: Must check whether the user exists in the first place