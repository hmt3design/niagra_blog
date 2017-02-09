package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.models.Post;
import com.codeup.repositories.Posts;
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

    @Autowired
    Posts postsDao;


/*    @PostMapping("/posts/create")
    public String savePost(
//            @RequestParam(name ="title") String title,
//            @RequestParam(name = "description") String description,
            @ModelAttribute Post post, // Post post
            Model viewModel) {
        // Sticky form
        // we would insert into the corresponding table, using a dao
        service.save(post); // -> {posts.add(post);} (array list in your service)
        viewModel.addAttribute("post", post);
        return "posts/create";
    }*/
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
        Iterable<Post> posts = postsDao.findAll();

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

    @GetMapping("/posts/create")
    public String showPost(@ModelAttribute Post post, Model viewModel) {
        viewModel.addAttribute("message", "");
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, Model viewModel) {
        postsDao.save(post);
        return "redirect:/posts";
    }

//     // think of the next two as Step 1 and Step 2 respectively
//    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    @ResponseBody
//    public String createPostForm() {
//        return "<h1>Create post form: </h1>";
//    }
//
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    @ResponseBody
//    public String createPostPage() {
//        return "<h1>Create post: </h1>";
//    }
}
