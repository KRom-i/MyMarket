package com.gb.market.services;


import com.gb.market.entites.SystemUser;
import com.gb.market.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
