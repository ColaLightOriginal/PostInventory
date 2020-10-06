package com.PostInventory.Services;

import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public void createPost(Post post){ postRepository.createPost(post); }

    public void deletePost(int postId){
        postRepository.deletePost(postId);
    }

    public void modifyPost(Post post){ postRepository.modifyPost(post);}

    public List<Post> getPostsFromCoord(Float latitude, Float longitude,
                                         Float latitudeDelta, Float longitudeDelta){
        List<Post> postsList = this.getPosts();
        List<Post> returnList = new LinkedList<>();
        float postLatitude;
        float postLongitude;
        for(Post post: postsList){
            postLatitude=Float.parseFloat(post.getCoordinateX());
            postLongitude=Float.parseFloat(post.getCoordinateY());

            float postLatitudeMinus = latitude-latitudeDelta;
            float postLatitudePlus = latitude+latitudeDelta;
            float postLongitudeMinus = longitude-longitudeDelta;
            float postLongitudePlus = longitude+longitudeDelta;

            if((postLatitude>latitude-latitudeDelta && postLatitude<latitude+latitudeDelta) &&
                    (postLongitude>longitude-longitudeDelta && postLongitude<longitude+longitudeDelta)){
                returnList.add(post);
            }
        }
        return returnList;
    }
}
