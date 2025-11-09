package com.yo.GestPro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenJwt {

    // The secret key must be stored in an environment variable for production
    private final String secret = "123456";

    public String generateTokenJwt(String client){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("GestPro")
                    .withSubject(client)
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException("Error generating JWT token", e);
        }
    }


    public String getSubject(String tokenJwt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("GestPro")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();

        }catch (JWTDecodeException e){
            throw new RuntimeException("Invalid or expired JWT token", e);
        }

    }

    private Instant expirationDate() {
        return Instant.now().plus(Duration.ofHours(2));
    }
}
