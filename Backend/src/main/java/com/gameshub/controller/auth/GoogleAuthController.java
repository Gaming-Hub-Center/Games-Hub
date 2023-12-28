package com.gameshub.controller.auth;

import com.gameshub.config.JWTGenerator;
import com.gameshub.controller.DTO.user.UserDTO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.model.user.UserDAO;
import com.gameshub.service.user.UserService;
import com.gameshub.utils.UserMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.JWK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

// Other import statements...

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean verifyGoogleToken(Map<String, String> token){
        try {
            String idToken = token.get("idToken");
            SignedJWT signedJWT = SignedJWT.parse(idToken);
            String kid = signedJWT.getHeader().getKeyID();
            JWKSet jwkSet = JWKSet.load(new URL("https://www.googleapis.com/oauth2/v3/certs"));
            JWK jwk = jwkSet.getKeyByKeyId(kid);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) jwk.toRSAKey().toPublicKey());
            return signedJWT.verify(verifier);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @PostMapping("/verify-google-signin")
    public ResponseEntity<UserDTO> verifyGoogleSignIn(@RequestBody Map<String, String> requestBody) throws ParseException {
        if(!verifyGoogleToken(requestBody))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        String idToken = requestBody.get("idToken");
        SignedJWT signedJWT = SignedJWT.parse(idToken);
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        String email = jwtClaimsSet.getStringClaim("email");

        String token = jwtGenerator.createToken(email);
        UserDAO userDAO = userService.getByEmail(email);
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/verify-google-signup/buyer")
    public ResponseEntity<UserDTO> verifyGoogleSignUpBuyer(@RequestBody Map<String, String> requestBody) throws ParseException {
        if(!verifyGoogleToken(requestBody))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        String idToken = requestBody.get("idToken");
        SignedJWT signedJWT = SignedJWT.parse(idToken);
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        String name = jwtClaimsSet.getStringClaim("name");
        String email = jwtClaimsSet.getStringClaim("email");

        userService.saveNewBuyer(
                new BuyerDAO(
                        name,
                        email,
                        passwordEncoder.encode(UUID.randomUUID().toString()),
                        "",
                        "",
                        0
                )
        );
        String token = jwtGenerator.createToken(email);
        UserDAO userDAO = userService.getByEmail(email);
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/verify-google-signup/seller")
    public ResponseEntity<UserDTO> verifyGoogleSignUpSeller(@RequestBody Map<String, String> requestBody) throws ParseException {
        if(!verifyGoogleToken(requestBody))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        String idToken = requestBody.get("idToken");
        SignedJWT signedJWT = SignedJWT.parse(idToken);
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        String name = jwtClaimsSet.getStringClaim("name");
        String email = jwtClaimsSet.getStringClaim("email");

        userService.saveNewSeller(
                new SellerDAO(
                        name,
                        email,
                        passwordEncoder.encode(UUID.randomUUID().toString()),
                        "",
                        "",
                        0,
                        "",
                        LocalDate.now(),
                        "",
                        ""
                )
        );
        String token = jwtGenerator.createToken(email);
        UserDAO userDAO = userService.getByEmail(email);
        UserDTO userDTO = userMapper.toUserDTO(userDAO);
        userDTO.setToken(token);
        return ResponseEntity.ok(userDTO);
    }
}
