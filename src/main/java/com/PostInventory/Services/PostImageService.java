package com.PostInventory.Services;

import com.PostInventory.Classes.PostImage;
import com.PostInventory.Repositories.PostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostImageService {

    @Autowired
    private PostImageRepository postImageRepository;

    public void createPostImage(PostImage postImage){
        postImageRepository.createPostImage(postImage);
    }
}
