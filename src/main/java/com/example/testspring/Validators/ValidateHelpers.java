package com.example.testspring.Validators;

import com.example.testspring.Models.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidateHelpers {
    private final String url = "jdbc:mysql://localhost/articledb?serverTimezone=Europe/Moscow&useSSL=false";
    private final String username = "root";
    private final String password = "12345";

    public USER select(int id) {
        USER user = new USER();
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommand = "SELECT * FROM users WHERE Id=1";

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                PreparedStatement preparedSt = conn.prepareStatement("SELECT * FROM users WHERE Id=?");
                preparedSt.setInt(1, id);
                ResultSet resultSet = preparedSt.executeQuery(sqlCommand);

                while (resultSet.next()) {

                    int getId = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String name = resultSet.getString(4);

                    user.setId(getId);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setName(name);

                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
