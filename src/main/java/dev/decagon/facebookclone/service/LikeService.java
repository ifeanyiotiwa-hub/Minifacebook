package dev.decagon.facebookclone.service;

import dev.decagon.facebookclone.entity.*;

public interface LikeService {
    boolean likePost(User user, Long postId, String action);
    void deleteAllLikesInPost(Post post);
}
