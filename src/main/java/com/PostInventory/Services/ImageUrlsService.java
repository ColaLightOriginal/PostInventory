package com.PostInventory.Services;

import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Repositories.ImageUrlsRepository;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImageUrlsService {

    @Autowired
    private ImageUrlsRepository imageUrlsRepository;

    public List<String> getPostImagesUrlsList(int postId){
        List<ImageUrls> imageUrlsList= imageUrlsRepository.getPostImagesUrlsList(postId);
        List<String> imageUrlsIdsList = new LinkedList<>();
        for (ImageUrls i: imageUrlsList) {
            imageUrlsIdsList.add(i.getImageUrlId());
        }
        return imageUrlsIdsList;
    }

    public void createPostImageUrlsList(List<ImageUrls> imageUrlsList){
        for (ImageUrls i: imageUrlsList) {
            imageUrlsRepository.createPostImageUrl(i);
        }
    }
}

