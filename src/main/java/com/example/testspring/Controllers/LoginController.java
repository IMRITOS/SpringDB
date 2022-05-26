package com.example.testspring.Controllers;

import com.example.testspring.DB.UserDAO;
import com.example.testspring.Models.USER;
import com.example.testspring.Validators.user.ValidateUser;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/log")
public class LoginController {
    private final UserDAO userDAO;
    private final ValidateUser validateUser;

    @Autowired
    public LoginController(UserDAO userDAO, ValidateUser validateUser) {
        this.userDAO = userDAO;
        this.validateUser = validateUser;
    }

    @PostMapping("/enter")
    public ResponseEntity authourization(@RequestBody USER user,
                                         HttpServletResponse response, HttpServletRequest request) {
        String login = user.getLogin();
        String password = user.getPassword();
        String checkingWord = "";
        JWT jwt = new JWT();
        checkingWord = userDAO.enter(login, password, checkingWord, jwt);

        if (checkingWord.equals("Login Error")) {
            return ResponseEntity.ok("Login entered incorrectly");
        } else if (checkingWord.equals("Password Error")) {
            return ResponseEntity.ok("Password entered incorrectly");
        } else {
            return ResponseEntity.ok(checkingWord);
        }
    }
}
