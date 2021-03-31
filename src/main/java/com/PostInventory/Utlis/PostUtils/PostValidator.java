package com.PostInventory.Utlis.PostUtils;

import com.PostInventory.Classes.Post;

public class PostValidator {

    public static void validatePost(Post post) throws Exception{
        switch(post.getType()){
            case "Question":
            case "Help":
            case "Alert":
                if(!checkStandardFields(post)) throw new Exception();
                break;
            case "Event":
                if(!checkEventFields(post)) throw new Exception();
                break;
            case "Offer":
                if(!checkOfferFields(post)) throw new Exception();
                break;
            default:
                throw new Exception();
        }
    }

    private static boolean checkStandardFields(Post post){
        post.log();
        if(post.getTitle() == null || post.getDescription() == null) return false;
        return true;
    }

    private static boolean checkEventFields(Post post){
        post.log();
        if(post.getTitle() == null || post.getDescription() == null ||
                post.getPrice() == null || post.getEventDate() == null) return false;
        return true;
    }

    private static boolean checkOfferFields(Post post){
        post.log();
        if(post.getTitle() == null || post.getDescription() == null ||
                post.getPrice() == null) return false;
        return true;
    }

}
