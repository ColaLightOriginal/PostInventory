package com.PostInventory.Controllers;

import com.PostInventory.Services.ImageUrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "api/posts/imageUrls")
@RestController
public class ImageUrlsController {
    @Autowired
    private ImageUrlsService imageUrlsService;

    @GetMapping(value="getPostImageUrls/{postId}")
    public List<String> getPostImagesUrlsList(@PathVariable Map<String, String> pathVariable){
        return imageUrlsService.getPostImagesUrlsList(Integer.parseInt(pathVariable.get("postId")));
    }
}
