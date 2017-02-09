package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.services.AdSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdsController {

    @GetMapping("/ads/create")
    public String showCreateAdForm(Model viewModel) {
        Ad ad = new Ad();
        viewModel.addAttribute("ad", ad);
        return "ads/create";
    }


    // RequestParam does the following servlet
    // String title = request.getParameter("title")
    // String description = request.getParameter("description")
    // controller.saveAd(title, descriptin);

    // ModelAttribute does the following servlet
    // String title = request.getParameter("title")
    // String description = request.getParameter("description")
    // Ad ad = new Ad();
    // ad.settitle(title)
    // ad.setDescription(description)
    // controller.saveAd(ad);

    @PostMapping("/ads/create")
    public String saveAd(
//            @RequestParam(name ="title") String title,
//            @RequestParam(name = "description") String description,
            @ModelAttribute Ad ad, // Post post
            Model viewModel) {
        // Sticky form
        // we would insert into the corresponding table, using a dao
        // service.save(post); -> {posts.add(post);} (array list in your service)
        viewModel.addAttribute("ad", ad);
        return "ads/create";
    }
}