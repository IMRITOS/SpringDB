package com.example.testspring.DAO;

import com.example.testspring.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> index() {
        return jdbcTemplate.query("SELECT * FROM articles", new BeanPropertyRowMapper<>(Article.class));
    }

    public Article show(int id) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Article.class))
                .stream().findAny().orElse(null);
    }

    public void save(Article article) {
        jdbcTemplate.update("INSERT INTO articles (title, text, authour) VALUES(?, ?, ?)", article.getTitle(), article.getText(), article.getAuthour());
    }

    public void update(int id, Article updateArticle) {
        jdbcTemplate.update("UPDATE articles SET title=?, text=?, authour=? WHERE id=?", updateArticle.getTitle(),
                updateArticle.getText(), updateArticle.getAuthour(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM articles WHERE id=?", id);
    }
}
