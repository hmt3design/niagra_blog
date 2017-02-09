package com.codeup.services;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2/9/17.
 */
@Service("postService")
public class PostService {
    private List<Post> posts = new ArrayList<>();

    public PostService(){
        createPosts();
    }

    // constructor to add test posts
    private void createPosts() {
        for (int i = 0; i < 100; i++) {
            posts.add(
                    new Post(i + 1, "Title" + (i + 1), "Body" + (i + 1))
            );
        }
    }

    // finding a post (retrieving an individual post object)
    public Post findSinglePost(long id) {
        return posts.get((int) (id-1));
    }

    // retrieving all the posts
    public List<Post> findAllPosts() {
        return posts;
    }
}
