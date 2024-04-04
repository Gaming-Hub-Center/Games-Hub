package com.gameshub.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.gameshub.model.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@EnableConfigurationProperties
@Component
public class JWTGenerator {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public String createToken(UserDAO userDAO) {
        return JWT.create()
            .withSubject(String.valueOf(userDAO.getEmail()))
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
            .withClaim("role", userDAO.getRole())
            .sign(Algorithm.HMAC256(secretKey));
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token).getSubject();
    }

}
