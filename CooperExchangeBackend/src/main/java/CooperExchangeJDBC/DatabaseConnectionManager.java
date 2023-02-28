/*
ECE-366: CooperExchange
Description: Class that allows connection to form with provided database.
 */

package CooperExchangeJDBC;

//java.sql package = Provides the API for accessing and processing data stored in a data source (usually a relational database) using the JavaTM programming language.
import java.sql.Connection; //Connection Interface: allows connection with specific database
import java.sql.DriverManager; //DriverManager Class: basic service for managing JDBC drivers
import java.sql.SQLException; //SQLException Exception: provides information on database access error or other errors
import java.util.Properties; //Properties Class: set of keys and corresponding values (as strings)

public class DatabaseConnectionManager {
    //private attributes
    private final String url;
    private final Properties properties;

    //constructor
    public DatabaseConnectionManager(String host, String databaseName, String username, String password) {
        this.url = "jdbc:postgresql://" + host + "/" + databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    //forms database connection with provided database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}
