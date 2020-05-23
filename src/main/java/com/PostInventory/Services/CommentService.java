package com.PostInventory.Services;

import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.CommentRepository;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {return commentRepository.getAll();}

    public List<Comment> getCommentsByPostId(int postId){return commentRepository.getPostComments(postId);}
}
