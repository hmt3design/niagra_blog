package com.codeup.controllers;

import com.codeup.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Harry on 2/13/17.
 */
@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/users/create")
    public String showForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/create";
    }

}