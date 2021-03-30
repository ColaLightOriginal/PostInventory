package com.PostInventory.Controllers;

import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import com.PostInventory.Classes.ResponseTransfer;
import com.PostInventory.Services.CommentService;
import com.PostInventory.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "api/posts/comment")
@RestController
public class CommentControler {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "getComments")
    public List<Comment> getPosts() {
        return commentService.getAllComments();
    }

    @GetMapping(value = "getCommentsByPostId/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Map<String, String> pathVariable){
        return commentService.getCommentsByPostId(Integer.parseInt(pathVariable.get("postId")));
    }

    @GetMapping(value = "getComments/{commentId}")
    public Comment getCommentById(@PathVariable Map<String, String> pathVariable){
        return commentService.getCommentById(Integer.parseInt(pathVariable.get("commentId")));
    }

    @PostMapping(value = "createComment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer createComment(@RequestBody Comment comment){
        try{
            commentService.createComment(comment);
            return new ResponseTransfer("Ok", "200", "Comment added");
        } catch( Exception e){
            return new ResponseTransfer("Error", "500", "Internal server error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "removeComment/{commentId}")
    public void deleteComment(@PathVariable Map<String, String> pathVariable){
        commentService.deleteComment(Integer.parseInt(pathVariable.get("commentId")));
    }

    @GetMapping(value = "getCommentsCount/{postId}")
    public long getCommentsCountByPostId(@PathVariable Map<String, String> pathVariable){
        return commentService.getCommentsCountByPostId(Integer.parseInt(pathVariable.get("postId")));
    }
}
