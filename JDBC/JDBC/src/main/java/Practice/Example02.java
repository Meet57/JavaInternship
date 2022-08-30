package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example02 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practice", "root", "password");
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate("insert into practice values(30,'MEET','PATEL')");

            System.out.println(rs + " Rows affected");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
