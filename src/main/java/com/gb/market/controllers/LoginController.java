package com.gb.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest) {
        String referrer = httpServletRequest.getHeader("referer");
        httpServletRequest.getSession ().setAttribute ("current_referer", referrer);
        return "login";
    }

}
