package com.PostInventory.Controllers;
import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "post")
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepositoryTest = new PostRepository();

    @GetMapping(value = "getPosts")
    public List<Post> getPosts() {
        return postRepositoryTest.getAll();
    }
}
