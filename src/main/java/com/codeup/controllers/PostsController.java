package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostsRepository;
import com.codeup.services.PostService;
import com.codeup.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Harry on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostsRepository postsDao;

    @Autowired
    UserService userService;

    private PostService service; // instance
    @Value("${uploads}")
    private String uploadsPath;

    // setter injection -> setService(PostService service){
    // constructor injection (favored for @Autowired)
    @Autowired
    public PostsController(PostService service) { // local variable
        this.service = service;
    }

    @GetMapping("/posts")
    public String viewAllPosts(Model viewModel) {

        //  array list with several post objects
//        Iterable<Post> posts = postsDao.findAll();

        // pass the list to the view (through a view model)
        viewModel.addAttribute("posts", Collections.emptyList());

        return "/posts/index";
    } // index.html

    @GetMapping("/posts.json")
    public @ResponseBody List<Post> retrieveAllPosts() {
        return (List<Post>) postsDao.findAll();
    }

    @GetMapping("/posts/{id}")
    public String findSinglePost(@PathVariable long id, Model viewModel) {
        Post post = service.findSinglePost(id);
        // inside the method that shows an indidvidual post, create a new post object and pass it to the view
        viewModel.addAttribute("post", post);
        return "/posts/show"; // show.html
    }

    @GetMapping("/posts/create")
    public String showPost(@ModelAttribute Post post, Model viewModel) {
        viewModel.addAttribute("message", "");
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @Valid Post post, // it calls ModelAttribute first
            Errors validation,
            Model viewModel,
            @RequestParam(name = "image_file") MultipartFile uploadedFile) {

        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "posts/create";
        }

        // unix based : mac, linux -> the folder for temporary files is always /tmp

        String filename = uploadedFile.getOriginalFilename();
        System.out.println(filename);
        String filepath = Paths.get(uploadsPath, filename).toString();
        System.out.println(filepath);
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile); // it will move the file in this step
            viewModel.addAttribute("message", "File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            viewModel.addAttribute("message", "File not uploaded");
        }


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        post.setImage(filename);

        post.setUser(userService.loggedInUser());
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editedPost(@PathVariable long id, Model viewModel) {
        Post post = service.findSinglePost(id);
        viewModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post, Model viewModel) {
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("posts/{id}/delete")
    public String deletePost(@ModelAttribute Post post){
        postsDao.delete(postsDao.findOne(post.getId()));
        return "redirect:/posts";
    }

}
