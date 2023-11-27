//package com.gameshub.Model.Users;
//
//import org.springframework.security.core.*;
//import org.springframework.security.core.userdetails.*;
//
//import java.util.*;
//import java.util.stream.*;
//
//public class BuyerDetails implements UserDetails {
//
//    private String userName;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorities;
//
//    public BuyerDetails(BuyerDAO buyerDAO) {
//        this.userName = buyerDAO.getEmail();
//        this.password = buyerDAO.getPassword();
//        this.active = true;
////        this.authorities = Arrays.stream(buyerDAO.getRoles().split(","))
////                    .map(SimpleGrantedAuthority::new)
////                    .collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return active;
//    }
//}