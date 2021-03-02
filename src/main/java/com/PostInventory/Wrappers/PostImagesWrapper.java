package com.PostInventory.Wrappers;

import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Classes.Post;

import java.util.List;

public class PostImagesWrapper {

    private Post post;
    private List<ImageUrls> imageUrls;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<ImageUrls> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ImageUrls> imageUrls) {
        this.imageUrls = imageUrls;
    }

}
