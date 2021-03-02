package com.PostInventory.Repositories;

import com.PostInventory.Classes.PostImage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PostImageRepository {

    @Autowired
    private EntityManager sessionFactory;

    public void createPostImage(PostImage postImage){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            PostImage result = new PostImage();
            result.setId(postImage.getId());
            result.setFilename(postImage.getFilename());
            result.setImage(postImage.getImage());
            result.setPostId(postImage.getPostId());

            session.save(result);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
