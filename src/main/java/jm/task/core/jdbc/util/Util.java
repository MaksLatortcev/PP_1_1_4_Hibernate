package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String connectionUrl;
    private final String userName;
    private final String password;

    public Util() throws ClassNotFoundException {
        connectionUrl = "jdbc:mysql://localhost:3306/user_113";
        userName = "kata";
        password = "123";
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        connection.setAutoCommit(false);
        return connection;
    }
}
