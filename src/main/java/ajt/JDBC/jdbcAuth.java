package ajt.JDBC;

import java.sql.*;

public class jdbcAuth {
    Connection con = jdbcConnectionProvider.getConnection();
    Statement stmt;
    ResultSet result;
    jdbcQueries queries = new jdbcQueries();

    public boolean checkEmployee(String username, String password) {
    try (PreparedStatement preparedStatement = con.prepareStatement(
            "SELECT * FROM library_employees WHERE username = ? AND password = ?")) {

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // A record was found, so the username and password match an employee
                return true;
            } else {
                // No record was found, so the username and password do not match any employee
                return false;
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
