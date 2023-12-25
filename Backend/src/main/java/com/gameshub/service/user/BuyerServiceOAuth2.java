//package com.gameshub.service.user;
//
//import com.gameshub.model.user.*;
//import com.gameshub.repository.user.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.oauth2.core.oidc.*;
//import org.springframework.stereotype.*;
//
//@Service
//public class BuyerServiceOAuth2 {
//
//    @Autowired
//    private BuyerRepository buyerRepository;
//
//    public void createUser(OidcIdToken idToken) {
//        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
//        String name = idToken.getClaim("name").toString();
//        String email = idToken.getClaim("email").toString();
//        BuyerDAO buyer = new BuyerDAO(userId, name, email, null, null, null, 0);
//
//        buyerRepository.saveAndFlush(buyer);
//    }
//
//    public boolean emailAlreadyExist(OidcIdToken idToken) {
//        String email = idToken.getClaim("email").toString();
//        BuyerDAO buyer = buyerRepository.findByEmail(email).orElse(null);
//        if(buyer != null) {
//            return true;
//        }
//        return false;
//    }
//
//}