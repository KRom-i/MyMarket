package com.gb.market.services;

import com.gb.market.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserService userService;

    public User getAuthUser () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUserName (auth.getName ());
    }
}
