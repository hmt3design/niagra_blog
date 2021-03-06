package com.codeup.services;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** * Created by Harry on 2/9/17. */
@Service("postService")
public class PostService {
    private List<Post> posts = new ArrayList<>();
    private PostsRepository repository;

    @Autowired
    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    public List<Post> all(PostsRepository postsDao) {
        //Iterable -> List (casting it)
        return (List<Post>) repository.findAll(); // select * from posts
    }

    // finding a post (retrieving an individual post object)
    public Post findSinglePost(long id) {
        return repository.findOne(id); // select * from posts where id = ?
    }

    // retrieving all the posts
    public List<Post> findAllPosts() {
        return posts;
    }

    public void save(Post post) {
        repository.save(post); // insert into ads (title, description) values (?,?)
    }
}
