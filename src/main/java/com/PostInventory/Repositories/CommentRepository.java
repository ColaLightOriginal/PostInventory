package com.PostInventory.Repositories;
import com.PostInventory.Classes.Comment;
import com.PostInventory.Classes.Post;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    @PersistenceContext
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

    public Comment getCommentById(int commentId){
        try{
            Comment result = new Comment();
            String query = "select comment from Comment comment where id=:id";
            TypedQuery<Comment> typedQuery = sessionFactory.createQuery(query, Comment.class);
            typedQuery.setParameter("id", commentId);
            result = typedQuery.getSingleResult();
            return  result;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public void createComment(Comment comment){
        try{
            sessionFactory.persist(comment);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteComment(int commentId){
        try{
            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            session.delete(comment);
            session.getTransaction().commit();
            session.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
