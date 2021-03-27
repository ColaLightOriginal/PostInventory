package com.PostInventory.Controllers;
import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Classes.LikesUnlikes;
import com.PostInventory.Classes.Post;
import com.PostInventory.Wrappers.PostImagesWrapper;
import com.PostInventory.Classes.ResponseTransfer;
import com.PostInventory.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
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
            post.log();
            return new ResponseTransfer("Ok", "200", "Post Added:" );
        }catch(Exception e){
            return new ResponseTransfer("Error: createPost", "500", "Internal server error: " + e.getMessage() );
        }
    }

    @DeleteMapping(value = "removePost/{postId}")
    public void deletePost(@PathVariable Map<String, String> pathVariable){
        postService.deletePost(Integer.parseInt(pathVariable.get("postId")));
    }

    @PostMapping(value = "modifyPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer modifyPost(@RequestBody Post post ){
        try {
            post.log();
            postService.modifyPost(post);
            return new ResponseTransfer("Ok", "200", "Post Added:" );
        } catch (Exception e){
            return new ResponseTransfer("Error: modifyPost", "500", "Internal server error: " + e.getMessage() );
        }
    }

    @PostMapping(value = "createPostWithImages", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer createPostWithImages(@RequestBody PostImagesWrapper postImagesWrapper) {
        try {
            postService.createPostWithImages(postImagesWrapper.getPost(), postImagesWrapper.getImageUrls());
            return new ResponseTransfer("Ok", "200", "Post Added:" );
        } catch (Exception e) {
            return new ResponseTransfer("Error: modifyPost", "500", "Internal server error: " + e.getMessage());
        }
    }

    @GetMapping(value="getPostsFromCoord/{latitude}/{longitude}/{latitudeDelta}/{longitudeDelta}")
    public List<Post> getPostsFromCoord(@PathVariable Map<String, String> pathVariable){
        Float latitude = Float.parseFloat(pathVariable.get("latitude"));
        Float longitude = Float.parseFloat(pathVariable.get("longitude"));
        Float latitudeDelta = Float.parseFloat(pathVariable.get("latitudeDelta"));
        Float longitudeDelta = Float.parseFloat(pathVariable.get("longitudeDelta"));
        return postService.getPostsFromCoord(latitude, longitude ,latitudeDelta, longitudeDelta);
    }

    @PostMapping(value = "addLikeOrUnlike", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrUpdateLikeOrUnlike(@RequestBody LikesUnlikes likesUnlikes){
        postService.addOrUpdateLikeOrUnlike(likesUnlikes);
    }

    @PostMapping(value="addImageToPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addImageToPost(@RequestBody List<ImageUrls> imageUrlsList){
        postService.addImageToPost(imageUrlsList);
    }
}
