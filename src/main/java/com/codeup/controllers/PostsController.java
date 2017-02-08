package com.codeup.controllers;

import com.codeup.models.Post;
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

    @GetMapping("/posts")
    public String viewAllPosts(Model model) {

        //  array list with several post objects
        List<Post> posts = new ArrayList<>();

        // pass the list to the view (through a view model)
        posts.add(new Post("My first post", "Body of first post"));
        posts.add(new Post("My second post", "Body of second post"));
        model.addAttribute("ListOfPosts", posts);

        return "/posts/index";
    } // index.html

    @GetMapping("/posts/{id}")
    public String viewSinglePost(@PathVariable long id, Model model) {
        // inside the method that shows an indidvidual post, create a new post object and pass it to the view
        Post post = new Post("Hello, World", "World body");
        model.addAttribute("post", post);
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
