package com.uca.security;

import com.uca.core.ProfCore;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

public class doLogin {

    // Note : In real life, this line should be (and must be !) in a configuration file, not in source code !
    private final static String TOKEN = "QVAlKTzo1zW9VwfGvJtrFZiSOzzEzEyb4Q4qdYIYncKqhd4l7Iasgq8LbesvH01Jk8kA49HNt9fq4M4Lpjpjvysyso7egZNlmHSU";

    public static ProfEntity introspect(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {

            Claims claims = Jwts.parser().setSigningKey(TOKEN).parseClaimsJws(token).getBody();

            ProfEntity entity = new ProfEntity();
            entity.setIdentifier(claims.get("emitter", String.class));
            entity.setId(claims.get("uuid", Integer.class));

            return entity;
        } catch (Exception e) {
            return null;
        }
    }


    public static String login(ProfEntity entity) throws IllegalArgumentException {
        if (entity.getIdentifier() == null || entity.getIdentifier().isEmpty()) {
            throw new IllegalArgumentException("name could not be null");
        }
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new IllegalArgumentException("password could not be null");
        }

        ProfEntity user;
        try {
            user = ProfCore.getByIdentifier(entity.getIdentifier(), true);
        } catch (ServiceException e){
            throw new IllegalArgumentException("User does not exists.");
        }

        if (!BCrypt.checkpw(entity.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        //Could connect as application
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", user.getId());
        map.put("id", user.getId());
        map.put("emitter", user.getIdentifier());

        Map<String, Object> header = new HashMap<>();
        header.put("kid", user.getId());

        //Generating user token for required service

        return Jwts.builder()
                .setClaims(map)
                .setId(UUID.randomUUID().toString())
                .setHeader(header)
                .setHeaderParam("kid", user.getId())
                .setSubject(user.getIdentifier())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 20))
                .signWith(SignatureAlgorithm.HS512, TOKEN)
                .compact();
    }
}
