package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.services.AdSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class AdsController {

    @Autowired
    AdSvc adSvc;

    @GetMapping("/ads")
    public String ads(Model m){
        List<Ad> ads = adSvc.findAll();
        m.addAttribute("ads", ads);
        //another way to to the same thing without using variables
        //m.addAttribute("ads", adSvc.findAll());
        return "demos/adsIndex";
    }

    @GetMapping("/ads/{id}")
    public String getAd(@PathVariable int id, Model m){
        m.addAttribute("ad", adSvc.findOne(id));
        return "demos/showAd";
    }

}