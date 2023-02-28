/*
ECE-366: CooperExchange
Description: Instantiates DatabaseConnectionManager(dcm) to form database connection and implements various DAOs.
 */

package CooperExchangeJDBC;

import CooperExchangeJDBC.Util.Account.Account;
import CooperExchangeJDBC.Util.Account.AccountDAO;

import java.sql.Connection; //Connection Interface: allows connection with specific database
import java.sql.ResultSet; //ResultSet Interface: table of data representing database result set (usually generated from query)
import java.sql.SQLException; //SQLException Exception: provides information on database access error or other errors
import java.sql.Statement; //Statement Interface: executes static SQL statement and returns result

public class JDBCExecutor {
    public static void main (String... args){
        //Instantiate dcm - parameters subject to change
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "cooper_exchange", "postgres", "password");

        try{
            Connection connection = dcm.getConnection(); //connection with database through DCM

            //Various DAO implementations - subject to change
            AccountDAO accountDAO = new AccountDAO(connection);
            Account account = accountDAO.findById(1);
            System.out.println(account);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
