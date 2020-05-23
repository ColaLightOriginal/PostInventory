package com.PostInventory.Controllers;

import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import com.PostInventory.Services.CommentService;
import com.PostInventory.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "comment")
@RestController
public class CommentControler {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "getComments")
    public List<Comment> getPosts() {
        return commentService.getAllComments();
    }

    @GetMapping(value = "getComments/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Map<String, String> pathVariable){
        return commentService.getCommentsByPostId(Integer.parseInt(pathVariable.get("postId")));
    }

}
