package com.gameshub.Security;

import com.auth0.jwt.*;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import com.gameshub.Model.User.DAO.*;
import com.gameshub.Service.*;
import lombok.*;
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

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + SecurityConstants.JWT_EXPIRATION);

        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET_KEY);

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET_KEY);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDAO userDAO = userService.getByEmail(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(userDAO, null, Collections.emptyList());
    }

}