package com.example.testspring.Validators;

import org.springframework.jdbc.core.JdbcTemplate;

public class Validation {
    private final JdbcTemplate jdbcTemplate;

    public Validation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static String checkMethod(String methodType) {
        if (methodType.equals("GET") || methodType.equals("POST")) {
            return "/errors/getError";
        } else if (methodType.equals("PATCH")) {
            return "/errors/patchError";
        } else if (methodType.equals("DELETE")) {
            return "/errors/deleteError";
        }

        return "";
    }
}
