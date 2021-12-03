package dev.decagon.facebookclone.mapper;

import dev.decagon.facebookclone.entity.*;
import lombok.Data;


import java.util.Collection;
import java.util.LinkedList;



public class LikePosts {

    private Long postId;
    private String title;
    private String body;
    private User user;
    private Collection<Comment> listOfComments = new LinkedList<>();
    private Collection<PostLikes> postLikes = new LinkedList<>();
    public boolean likedPost;

    public boolean getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(boolean likedPost) {
        this.likedPost = likedPost;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(Collection<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }

    public Collection<PostLikes> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Collection<PostLikes> postLikes) {
        this.postLikes = postLikes;
    }
}
