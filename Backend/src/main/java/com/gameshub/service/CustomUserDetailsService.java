package com.gameshub.service;

import com.gameshub.exception.*;
import com.gameshub.model.user.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        UserDAO userDAO = userService.getByEmail(username);
        return new User(userDAO.getEmail(), userDAO.getPassword(), new ArrayList<>());
    }

}