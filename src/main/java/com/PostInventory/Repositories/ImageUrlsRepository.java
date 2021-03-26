package com.PostInventory.Repositories;
import com.PostInventory.Classes.ImageUrls;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ImageUrlsRepository {

    @Autowired
    @PersistenceContext
    private EntityManager sessionFactory;

    public List<ImageUrls> getPostImagesUrlsList(int postId){
        try{
            List<ImageUrls> resultList = new LinkedList<>();
            String query = "from ImageUrls where post_id=:postId";
            TypedQuery<ImageUrls> typedQuery = sessionFactory.createQuery(query, ImageUrls.class);
            typedQuery.setParameter("postId", postId);
            resultList = typedQuery.getResultList();
            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void createPostImageUrl(ImageUrls imageUrls, int postId){
        try{
            ImageUrls result = new ImageUrls();

            result.setImageUrlId(imageUrls.getImageUrlId());
            result.setPostId(postId);

            sessionFactory.persist(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
