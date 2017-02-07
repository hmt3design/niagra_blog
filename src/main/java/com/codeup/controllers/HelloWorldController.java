package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** * Created by Harry on 2/7/17. */

@Controller //defines that our class is a controller
public class HelloWorldController {

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/hello/{name}") // defines a method that should be invoked when a GET request is received for the specified URI
    @ResponseBody // tells Spring that whatever is returned from this method should be the body of our response.
    public String hello(@PathVariable String name){
        return formatGreeting(name);
    }

    private String formatGreeting(String name) {
        return "<h1>Hello " + name + " from Spring!</h1>";
    }

    @RequestMapping(path = "/bye/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String bye(@PathVariable String name) {
        return "<h1>Goodbye " + name + " from Spring!</h1>";
    }
}
