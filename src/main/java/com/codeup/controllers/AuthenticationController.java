package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserRole;
import com.codeup.repositories.RolesRepository;
import com.codeup.repositories.Users;
import com.codeup.services.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by Harry on 2/13/17.
 */
@Controller
public class AuthenticationController {

    @Autowired
    Users usersDao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    RolesRepository userRoles;

    @Autowired
    UserService userService;

    @Autowired
    public AuthenticationController(Users repository, PasswordEncoder encoder) {
        this.usersDao = repository;
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
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "redirect:/login";
        }

        String hashedPassword = encoder.encode(user.getPassword()); // hash the user's password
        user.setPassword(hashedPassword);
        User newUser = usersDao.save(user); // save user to the database

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUserId(newUser.getId());
        userRoles.save(userRole);
        return "redirect:/login"; // redirect the user to the login page
    }

    @GetMapping("users/{id}")
    public String showUser(@PathVariable Long id, Model viewModel) {
        viewModel.addAttribute("user", usersDao.findOne(id));
        return "users/show";
    }
}