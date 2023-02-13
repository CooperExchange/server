package cooperexchange;

import cooperexchange.Account;
import java.sql.*;
public class App {
    private Connection connection;
    public static void main(String args[]) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cooper_exchange",
                            "postgres", "password");
            System.out.println("Successfully connected to the local database");

            Account account = new Account("bobleesj", "abc123");

            // READ
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM accounts");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)); // count rows
            }

            // UPDATE USERNAME
            String SQL = "UPDATE accounts "
                    + "SET username = ? "
                    + "WHERE user_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, "bobleesj");
            pstmt.setInt(2, 101);
            int affectedrows = 0;
            affectedrows = pstmt.executeUpdate();

            // INSERT: Create an account
            String insertSQL = "INSERT INTO accounts (user_id, first_name, last_name, username, pass_word, email) "
                    + "VALUES (106,'Paul', 'Kim', 'PaulKing', 'abc123', 'paul.kim123@cooper.edu');";
            statement.executeUpdate(insertSQL);

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
