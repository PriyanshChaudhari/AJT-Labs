package IO;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcConnectionProvider {
    private static Connection con;

    public static Connection getConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/testjdbc";
            String username = "root";
            String password = "root@mysql";
            if (con == null) {
                System.out.print("loading driver..." + "\t");
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.print("establishing connection..." + "\t");
                con = DriverManager.getConnection(url, username, password);
                if (con.isClosed())
                    System.out.println("connection is closed!");
                else System.out.println("connected successfully!");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
