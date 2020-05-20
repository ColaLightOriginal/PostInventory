package com.PostInventory.Services;

import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.PostRepository;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoLocationService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostsByDistanceRadius(GeoLocation geoLocation){
        return postRepository.getPostsWithinDistance(geoLocation, geoLocation.getDistance());
    }
}
