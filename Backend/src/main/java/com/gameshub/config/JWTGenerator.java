package com.gameshub.config;

import com.auth0.jwt.*;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import com.gameshub.exception.*;
import com.gameshub.model.user.*;
import com.gameshub.service.user.UserService;
import jakarta.annotation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@EnableConfigurationProperties
@Component
public class JWTGenerator {

    private final UserService userService;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ConfigConstants.JWT_EXPIRATION);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        UserDAO userDAO = userService.getByEmail(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(userDAO, null, Collections.emptyList());
    }

}
