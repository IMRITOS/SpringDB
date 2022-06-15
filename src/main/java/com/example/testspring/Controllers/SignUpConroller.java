package com.example.testspring.Controllers;

import com.example.testspring.DAO.UserDAO;
import com.example.testspring.Models.USER;
import com.example.testspring.Validators.user.ValidateUser;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/signUp")
public class SignUpConroller {
    private final UserDAO userDAO;
    private final ValidateUser validateUser;

    @Autowired
    public SignUpConroller(UserDAO userDAO, ValidateUser validateUser) {
        this.userDAO = userDAO;
        this.validateUser = validateUser;
    }

    @PostMapping("/reg")
    public ResponseEntity register(@RequestBody USER user,
                                   HttpServletResponse response,
                                   Model model) {

        JWT jwt = new JWT();
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();

        String loginError = validateUser.checkLogin(login);
        if (!loginError.equals("")) {
            return ResponseEntity.ok(loginError);
        }

        String passwordError = validateUser.checkPassword(password);
        if (!passwordError.equals("")) {
            return ResponseEntity.ok(passwordError);
        }

        String nameError = validateUser.checkName(name);
        if (!nameError.equals("")) {
            return ResponseEntity.ok(nameError);
        }

        USER returnUser = userDAO.register(user, login);
        Map<String, Object> claims = new HashMap<>();
        claims.put("Id", returnUser.getId());
        String token = jwt.generateToken(claims);

        return ResponseEntity.ok(returnUser.getLogin() + " registrated" + "\n"
                + "Token: " + token);
    }
}
