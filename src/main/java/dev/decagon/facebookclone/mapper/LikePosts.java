package dev.decagon.facebookclone.mapper;

import dev.decagon.facebookclone.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LikePosts {

    private Long postId;
    private String title;
    private String body;
    private User user;
    private Collection<Comment> listOfComments;
    private Collection<PostLikes> postLikes;
    private boolean likedPost;

    public boolean getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(boolean likedPost) {
        this.likedPost = likedPost;
    }

}
