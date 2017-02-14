package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


/**
 * Created by Harry on 2/13/17.
 */
@Controller
public class AuthenticationController {

    private Users repository;
    private PasswordEncoder encoder;

    @Autowired
    public AuthenticationController(Users repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/users/create")
    public String showForm(Model viewModel){
        viewModel.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/users/create")
    public String registerUser(
            @Valid User user,
            Errors validation,
            Model viewModel, // create the user from the input
            @RequestParam(name = "password_confirm") String passwordConfirmation
    ){
        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue("password", "user.password", "Your passwords do not match");
        }
        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/create";
        }
        String hashedPassword = encoder.encode(user.getPassword()); // hash the user's password
        user.setPassword(hashedPassword);
        repository.save(user); // save user to the database
        return "redirect:/login"; // redirect the user to the login page
    }
}