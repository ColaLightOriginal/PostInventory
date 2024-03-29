package com.PostInventory.Repositories;
import com.PostInventory.Classes.Post;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;
import jdk.jfr.StackTrace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public Post getByPostId(int postId){
        try{
            String query = "select post from Post post where id=:postId";
            TypedQuery<Post> typedQuery = sessionFactory.createQuery(query, Post.class);
            typedQuery.setParameter("postId",postId);
            return typedQuery.getSingleResult();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<Post> getUserPosts(int userId){
        try{
            List<Post> resultList = new LinkedList<>();
            String query = "select post from Post post where createUser=:userId";
            TypedQuery<Post> typedQuery = sessionFactory.createQuery(query, Post.class);
            typedQuery.setParameter("userId", userId);
            resultList = typedQuery.getResultList();
            return resultList;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void createPost(Post post){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            Post result = new Post();

            result.setId(post.getId());
            result.setType(post.getType());
            result.setStatus(post.getStatus());
            result.setDescription(post.getDescription());
            result.setValidDateTime(post.getValidDateTime());
            result.setCreateDateTime(post.getCreateDateTime());
            result.setCreateUser(post.getCreateUser());
            result.setCoordinateX(post.getCoordinateX());
            result.setCoordinateY(post.getCoordinateY());

            session.save(result);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void deletePost(int postId){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            Post post = session.get(Post.class, postId);
            session.delete(post);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public List<Post> getPostsWithinDistance(GeoLocation location, double distance){
        List<Post> resultList = new LinkedList<>();
        String query = "SELECT * FROM Post post WHERE (coordinateX >=:minLat AND coordinateX <=:maxLat) AND (coordinateY >=:minLon " +
                (location.meridian180WithDistance() ? "OR" : "AND") +
                " coordinateY <=:maxLon) AND acos(sin(:radLat) * sin(coordinateX) + cos(:radLat) * cos(coordinateX) * cos(coordinateY - :radLon)) <=:earthRadius";
        TypedQuery<Post> typedQuery = sessionFactory.createQuery(query, Post.class);
        typedQuery.setParameter("minLat", location.boundingCoordinates(distance).get(0).getRadLatitude());
        typedQuery.setParameter("maxLat", location.boundingCoordinates(distance).get(1).getRadLatitude());
        typedQuery.setParameter("minLon", location.boundingCoordinates(distance).get(0).getRadLongitude());
        typedQuery.setParameter("maxLon", location.boundingCoordinates(distance).get(1).getRadLongitude());
        typedQuery.setParameter("radLat", location.getRadLatitude());
        typedQuery.setParameter("radLon", location.getRadLongitude());
        typedQuery.setParameter("earthRadius", location.getRADIUS());

        resultList = typedQuery.getResultList();
        return resultList;
    }
}
