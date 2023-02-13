package cooperexchange;

import java.sql.*;


public class AccountDAO {
    public static void testAccountDAO(Connection connection) {
        System.out.println("Testing Print AccountDAO");
        System.out.println(connection);

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM accounts");

            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

//          Update username
            String SQL = "UPDATE accounts "
                    + "SET username = ? "
                    + "WHERE user_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, "bobthedev");
            pstmt.setInt(2, 101);
            int affectedrows = 0;
            affectedrows = pstmt.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Statement and Connection successfully closed");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}