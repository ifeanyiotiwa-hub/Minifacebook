package dev.decagon.facebookclone.serviceimpl;


import dev.decagon.facebookclone.entity.*;
import dev.decagon.facebookclone.repository.*;
import dev.decagon.facebookclone.service.CommentService;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void updateComment(Comment editedComment) {
        commentRepository.save(editedComment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findByCommentId(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }

    @Override
    @Transactional
    public void deleteAllCommentsInPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }
}
