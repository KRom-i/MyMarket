package com.gb.market.services;

import com.gb.market.entities.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
