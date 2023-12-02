package com.gameshub.Service;

import com.gameshub.Exception.*;
import com.gameshub.Model.User.DAO.*;
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