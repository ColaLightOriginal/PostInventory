package com.PostInventory.Classes;

import javax.persistence.*;

@Entity
@Table(name = "image_urls")
public class ImageUrls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private int id;
    @Column(name="post_id", nullable = false)
    private int postId;
    @Column(name="image_url_id", nullable = false)
    private String imageUrlId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(String imageUrlId) {
        this.imageUrlId = imageUrlId;
    }
}
