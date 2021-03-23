package com.PostInventory.Repositories;
import com.PostInventory.Classes.LikesUnlikes;
import com.PostInventory.Classes.Post;
import com.PostInventory.Utlis.GeoLocationUtils.GeoLocation;
import com.PostInventory.Utlis.PostUtils.PostValidator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    @PersistenceContext
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
                e.printStackTrace();
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

    @Transactional
    public void createPost(Post post){
        try{
            PostValidator.validatePost(post);
            sessionFactory.persist(post);
        }catch(Exception e){
            e.printStackTrace();
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

    public void modifyPost(Post post){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            Post tmp = session.get(Post.class, post.getId());
            session.saveOrUpdate(tmp);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public List<Post> getPostsWithinDistance(GeoLocation location, double distance){
        List<Post> resultList = new LinkedList<>();
        String query = "SELECT post FROM Post post WHERE (coordinateX >=:minLat AND coordinateX <=:maxLat) AND (coordinateY >=:minLon " +
                (location.meridian180WithDistance() ? "OR" : "AND") +
                " coordinateY <=:maxLon) AND acos(sin(:radLat) * sin(coordinateX) + cos(:radLat) * cos(coordinateX) * cos(coordinateY - :radLon)) <=:earthRadius";
        TypedQuery<Post> typedQuery = sessionFactory.createQuery(query, Post.class);
        typedQuery.setParameter("minLat", Double.toString(location.boundingCoordinates(distance).get(0).getRadLatitude()));
        typedQuery.setParameter("maxLat", Double.toString(location.boundingCoordinates(distance).get(1).getRadLatitude()));
        typedQuery.setParameter("minLon", Double.toString(location.boundingCoordinates(distance).get(0).getRadLongitude()));
        typedQuery.setParameter("maxLon", Double.toString(location.boundingCoordinates(distance).get(1).getRadLongitude()));
        typedQuery.setParameter("radLat", Double.toString(location.getRadLatitude()));
        typedQuery.setParameter("radLon", Double.toString(location.getRadLongitude()));
        typedQuery.setParameter("earthRadius", location.getRADIUS());

        resultList = typedQuery.getResultList();
        return resultList;
    }

    @Transactional
    public void addLikeOrUnlike(LikesUnlikes likesUnlikes){
        try{
            sessionFactory.persist(likesUnlikes);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkIfPostHasUserOperation(int userId, int postId){
        LikesUnlikes result;
        String query = "SELECT likes_unlikes from LikesUnlikes likes_unlikes where user_id=:userId and post_id=:postId";
        TypedQuery<LikesUnlikes> typedQuery = sessionFactory.createQuery(query, LikesUnlikes.class);
        typedQuery.setParameter("userId", userId);
        typedQuery.setParameter("postId", postId);

        try{
            result = typedQuery.getSingleResult();
        } catch(NoResultException e){
            return false;
        }
        return true;
    }

    public boolean getLikesUnlikeUserPostOperation(int userId, int postId){
        LikesUnlikes result;
        String query = "SELECT likes_unlikes from LikesUnlikes likes_unlikes where user_id=:userId and post_id=:postId";
        TypedQuery<LikesUnlikes> typedQuery = sessionFactory.createQuery(query, LikesUnlikes.class);
        typedQuery.setParameter("userId", userId);
        typedQuery.setParameter("postId", postId);

        result = typedQuery.getSingleResult();
        return result.getOperation();
    }

    public void updateLikesUnlikesOperation(LikesUnlikes likesUnlikes,int likesUnlikesId){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            LikesUnlikes tmp = session.get(LikesUnlikes.class, likesUnlikesId);
            tmp.setOperation(likesUnlikes.getOperation());
            session.saveOrUpdate(tmp);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public int getLikesUpdatesIdByUserPostId(int userId, int postId){
        LikesUnlikes result;
        String query = "SELECT likes_unlikes from LikesUnlikes likes_unlikes where user_id=:userId and post_id=:postId";
        TypedQuery<LikesUnlikes> typedQuery = sessionFactory.createQuery(query, LikesUnlikes.class);
        typedQuery.setParameter("userId", userId);
        typedQuery.setParameter("postId", postId);

        result = typedQuery.getSingleResult();
        return result.getId();
    }
}
