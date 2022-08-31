package Todo;

import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/todo";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "password";

    private static final String INSERT_TODO_SQL = "INSERT INTO todo (task) VALUES (?);";
    private static final String SELECT_ALL_TODO = "select * from todo;";
    private static final String SELECT_TODO_BY_ID = "select * from todo where id = ?;";
    private static final String DELETE_TODO_SQL = "delete from todo where id = ?;";
    private static final String UPDATE_TODO_SQL = "update todo set task = ? where id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void insertTodo(Todo todo) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO_SQL);
            preparedStatement.setString(1, todo.getTask());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTodo(Todo todo) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO_SQL);
            preparedStatement.setString(1, todo.getTask());
            preparedStatement.setInt(2, todo.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Todo selectTodo(int id) {
        Todo todo = null;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                todo = new Todo(rs.getInt("id"), rs.getString("task"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todo;
    }

    public ArrayList<Todo> selectAllUsers() {
        ArrayList<Todo> tasks = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_TODO);

            while (rs.next()) {
                tasks.add(new Todo(rs.getInt("id"), rs.getString("task")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public Boolean deleteTodo(int id) {

        Boolean rs = null;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO_SQL);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

}
