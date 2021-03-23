package com.PostInventory.Classes;

import javax.persistence.*;

@Entity
@Table(name = "likes_unlikes")
public class LikesUnlikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="user_id", nullable = false)
    private int userId;
    @Column(name="post_id", nullable = false)
    private int postId;
    //Operation true - like, false - unlike
    @Column(name="operation", nullable = false)
    private boolean operation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public boolean getOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }
}
