package usermanagment.dao;

/*
 * CRUD database operations for user
 * */

import usermanagment.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/todo";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "password";

    private static final String INSERT_USERS_SQL = "INSERT INTO users VALUES (?, ?, ?);";
    private static final String SELECT_ALL_USERS = "select * from users;";
    private static final String SELECT_USER_BY_ID = "select * from users where id = ?;";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ? , email = ? , country = ? where id = ?;";

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

    public void insertUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("email"), rs.getString("country"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ArrayList<User> selectAllUsers() {
        ArrayList<User> Users = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);

            while (rs.next()) {
                Users.add(new User(rs.getString("name"), rs.getString("email"), rs.getString("country")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Users;
    }

    public Boolean deleteUser(int id) {

        Boolean rs = null;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}