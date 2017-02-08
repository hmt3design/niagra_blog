package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Harry on 2/8/17.
 */
@Controller
public class RollDiceController {
    @GetMapping("/rolldice")
    public String dicePage() {
        return "rolldice/rolldiceselect";
    }

    @GetMapping("/rolldice/{n}")
    public String resultPage(@PathVariable int n, Model model) {
        int rollDice = (int) (Math.random() * 6 + 1);
        model.addAttribute("n", n);
        model.addAttribute("rollDice", rollDice);
        return "rolldice/rolldiceresult";
    }

}
