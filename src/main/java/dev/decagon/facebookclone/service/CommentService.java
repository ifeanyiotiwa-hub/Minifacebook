package dev.decagon.facebookclone.service;

import dev.decagon.facebookclone.entity.*;

import java.util.List;

public interface CommentService {

    void updateComment(Comment editedComment);

    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    List<Comment> findCommentByPost(Post post);

    void deleteComment(Long commentId);

    void deleteAllCommentsInPost(Post post);
}
