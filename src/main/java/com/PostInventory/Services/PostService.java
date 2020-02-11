package com.PostInventory.Services;

import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.getAll();
    }

    public Post getPostByPostId(int postId){
        return postRepository.getByPostId(postId);
    }

    public List<Post> getUserPosts(int userId){
        return postRepository.getUserPosts(userId);
    }

    public void createPost(Post post){
        postRepository.createPost(post);
    }

    public void deletePost(int postId){
        postRepository.deletePost(postId);
    }
}
