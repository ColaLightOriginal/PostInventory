package com.PostInventory.Controllers;
import com.PostInventory.Classes.Post;
import com.PostInventory.Classes.ResponseTransfer;
import com.PostInventory.Services.PostService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "api/posts/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "getPosts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(value = "getUserPosts/{userId}")
    public List<Post> getUserPosts(@PathVariable Map<String, String> pathVariable){
        return postService.getUserPosts( Integer.parseInt(pathVariable.get("userId")));
    }

    @GetMapping(value = "getPosts/{postId}")
    public Post getPostById(@PathVariable Map<String, String> pathVariable){
        return postService.getPostByPostId( Integer.parseInt(pathVariable.get("postId")));
    }

    @ResponseBody
    @PostMapping(value = "createPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer createPost(@RequestBody Post post ){
        try {
            postService.createPost(post);
            return new ResponseTransfer("Ok", "200", "Post Added");
        }catch(Exception e){
            return new ResponseTransfer("Error", "500", "Internal server error");
        }
    }

    @DeleteMapping(value = "removePost/{postId}")
    public void deletePost(@PathVariable Map<String, String> pathVariable){
        postService.deletePost(Integer.parseInt(pathVariable.get("postId")));
    }

    @PostMapping(value = "modifyPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifyPost(@RequestBody Post post ){
        postService.modifyPost(post);
    }

    @GetMapping(value="getPostsFromCoord/{latitude}/{longitude}/{latitudeDelta}/{longitudeDelta}")
    public List<Post> getPostsFromCoord(@PathVariable Map<String, String> pathVariable){
        Float latitude = Float.parseFloat(pathVariable.get("latitude"));
        Float longitude = Float.parseFloat(pathVariable.get("longitude"));
        Float latitudeDelta = Float.parseFloat(pathVariable.get("latitudeDelta"));
        Float longitudeDelta = Float.parseFloat(pathVariable.get("longitudeDelta"));
        return postService.getPostsFromCoord(latitude, longitude ,latitudeDelta, longitudeDelta);
    }
}
