package ajt.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcPriceChangeQuery {
    Connection con;
    Statement stmt;
    ResultSet result;

    jdbcQueries queries = new jdbcQueries();
    public jdbcPriceChangeQuery() {
        try {
            Connection con = jdbcConnectionProvider.getConnection();

            // Write the PL/SQL block to perform price changes
            String q = queries.changeBookPrice;

            // Prepare and execute the PL/SQL block
            Statement stmt = con.createStatement();
            stmt.executeUpdate(q);
            System.out.println("Price Changed Successfully!");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Price not changed!");
        }
    }
}
