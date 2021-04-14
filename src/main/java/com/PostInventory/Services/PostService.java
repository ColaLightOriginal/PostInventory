package com.PostInventory.Services;

import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Classes.LikesUnlikes;
import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.ImageUrlsRepository;
import com.PostInventory.Repositories.PostRepository;
import com.PostInventory.Wrappers.PostImagesWrapper;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageUrlsRepository imageUrlsRepository;
    @Autowired
    @PersistenceContext
    private EntityManager sessionFactory;


    public List<Post> getPosts(){
        return postRepository.getAll();
    }

    public Post getPostByPostId(int postId){
        return postRepository.getByPostId(postId);
    }

    public PostImagesWrapper getPostByPostIdWithImageUrls(int postId){
        Post post = postRepository.getByPostId(postId);
        List<ImageUrls> imageUrls = imageUrlsRepository.getPostImagesUrlsList(postId);
        return new PostImagesWrapper(post,imageUrls);
    }

    public List<Post> getUserPosts(int userId){
        return postRepository.getUserPosts(userId);
    }

    public void createPost(Post post) throws Exception { postRepository.createPost(post); }

    public void deletePost(int postId){
        postRepository.deletePost(postId);
    }

    public void modifyPost(Post post){ postRepository.modifyPost(post);}

    public List<Post> getPostsFromCoord(Float latitude, Float longitude,
                                         Float latitudeDelta, Float longitudeDelta){
        List<Post> postsList = this.getPosts();
        List<Post> returnList = new LinkedList<>();
        float postLatitude;
        float postLongitude;
        for(Post post: postsList){
            postLatitude=Float.parseFloat(post.getCoordinateX());
            postLongitude=Float.parseFloat(post.getCoordinateY());

            if((postLatitude>latitude-latitudeDelta && postLatitude<latitude+latitudeDelta) &&
                    (postLongitude>longitude-longitudeDelta && postLongitude<longitude+longitudeDelta)){
                returnList.add(post);
            }
        }
        return returnList;
    }

    public void addOrUpdateLikeOrUnlike(LikesUnlikes likesUnlikes){
        int operationUser = likesUnlikes.getUserId();
        int postId = likesUnlikes.getPostId();
        int userId = likesUnlikes.getUserId();
        Post post = postRepository.getByPostId(likesUnlikes.getPostId());
        Boolean operation = likesUnlikes.getOperation();

        Boolean isPostHasOperation = postRepository.checkIfPostHasUserOperation(operationUser, postId);

        likesUnlikes.log();
        if(!isPostHasOperation) {
            postRepository.addLikeOrUnlike(likesUnlikes);
            if(operation) {
                post.setLikesCount(post.getLikesCount()+1);
            } else post.setLikesCount(post.getUnlikesCount()+1);
            postRepository.modifyPost(post);
        }

        else {
            LikesUnlikes actualUserLikesUnlikes =  postRepository.getLikesUnlikeUserPostOperation(operationUser, postId);
            System.out.println("ActualUserLikesUnlikes ");
            actualUserLikesUnlikes.log();

            Boolean userActualPostOperation = actualUserLikesUnlikes.getOperation();
            if(userActualPostOperation && operation ||
                (!userActualPostOperation && !operation)) return;
            else if(userActualPostOperation && !operation){
                post.setLikesCount(post.getUnlikesCount()+1);
                post.setUnlikesCount(post.getLikesCount()-1);
            }
            else{
                post.setLikesCount(post.getUnlikesCount()-1);
                post.setUnlikesCount(post.getLikesCount()+1);
            }

            Session session = sessionFactory.unwrap(Session.class);
            session.beginTransaction();
            LikesUnlikes tmp = session.get(LikesUnlikes.class, actualUserLikesUnlikes.getId());
            session.saveOrUpdate(tmp);
            Post tmp2 = session.get(Post.class, post.getId());
            session.saveOrUpdate(tmp2);
            session.getTransaction().commit();
            session.close();
//            postRepository.modifyLikesUnlikesOperation(actualUserLikesUnlikes);
//            postRepository.modifyPost(post);
        }
    }

    public void createPostWithImages(Post post, List<ImageUrls> imageUrls) throws Exception{
        postRepository.createPost(post);
        for(ImageUrls i: imageUrls) imageUrlsRepository.createPostImageUrl(i, post.getId());
    }

    public void addImageToPost(List<ImageUrls> imageUrls){
        for(ImageUrls i: imageUrls) imageUrlsRepository.createPostImageUrl(i, i.getPostId());
    }
}
