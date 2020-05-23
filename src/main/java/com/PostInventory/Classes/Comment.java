package com.PostInventory.Classes;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="post_id", unique = false)
    private int postId;
    @Column(name="comment_user_id", nullable = false)
    private int postUserId;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="likes_count", nullable = false)
    private int likesCount;
    @Column(name="user_likes_ids", nullable = false)
    private int[] userLikesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(int postUserId) {
        this.postUserId = postUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int[] getUserLikesId() {
        return userLikesId;
    }

    public void setUserLikesId(int[] userLikesId) {
        this.userLikesId = userLikesId;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
