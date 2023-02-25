/*
ECE-366: CooperExchange
Description: DAO for "accounts" table.
 */

package CooperExchangeJDBC.Util.Account;

import CooperExchangeJDBC.Util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DataAccessObject {

    //Constructor
    public AccountDAO (Connection connection){
        super(connection);
    }

    //SQL queries
    private static final String GET_ONE_BY_ID = "SELECT user_id, first_name, last_name, username, pass_word, email, num_of_trades, total_deposit, total_withdrawn, current_bal, net_profit FROM accounts WHERE user_id=?";
    private static final String GET_ONE_BY_USERNAME = "SELECT user_id, first_name, last_name, username, pass_word, email, num_of_trades, total_deposit, total_withdrawn, current_bal, net_profit FROM accounts WHERE username=?";
    private static final String INSERT_ACCOUNT = "INSERT INTO accounts (first_name, last_name, username, pass_word, email) VALUES (?, ?, ?, ?, ?)";

    //Methods to execute SQL queries and return desired information as attributes of an Account object
    public Account findById(long id){
        Account account = new Account();
        System.out.println(GET_ONE_BY_ID);

        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_BY_ID);){
            statement.setLong(1, id); //setting '?' as id in GET_ONE_BY_ID query
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                //Set account object attributes based on database query return
                account.setUserID(rs.getLong("user_id"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("pass_word"));
                account.setEmail(rs.getString("email"));
                account.setNumOfTrades(rs.getInt("num_of_trades"));
                account.setTotalDeposit(rs.getDouble("total_deposit"));
                account.setTotalWithdrawn(rs.getDouble("total_withdrawn"));
                account.setCurrentBalance(rs.getDouble("current_bal"));
                account.setNetProfit(rs.getDouble("net_profit"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return account;
    }

    public Account findByUsername(String username){
        Account account = new Account();
        System.out.println(GET_ONE_BY_USERNAME);

        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE_BY_USERNAME);){
            statement.setString(1, username); //setting '?' as id in GET_ONE_BY_USERNAME query
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                //Set account object attributes based on database query return
                account.setUserID(rs.getLong("user_id"));
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("pass_word"));
                account.setEmail(rs.getString("email"));
                account.setNumOfTrades(rs.getInt("num_of_trades"));
                account.setTotalDeposit(rs.getDouble("total_deposit"));
                account.setTotalWithdrawn(rs.getDouble("total_withdrawn"));
                account.setCurrentBalance(rs.getDouble("current_bal"));
                account.setNetProfit(rs.getDouble("net_profit"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return account;
    }


}
