package com.PostInventory.Controllers;

import com.PostInventory.Classes.PostImage;
import com.PostInventory.Services.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/posts/postImage")
@RestController
public class PostImageController {

    @Autowired
    private PostImageService postImageService;

    @PostMapping(value = "createPostImage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPostImage(@RequestBody PostImage postImage){
        postImageService.createPostImage(postImage);
    }

}
