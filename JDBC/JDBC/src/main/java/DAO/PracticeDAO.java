package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PracticeDAO {
    private Connection con;
    String query;

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practice", "root", "password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int updatedeleteinsert(string query){
        query.fire
        get int value
        return int
    }

    public list(hashmap) select(string query){
        query.fire
        list(each row as hashmap)
        return list
    }

    public void addStudent(Practice p){
        connect();
        query = "insert into practice values (?,?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,p.id);
            pst.setString(2,p.first_name);
            pst.setString(3,p.last_name);

            int result = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudents(ArrayList<Practice> data){
        connect();
        query = "insert into practice values (?,?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            for (Practice p: data) {
                pst.setInt(1,p.id);
                pst.setString(2,p.first_name);
                pst.setString(3,p.last_name);
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Practice getStudent(int id) {
        connect();
        Practice student = new Practice();
        try {
            query = "select * from practice where id =" + id + ";";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(quer   y);

            rs.next();

            student.id = rs.getInt(1);
            student.first_name = rs.getString(2);
            student.last_name = rs.getString(3);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return student;
    }

    public void removeStudent(int id) throws SQLException {
        connect();
        query = "delete from practice where id =" + id + ";";

        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
    }
}
