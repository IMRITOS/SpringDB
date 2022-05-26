package com.example.testspring.Validators.user;

import com.example.testspring.Models.USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateUser {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ValidateUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String checkLogin(String login) {
        ArrayList<USER> users = (ArrayList) jdbcTemplate.query("SELECT Login FROM users", new BeanPropertyRowMapper<>(USER.class));
        boolean checkingLogin;
        boolean isEmpty = login.isEmpty();
        int loginLength = login.length();
        Pattern pattern = Pattern.compile("\\w+[-,.]*");
        Matcher matcher = pattern.matcher(login);
        boolean loginPattern = matcher.matches();

        if (isEmpty == true) {
            return "Login should not be empty";
        }
        if (loginLength < 5 || loginLength > 12) {
            return "Login should be between 5 and 12 characters";
        }
        if (loginPattern == false) {
            return "Login should contain only latin letters, hyphens, underscores and dots";
        }

        for (USER user : users) {
            checkingLogin = user.getLogin().equals(login);

            if (checkingLogin == true) {
                return "This login already exists";
            }
        }

        return "";
    }

    public String checkPassword(String password) {
        boolean isEmpty = password.isEmpty();
        int passwordLength = password.length();

        if (isEmpty == true) {
            return "Password should not be empty";
        }
        if (passwordLength < 5 || passwordLength > 12) {
            return "Password should be between 5 and 12 characters";
        }
        return "";
    }

    public String checkOldPassword(String oldPassword, int id) {
        USER user = jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(USER.class))
                .stream().findAny().orElse(null);
        boolean isEmpty = oldPassword.isEmpty();

        if (isEmpty == true) {
            return "Old password should not be empty";
        }
        if (!oldPassword.equals(user.getPassword())) {
            return "Old password does not match";
        }

        return "";
    }

    public String checkNewPassword(String newPassword, int id) {
        USER user = jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(USER.class))
                .stream().findAny().orElse(null);
        boolean isEmpty = newPassword.isEmpty();

        if (isEmpty == true) {
            return "New password should not be empty";
        }
        if (!newPassword.equals(user.getPassword())) {
            return "New password must not match the old one.";
        }

        return "";
    }

    public String checkName(String name) {
        boolean isEmpty = name.isEmpty();
        int nameLength = name.length();
        Pattern pattern = Pattern.compile("[A-z]*[А-я]*[0-9]*");
        Matcher matcher = pattern.matcher(name);
        boolean namePattern = matcher.matches();

        if (isEmpty == true) {
            return "Name should not be empty";
        }
        if (nameLength < 1 || nameLength > 20) {
            return "Name should be between 1 and 20 characters";
        }
        if (namePattern == false) {
            return "Name should contain only words and numbers";
        }

        return "";
    }

    public String checkEdit(Map<String, Object> values) {
        if (values.get("old password") == null & values.get("new password") == null & values.get("name").toString().isEmpty() == false) {
            return "no passwords";
        }
        if (values.get("old password") == null & values.get("new password").toString().isEmpty() == false) {
            return "no old password";
        }
        if (values.get("new password") == null & values.get("old password").toString().isEmpty() == false) {
            return "no new password";
        }

        return "";
    }
}
