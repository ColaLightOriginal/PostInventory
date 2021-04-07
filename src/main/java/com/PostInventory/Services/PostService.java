package com.PostInventory.Services;

import com.PostInventory.Classes.ImageUrls;
import com.PostInventory.Classes.LikesUnlikes;
import com.PostInventory.Classes.Post;
import com.PostInventory.Repositories.ImageUrlsRepository;
import com.PostInventory.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageUrlsRepository imageUrlsRepository;

    public List<Post> getPosts(){
        return postRepository.getAll();
    }

    public Post getPostByPostId(int postId){
        return postRepository.getByPostId(postId);
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
        Boolean isPostHasOperation = postRepository.checkIfPostHasUserOperation(operationUser, postId);

        if(!isPostHasOperation) postRepository.addLikeOrUnlike(likesUnlikes);
        else {
            Boolean likeOrUnlike = postRepository.getLikesUnlikeUserPostOperation(operationUser, postId);
            if(likeOrUnlike && likesUnlikes.getOperation() ||
                (!likeOrUnlike && !likesUnlikes.getOperation())) return;

            postRepository.updateLikesUnlikesOperation(likesUnlikes,
                    postRepository.getLikesUpdatesIdByUserPostId(userId,postId));
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
