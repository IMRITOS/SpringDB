package com.example.testspring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/test")
public class Controller {


    public Controller(){


    }

    @GetMapping("")
    public ResponseEntity test(){
        return ResponseEntity.ok("Test success");
    }
}
