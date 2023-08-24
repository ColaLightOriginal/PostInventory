package com.PostInventory.Controllers;

import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import com.PostInventory.Classes.ResponseTransfer;
import com.PostInventory.Services.CommentService;
import com.PostInventory.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "api/posts/comment")
@RestController
public class CommentControler {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "getComments")
    public ResponseEntity<List<Comment>> getPosts() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.getAllComments());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getCommentsByPostId/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Map<String, String> pathVariable){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.getCommentsByPostId(Integer.parseInt(pathVariable.get("postId"))));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getComments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Map<String, String> pathVariable){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.getCommentById(Integer.parseInt(pathVariable.get("commentId"))));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "createComment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        try{
            commentService.createComment(comment);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(comment);
        } catch( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "removeComment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Map<String, String> pathVariable){
        try{
            commentService.deleteComment(Integer.parseInt(pathVariable.get("commentId")));
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Comment Deleted");
        } catch( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "getCommentsCount/{postId}")
    public ResponseEntity<Long> getCommentsCountByPostId(@PathVariable Map<String, String> pathVariable){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentService.getCommentsCountByPostId(Integer.parseInt(pathVariable.get("postId"))));
        } catch( Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
