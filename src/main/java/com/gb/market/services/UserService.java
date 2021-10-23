package com.gb.market.services;

import com.gb.market.entities.SystemUser;
import com.gb.market.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    boolean save(SystemUser systemUser);
}
