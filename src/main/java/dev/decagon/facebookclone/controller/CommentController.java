package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.entity.*;
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
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping("/editcomment")
    public String getEditCommentPage(Model model, HttpSession session, Long id) {
        User user = (User) session.getAttribute("logUser");
        if(user == null) return "redirect:/";

        Comment comment = commentService.getCommentById(id);

        model.addAttribute("editcomment", comment);
        model.addAttribute("loggedUser", user);

        return "editcomment";
    }


    @GetMapping("/comments")
    public String getAllCommentsPage(Model model, HttpSession session, Long id) {
        User user = (User) session.getAttribute("logUser");

        if(user == null) return "redirect:/";

        Post post = postService.getPostById(id);

        var comments = commentService.findCommentByPost(post);

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
    public String updateComment(@ModelAttribute("comment") Comment comment){
        System.err.println("in edit comment");
        System.err.println(comment);
        Comment newComment = commentService.getCommentById(comment.getCommentId());
        newComment.setCommentBody(comment.getComments());

        commentService.updateComment(newComment);

        return "redirect:/home";

    }


    @PostMapping("/delete-comment")
    public String deleteComment(@ModelAttribute("commentDelete") Comment comment){
        commentService.deleteComment(comment.getCommentId());
        return "redirect:/home";
    }

}
