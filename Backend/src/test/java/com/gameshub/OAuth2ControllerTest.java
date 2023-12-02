//package com.gameshub;
//
//import com.gameshub.Controller.OAuth2Controller;
//import com.gameshub.Service.BuyerServiceOAuth2;
//import com.gameshub.Service.SellerServiceOAuth2;
//import com.gameshub.Service.CreateUserProxy;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(OAuth2Controller.class)
//public class OAuth2ControllerTest {
//
//    @Autowired
//    private OAuth2Controller oAuth2Controller;
//
//    @MockBean
//    private CreateUserProxy createUserProxy;
//
//    @MockBean
//    private BuyerServiceOAuth2 buyerService;
//
//    @MockBean
//    private SellerServiceOAuth2 sellerService;
//
//    @Test
//    void testSignupPageBuyer_ExistingBuyerLogin() {
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        DefaultOidcUser defaultOidcUser = mock(DefaultOidcUser.class);
//        when(defaultOidcUser.getIdToken()).thenReturn(idToken);
//        when(buyerService.emailAlreadyExist(idToken)).thenReturn(true);
//
//        ResponseEntity<?> response = oAuth2Controller.signupPageBuyer(defaultOidcUser);
//
//        assertEquals("Logged in successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    public void testSignupPageBuyer_NotExisting() {
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        DefaultOidcUser defaultOidcUser = mock(DefaultOidcUser.class);
//        when(defaultOidcUser.getIdToken()).thenReturn(idToken);
//        when(buyerService.emailAlreadyExist(idToken)).thenReturn(false);
//
//        ResponseEntity<?> response = oAuth2Controller.signupPageBuyer(defaultOidcUser);
//
//        assertEquals("Signed up successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    public void testSignupPageSeller_EmailAlreadyExists() {
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        DefaultOidcUser defaultOidcUser = mock(DefaultOidcUser.class);
//        when(defaultOidcUser.getIdToken()).thenReturn(idToken);
//        when(sellerService.emailAlreadyExist(idToken)).thenReturn(true);
//
//        ResponseEntity<?> response = oAuth2Controller.signupPageSeller(defaultOidcUser);
//
//        assertEquals("Logged in successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    public void testSignupPageSeller_SuccessfulSignup() {
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        DefaultOidcUser defaultOidcUser = mock(DefaultOidcUser.class);
//        when(defaultOidcUser.getIdToken()).thenReturn(idToken);
//        when(sellerService.emailAlreadyExist(idToken)).thenReturn(false);
//
//        ResponseEntity<?> response = oAuth2Controller.signupPageSeller(defaultOidcUser);
//
//        assertEquals("Signed up successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//}
