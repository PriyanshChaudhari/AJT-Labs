package ajt.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcCreateTableQuery {
    Connection con;
    Statement stmt;
    ResultSet result;

    jdbcQueries queries = new jdbcQueries();
    public jdbcCreateTableQuery(){
            try {
                con = jdbcConnectionProvider.getConnection();
                String q = queries.createTable;
                stmt = con.createStatement();
                stmt.executeUpdate(q);
                    System.out.println("Table Created Successfully!");
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Table not Created!");
            }
        }
    }
