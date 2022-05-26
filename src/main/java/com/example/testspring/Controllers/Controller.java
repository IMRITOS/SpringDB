package com.example.testspring.Controllers;

import com.example.testspring.DB.DAO;
import com.example.testspring.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/test")
public class Controller {
    private final DAO dao;

    @Autowired
    public Controller(DAO dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public ResponseEntity test(){
        List<Article> list = dao.index();
        return ResponseEntity.ok(list);
    }
}
