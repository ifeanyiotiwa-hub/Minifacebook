package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.entity.*;
import dev.decagon.facebookclone.repository.CommentRepository;
import dev.decagon.facebookclone.repository.PostRepository;
import dev.decagon.facebookclone.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    private final PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping("/editcomment")
    public String getEditCommentPage(Model model, HttpSession session, Long commentId) {
        User user = (User) session.getAttribute("logUser");
        if(user == null) return "redirect:/";

//        Comment comment = commentService.getCommentById(commentId);

        var comment = commentRepository.findByCommentId(commentId);
        model.addAttribute("editcomment", comment);
        model.addAttribute("loggedUser", user);

        return "editcomment";
    }


    @GetMapping("/comments")
    public String getAllCommentsPage(Model model, HttpSession session, Long postId) {
        User user = (User) session.getAttribute("logUser");

        if(user == null) return "redirect:/";

        var pst = postRepository.findByPostId(postId);
        Post post = postService.getPostById(postId);

        var comments = commentService.findCommentByPost(pst);

        model.addAttribute("allComments", comments);
        model.addAttribute("loggedUser", user);
        model.addAttribute("commentDelete", new Comment());

        return "comments";
    }


    @PostMapping("/home-comment")
    public String createComment(@ModelAttribute("newComment") Comment comment, HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("logUser");
        if(user == null) return "redirect:/";

        Long id = Long.parseLong(request.getParameter("postid"));

        Post post = postService.getPostById(id);

        Comment newComment = new Comment(comment.getCommentBody(), post, user);

        commentService.saveComment(newComment);

        postService.getPostById(id).getListOfComments().add(newComment);

        return "redirect:/home";

    }


    @PostMapping("/update-comment")
    public String updateComment(@ModelAttribute Comment comment){
        System.err.println("in edit comment");
        System.err.println(comment);
        Comment newComment = commentService.getCommentById(comment.getCommentId());
        newComment.setCommentBody(comment.getComments());

        commentService.updateComment(newComment);

        return "redirect:/home";

    }


    @GetMapping("/delete-comment")
    public String deleteComment(@ModelAttribute("commentDelete") Comment comment){
        commentService.deleteComment(comment.getCommentId());
        return "redirect:/home";
    }

}
