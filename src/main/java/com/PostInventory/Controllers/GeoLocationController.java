package com.PostInventory.Controllers;

import com.PostInventory.Classes.Post;
import com.PostInventory.Services.GeoLocationService;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "geoLocation")
@Controller
public class GeoLocationController {

    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping(value="getPostsByRadius", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getPostsByRadius(@RequestBody GeoLocation geoLocation){
        return geoLocationService.getPostsByDistanceRadius(geoLocation, geoLocation.getDistance());
    }
}

