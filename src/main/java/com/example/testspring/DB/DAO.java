package com.example.testspring.DB;

import com.example.testspring.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> index() {
        return jdbcTemplate.query("SELECT * FROM articles", new BeanPropertyRowMapper<>(Article.class));
    }
}
