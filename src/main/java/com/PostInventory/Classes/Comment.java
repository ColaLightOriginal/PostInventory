package com.PostInventory.Classes;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Comment {
    private int id;
    private int postUserId;
    private String content;
    private int likesCount;
    private LinkedList<Integer> userLikesId = new LinkedList<Integer>();

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

    public LinkedList<Integer> getUserLikesId() {
        return userLikesId;
    }

    public void setUserLikesId(LinkedList<Integer> userLikesId) {
        this.userLikesId = userLikesId;
    }



}
