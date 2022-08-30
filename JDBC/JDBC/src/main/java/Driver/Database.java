package Driver;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    Database(String url,String user,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,password);
    }

    public ArrayList<String> showDbs() throws SQLException {
        stmt= con.createStatement();
        rs = stmt.executeQuery("show databases;");

        ArrayList<String> output = new ArrayList<>();
        while(rs.next()){
            output.add(rs.getString(1));
        }
        return output;
    }

    public void close() throws SQLException {
        con.close();
    }
}
