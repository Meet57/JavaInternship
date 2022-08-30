package Practice;

import java.sql.*;

public class Example01 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practice", "root", "password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from practice");
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

