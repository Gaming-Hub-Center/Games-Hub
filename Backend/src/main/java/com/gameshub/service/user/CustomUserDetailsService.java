package com.gameshub.service.user;

import com.gameshub.model.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDAO userDAO = userService.getUserByEmail(username);
        return new User(userDAO.getEmail(), userDAO.getPassword(), List.of(new SimpleGrantedAuthority(userDAO.getRole())));
    }

}