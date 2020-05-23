package com.PostInventory.Repositories;
import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private EntityManager sessionFactory;

    public List<Comment> getAll(){
        try {
            List<Comment> resultList = new LinkedList<>();
            String query = "select comment from Comment comment";
            TypedQuery<Comment> typedQuery = sessionFactory.createQuery(query, Comment.class);
            resultList = typedQuery.getResultList();
            return resultList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Comment> getPostComments(int postId){
        try{
            List<Comment> resultList = new LinkedList<>();
            String query = "select comment from Comment comment where postId=:postId";
            TypedQuery<Comment> typedQuery = sessionFactory.createQuery(query, Comment.class);
            typedQuery.setParameter("postId", postId);
            resultList = typedQuery.getResultList();
            return resultList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



}
