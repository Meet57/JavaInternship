package todo.DAO;

import todo.model.Todo;

import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {
    private static Connection con;

    private static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo", "root", "password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void disconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addTodo(String task) throws SQLException {
        connect();
        PreparedStatement stmt=con.prepareStatement("insert into Todo(task) values (?)");
        stmt.setString(1,task);
        return stmt.executeUpdate()>0;
    }

    public static ArrayList<Todo> getTodos(){
        connect();
        ArrayList<Todo> result = new ArrayList<>();

        try {
            String query = "select * from Todo ;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                result.add(new Todo(rs.getInt(1),rs.getString(2)));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public static boolean removeTodo(int id) throws SQLException {
        connect();
        PreparedStatement stmt=con.prepareStatement("delete from Todo where id = ?");
        stmt.setInt(1,id);
        return stmt.executeUpdate()>0;
    }

    public static boolean updateTodo(int id,String task) throws SQLException {
        connect();
        PreparedStatement pst=con.prepareStatement("update Todo set task = ? where id = ?");
        pst.setString(1,task);
        pst.setInt(2,id);

        return pst.executeUpdate() > 0;
    }
}
