package com.lsc.users.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public static final String TOKEN_SECRET = "6989595117";

    public String createToken(ObjectId userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("userId", userId.toString())
                    .withClaim("createdAt", new Date())
                    .sign(algorithm);
            return token;
        } catch (Exception exception) {
        }
        return null;
    }
}