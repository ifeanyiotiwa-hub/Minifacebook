package dev.decagon.facebookclone.service;

import dev.decagon.facebookclone.entity.*;
import dev.decagon.facebookclone.mapper.LikePosts;

import java.util.List;

public interface PostService {
    void addPost(User user, Post post);

    List<LikePosts> getAllPost(User user);

    void updatePost(Post post);

    void deletePost(Post post);

    Post getPostById(Long id);
}
