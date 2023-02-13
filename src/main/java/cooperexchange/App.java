
package cooperexchange;

import cooperexchange.Account;
import java.sql.*;
import java.util.Random;


public class App {
    private Connection connection;
    public static void main(String args[]) {

        try {
            // Connect with the database
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cooper_exchange",
                            "postgres", "password");
            Statement statement = connection.createStatement();
            System.out.println("Successfully connected to the local database");

            Account account = new Account("bobleesj", "abc123");

            // INSERT: Create an account
            int upperbound = 1000;
            Random rand = new Random();
            int int_random = rand.nextInt(upperbound);
            System.out.println("x before assigning new Value :  "+int_random+"");
            String randomUsername = "PaulGeorge" + int_random;
            String randomEmail = "PaulGeorge" + int_random + "@cooper.edu";

            String insertSQL = "INSERT INTO accounts (user_id, first_name, last_name, username, pass_word, email) "
                    + "VALUES ("+int_random+",'Paul', 'Kim', '"+randomUsername+"', 'abc123', '"+randomEmail+"');";
            statement.executeUpdate(insertSQL);

            // UPDATE: Update Username
            String updateSQL = "UPDATE accounts "
                    + "SET first_name = ? "
                    + "WHERE user_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(updateSQL);
            pstmt.setString(1, "Bobby");
            pstmt.setInt(2, 101);
            pstmt.executeUpdate();

            // READ:
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM accounts");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)); // count rows
            }

            // Close the connections
            statement.close();
            connection.close();
            System.out.println("Statement and Connection successfully closed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
