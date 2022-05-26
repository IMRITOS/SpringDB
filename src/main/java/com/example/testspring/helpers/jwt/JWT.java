package com.example.testspring.helpers.jwt;

import com.example.testspring.Models.USER;
import com.example.testspring.Validators.ValidateHelpers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JWT {
    public String generateToken(Map<String, Object> claims) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getClaimFromToken(String token, String claim) {
        Map<String, Object> claims = getAllClaimsFromToken(token);
        return claims.get(claim).toString();
    }

    public boolean validatePassword(String token){
        ValidateHelpers helper = new ValidateHelpers();
        int id = Integer.valueOf(getClaimFromToken(token, "Id"));
        String password = getClaimFromToken(token, "Password");
        USER user = helper.select(id);

        if(user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateToken(@NonNull String token) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("TOKEN ERROR");
        }
        return false;
    }
}
