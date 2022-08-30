package Practice;

import java.sql.*;

public class CommitRollBack {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practice", "root", "password");

            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("insert into practice values(?,?,?)");

            for (int i = 65; i <= 90; i++) {
                pst.setInt(1, i);
                pst.setString(2, String.valueOf((char) i));
                pst.setString(3, String.valueOf((char) i));
                if (i == 75) throw new RuntimeException("Manually Breaked the batch");

                pst.executeUpdate();
            }
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();
                    con.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
