package dev.decagon.facebookclone.serviceimpl;


import dev.decagon.facebookclone.entity.*;
import dev.decagon.facebookclone.mapper.LikePosts;
import dev.decagon.facebookclone.repository.*;
import dev.decagon.facebookclone.service.PostService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    private final LikeRepository likeRepository;

    private final CommentRepository commentRepository;

    public PostServiceImp(PostRepository postRepository, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addPost(User user, Post post) {
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<LikePosts> getAllPost(User user) {
        List<LikePosts> listOfLikePosts = new LinkedList<>();
        List<Post> listOfPost = postRepository.findAllByPostIdIsNotNullOrderByPostIdDesc();

        for(Post post: listOfPost){
            LikePosts likePost = new LikePosts();

            likePost.setPostId(post.getPostId());
            likePost.setTitle(post.getTitle());
            likePost.setBody(post.getBody());
            likePost.setUser(post.getUser());

            List<PostLikes> listOfLikes = likeRepository.findAllByPost(post);
            List<Comment> listOfComments = commentRepository.findCommentByPost(post);
            likePost.setListOfComments(listOfComments);

            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + listOfComments);
            likePost.setPostLikes(listOfLikes);

            List<PostLikes> listOfPostLikes = likeRepository.findAllByPost(post);

            for(PostLikes likes: listOfPostLikes){
                if(likes.getUser().getUserId().equals(user.getUserId())){
                    likePost.setLikedPost(true);
                    break;
                }
            }

            listOfLikePosts.add(likePost);
        }

        return listOfLikePosts;
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findByPostId(id);
    }

}
