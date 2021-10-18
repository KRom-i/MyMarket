package com.gb.market.controllers;

import com.gb.market.entities.user.SystemUser;
import com.gb.market.entities.user.User;
import com.gb.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("systemUser", new SystemUser ());
        return "registration_form";
    }


    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser, BindingResult theBindingResult, Model theModel) {
        String userName = theSystemUser.getUserName();

        if (theBindingResult.hasErrors()) {
            return "registration_form";
        }
        User existing = userService.findByUserName(userName);
        if (existing != null) {

            theModel.addAttribute("systemUser", theSystemUser);
            theModel.addAttribute("registrationError", "Пользователь с данным именем уже зарегистрирован");

            return "registration_form";
        }

        userService.save(theSystemUser);

        return "redirect:/login";
    }
}
