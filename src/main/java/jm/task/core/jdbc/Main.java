package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Пётр", "Петров", (byte) 22);
        System.out.println("User с именем – Пётр добавлен в базу данных");
        userService.saveUser("Иван", "Иванов", (byte) 33);
        System.out.println("User с именем – Иван добавлен в базу данных");
        userService.saveUser("Семён", "Семёнов", (byte) 44);
        System.out.println("User с именем – Семён добавлен в базу данных");
        userService.saveUser("Григорий", "Григорьев", (byte) 55);
        System.out.println("User с именем – Григорий добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
