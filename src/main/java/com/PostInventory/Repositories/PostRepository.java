package com.PostInventory.Repositories;
import com.PostInventory.Classes.Post;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    private EntityManager sessionFactory;

    public List<Post> getAll(){
        try {
            List<Post> resultList = new LinkedList<>();
            String query = "select post from Post post";
            TypedQuery<Post> typedQuery = sessionFactory.createQuery(query, Post.class);
            resultList = typedQuery.getResultList();
            return resultList;
        }
        catch(Exception e){
                System.out.println(e);
                return null;
        }
    }
}
