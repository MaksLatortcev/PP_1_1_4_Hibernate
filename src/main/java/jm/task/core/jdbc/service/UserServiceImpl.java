package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final Connection connection;

    public UserServiceImpl() {
        try {
            Util util = new Util();
            connection = util.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT," + " name VARCHAR(50)," + " lastName VARCHAR(50)," + " age TINYINT)");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("DROP TABLE users");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String SQL = "INSERT INTO users VALUES (0, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String SQL = "DELETE FROM developers WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = connection.createStatement();
        String SQL = "SELECT * FROM users";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(SQL);
            connection.commit();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong(1));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate("TRUNCATE TABLE users");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
            connection.rollback();
        }
    }
}
