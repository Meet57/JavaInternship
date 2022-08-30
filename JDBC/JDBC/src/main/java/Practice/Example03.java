package Practice;

import java.sql.*;

public class Example03 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practice", "root", "password");

            String query = "insert into practice values(?,?,?);";

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1,32);
            stmt.setString(2,"Dhruv");
            stmt.setString(3,"Patel");

            int count = stmt.executeUpdate();

            System.out.println(count);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
