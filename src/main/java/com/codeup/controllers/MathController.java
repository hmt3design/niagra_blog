package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Harry on 2/7/17.
 */

@Controller
public class MathController {
    @GetMapping(path = "/add/{number1}/and/{number2}")
    @ResponseBody
    public String add(@PathVariable double number1, @PathVariable double number2) {
        return number1 + " + " + number2 + " = " + (number1 + number2);
    }

    @GetMapping(path = "/subtract/{number1}/and/{number2}")
    @ResponseBody
    public String subtract(@PathVariable double number1, @PathVariable double number2) {
        return number1 + " - " + number2 + " = " + (number1 - number2);
    }

    @GetMapping(path = "/multiply/{number1}/and/{number2}")
    @ResponseBody
    public String multiply(@PathVariable double number1, @PathVariable double number2) {
        return number1 + " * " + number2 + " = " + (number1 * number2);
    }

    @GetMapping(path = "/divide/{number1}/and/{number2}")
    @ResponseBody
    public String divide(@PathVariable double number1, @PathVariable double number2) {
        return number1 + " / " + number2 + " = " + (number1 / number2);
    }
}
