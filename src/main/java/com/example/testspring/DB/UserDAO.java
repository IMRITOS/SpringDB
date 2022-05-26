package com.example.testspring.DB;

import com.example.testspring.Models.USER;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<USER> indexUser() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(USER.class));
    }

    public String enter(String login, String password, String checkingWord, JWT jwt) {
        ArrayList<USER> users = (ArrayList) jdbcTemplate.query("SELECT id, Login, Password, Name FROM users", new BeanPropertyRowMapper<>(USER.class));
        Map<String, Object> claims = new HashMap<>();
        boolean checkingLogin;
        boolean checkingPassword;

        for (USER user : users) {
            checkingLogin = user.getLogin().equals(login);
            checkingPassword = user.getPassword().equals(password);

            if (checkingLogin == true) {

                if (checkingPassword == true) {
                    claims.put("Id", user.getId());
                    claims.put("Password", user.getPassword());
                    String token = jwt.generateToken(claims);
                    return user.getLogin() + " successfully logged in" + "\n"
                            + "Token: " + token;
                } else {
                    checkingWord = "Password Error";
                    return checkingWord;
                }
            }
        }

        checkingWord = "Login Error";
        return checkingWord;
    }

    public USER register(USER user, String login) {
        jdbcTemplate.update("INSERT INTO users (Login, Password, Name) VALUES(?, ?, ?)", user.getLogin(), user.getPassword(), user.getName());
        return jdbcTemplate.query("SELECT * FROM users WHERE Login=?", new Object[]{login}, new BeanPropertyRowMapper<>(USER.class))
                .stream().findAny().orElse(null);
    }

    public USER showUser(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(USER.class))
                .stream().findAny().orElse(null);
    }

    public void updateUser(int id, String password, String name, String condition) {
        if(condition.equals("only passwords")){
            jdbcTemplate.update("UPDATE users SET Password=? WHERE id=?", password, id);
        }
        else if(condition.equals("only name")){
            jdbcTemplate.update("UPDATE users SET Name=? WHERE id=?", name, id);
        }
        else{
            jdbcTemplate.update("UPDATE users SET Password=?, Name=? WHERE id=?", password, name, id);
        }
    }
}
