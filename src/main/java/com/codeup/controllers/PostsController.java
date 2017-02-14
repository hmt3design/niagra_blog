package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostsRepository;
import com.codeup.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostsRepository postsDao;

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
    public String createPost(@Valid Post post, Errors validation, Model viewModel) { // it calls ModelAttribute first
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "posts/create";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
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
