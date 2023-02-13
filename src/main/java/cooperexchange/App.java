package cooperexchange;

import cooperexchange.AccountDAO;
import java.sql.*;
public class App
{
    public static void main(String args[]) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cooper_exchange",
                            "postgres", "password");
            System.out.println("Successfully connected to the local database");

            AccountDAO accountDAO = new AccountDAO();
            accountDAO.testAccountDAO(connection);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
}
