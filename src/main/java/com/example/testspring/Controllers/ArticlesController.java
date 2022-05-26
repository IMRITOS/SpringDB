package com.example.testspring.Controllers;

import com.example.testspring.DB.ArticleDAO;
import com.example.testspring.Models.Article;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleDAO articleDAO;

    @Autowired
    public ArticlesController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping("")
    public ResponseEntity index() {
        try {
            return ResponseEntity.ok(articleDAO.index());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/show")
    public ResponseEntity show(HttpServletRequest request) {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Article article = articleDAO.show(id);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/new")
    public ResponseEntity create(@RequestBody Article article) {
        try {
            articleDAO.save(article);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }

    }

    @PatchMapping("/edit")
    public ResponseEntity update(@RequestBody Article article, HttpServletRequest request) {
        JWT jwt = new JWT();
        String header = request.getHeader("Authourization");

        if (jwt.validateToken(header) == false) {
            return ResponseEntity.ok("You don't have access");
        }

        articleDAO.update(article.getId(), article);
        return ResponseEntity.ok(article.getTitle() + " update success");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") int id, HttpServletRequest request) {
        JWT jwt = new JWT();
        String header = request.getHeader("Authourization");

        if (jwt.validateToken(header) == false) {
            return ResponseEntity.ok("You don't have access");
        }

        articleDAO.delete(id);
        return ResponseEntity.ok("Success deleted");
    }
}
