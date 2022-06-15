package com.example.testspring.Controllers;

import com.example.testspring.DAO.UserDAO;
import com.example.testspring.Models.USER;
import com.example.testspring.Validators.user.ValidateUser;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;
    private final ValidateUser validateUser;

    @Autowired
    public UsersController(UserDAO userDAO, ValidateUser validateUser) {
        this.userDAO = userDAO;
        this.validateUser = validateUser;
    }

    @GetMapping("/showUser")
    public ResponseEntity showUser(HttpServletRequest request, HttpServletResponse response) {
        JWT jwt = new JWT();
        String token = request.getHeader("Authourization");
        Map<String, Object> claims = jwt.getAllClaimsFromToken(token);
        String id = claims.get("Id").toString();
        USER user = userDAO.showUser(Integer.valueOf(id));

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/editUser")
    public ResponseEntity updateUser(@RequestBody Map<String, Object> values, HttpServletRequest request) {
        JWT jwt = new JWT();
        String header = request.getHeader("Authourization");

        if (jwt.validateToken(header) == false) {
            return ResponseEntity.ok("You don't have access");
        }

        int id = Integer.valueOf(jwt.getClaimFromToken(header, "Id"));
        String condition = "";
        String name="";
        String oldPassword="";
        String newPassword="";

        String editError = validateUser.checkEdit(values);
        if(editError.equals("no passwords")){
            name = values.get("name").toString();
            String nameError = validateUser.checkName(name);
            if (!nameError.equals("")) {
                return ResponseEntity.ok(nameError);
            }
            condition="only name";

        }
        else if(editError.equals("no name")){
            oldPassword = values.get("old password").toString();
            newPassword = values.get("new password").toString();
            String oldPasswordError = validateUser.checkOldPassword(oldPassword, id);
            String newPasswordError = validateUser.checkNewPassword(newPassword, id);
            if(!oldPasswordError.equals("")){
                return ResponseEntity.ok(oldPasswordError);
            }
            if(!newPasswordError.equals("")){
                return ResponseEntity.ok(newPasswordError);
            }
            condition="only passwords";
        }
        else{
            name = values.get("name").toString();
            oldPassword = values.get("old password").toString();
            newPassword = values.get("new password").toString();

            String passwordError = validateUser.checkOldPassword(oldPassword, id);
            if(!passwordError.equals("")){
                return ResponseEntity.ok(passwordError);
            }
            if(oldPassword.equals(newPassword)){
                return ResponseEntity.ok("New password should not be match old password");
            }
            String nameError = validateUser.checkName(name);
            if (!nameError.equals("")) {
                return ResponseEntity.ok(nameError);
            }

            condition = "";
        }

        userDAO.updateUser(id, newPassword, name, condition);
        return ResponseEntity.ok("Success update");
    }
}
