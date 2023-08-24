package com.PostInventory.Controllers;

import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Classes.LikesUnlikes;
import com.PostInventory.Classes.Post;
import com.PostInventory.DTOs.PostDTO;
import com.PostInventory.Services.PostService;
import com.PostInventory.Wrappers.PostImagesWrapper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "api/posts/post")
@RestController
@Log4j2
public class PostController {

    @Autowired
    private PostService postService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "getPosts")
    public ResponseEntity<List<PostDTO>> getPosts(HttpServletRequest request) throws IOException {
        try{
            List<Post> postsList = postService.getPosts();
            return ResponseEntity.status(HttpStatus.OK).
                    body(modelMapper.map(postsList, new TypeToken<List<PostDTO>>() {}.getType()));
        } catch(Exception e){
            log.error(request.getRequestURI() + request.getInputStream().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getUserPosts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Map<String, String> pathVariable){
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(postService.getUserPosts(Integer.parseInt(pathVariable.get("userId"))));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getPosts/{postId}")
    public ResponseEntity<PostImagesWrapper> getPostById(@PathVariable Map<String, String> pathVariable){
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(postService.getPostByPostIdWithImageUrls(Integer.parseInt(pathVariable.get("postId"))));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @PostMapping(value = "createPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@RequestBody Post post, HttpServletRequest request) {
        try {
            postService.createPost(post);
            log.info("Create post: " + post.toString());
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(post);
        }
    }

    @DeleteMapping(value = "removePost/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Map<String, String> pathVariable){
        try{
            postService.deletePost(Integer.parseInt(pathVariable.get("postId")));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post not found");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "modifyPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> modifyPost(@RequestBody Post post ){
        try {
            post.log();
            postService.modifyPost(post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(post);
        }
    }

    @PostMapping(value = "createPostWithImages", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPostWithImages(@RequestBody PostImagesWrapper postImagesWrapper) {
        try {
            postService.createPostWithImages(postImagesWrapper.getPost(), postImagesWrapper.getImageUrls());
            return new ResponseEntity<>(postImagesWrapper.getPost(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(postImagesWrapper.getPost());
        }
    }

    @GetMapping(value="getPostsFromCoord/{latitude}/{longitude}/{latitudeDelta}/{longitudeDelta}")
    public ResponseEntity<List<PostDTO>> getPostsFromCoord(@PathVariable Map<String, String> pathVariable){
        try{
            Float latitude = Float.parseFloat(pathVariable.get("latitude"));
            Float longitude = Float.parseFloat(pathVariable.get("longitude"));
            Float latitudeDelta = Float.parseFloat(pathVariable.get("latitudeDelta"));
            Float longitudeDelta = Float.parseFloat(pathVariable.get("longitudeDelta"));

            List<Post> postsList =  postService.getPostsFromCoord(latitude, longitude ,latitudeDelta, longitudeDelta);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapper.map(postsList, new TypeToken<List<PostDTO>>() {}.getType()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value="getPostsFromCoordWithinRadius/{latitude}/{longitude}/{radiusInKm}")
    public ResponseEntity<List<PostDTO>> getPostsFromCoordWithinRadius(@PathVariable Map<String, String> pathVariable){
        try {
            Float latitude = Float.parseFloat(pathVariable.get("latitude"));
            Float longitude = Float.parseFloat(pathVariable.get("longitude"));
            Float radiusInKm = Float.parseFloat(pathVariable.get("radiusInKm"));

            radiusInKm = radiusInKm / 100;

            List<Post> postsList = postService.getPostsFromCoord(latitude, longitude, radiusInKm, radiusInKm);
            return modelMapper.map(postsList, new TypeToken<List<PostDTO>>() {}.getType());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "addLikeOrUnlike", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrUpdateLikeOrUnlike(@RequestBody LikesUnlikes likesUnlikes){
        try {
            postService.addOrUpdateLikeOrUnlike(likesUnlikes);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value="addImageToPost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addImageToPost(@RequestBody List<ImageUrls> imageUrlsList){
        try {
            postService.addImageToPost(imageUrlsList);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
