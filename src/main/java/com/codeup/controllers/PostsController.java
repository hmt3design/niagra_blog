package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2/7/17.
 */
@Controller
public class PostsController {
    // property injection
    private PostService service; // instance

    // setter injection -> setService(PostService service){
    // constructor injection (favored for @Autowired)
    @Autowired
    public PostsController(PostService service) { // local variable
        this.service = service;
    }

    @GetMapping("/posts")
    public String viewAllPosts(Model viewModel) {

        //  array list with several post objects
        List<Post> posts = service.findAllPosts();

        // pass the list to the view (through a view model)
        viewModel.addAttribute("posts", posts);

        return "/posts/index";
    } // index.html

    @GetMapping("/posts/{id}")
    public String findSinglePost(@PathVariable long id, Model viewModel) {
        Post post = service.findSinglePost(id);
        // inside the method that shows an indidvidual post, create a new post object and pass it to the view
        viewModel.addAttribute("post", post);
        return "/posts/show"; // show.html
    }

    // think of the next two as Step 1 and Step 2 respectively
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPostForm() {
        return "<h1>Create post form: </h1>";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPostPage() {
        return "<h1>Create post: </h1>";
    }
}
