package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.dto.ResponseDTO;
import dev.decagon.facebookclone.entity.*;
import dev.decagon.facebookclone.mapper.LikePosts;
import dev.decagon.facebookclone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    private final LikeService likeService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }



    //GEt HomePage
    @GetMapping("/home")
    public String getHomePage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("logUser");

        if(user == null) return "redirect:/";

        model.addAttribute("post", new Post());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("loggedUser", user);
        model.addAttribute("postLikes", new PostLikes());
        model.addAttribute("postDelete", new Post());

        List<LikePosts> listOfPost = postService.getAllPost(user);
        model.addAttribute("listOfAllPost", listOfPost);

        return "home";
    }


    @GetMapping("/update-post")
    public String getUpdatePostPage(Model model, HttpSession session,  Long postId) {
        User user = (User) session.getAttribute("logUser");

        if(user == null) return "redirect:/";

        Post post = postService.getPostById(postId);

        model.addAttribute("editpost", post);
        model.addAttribute("loggedUser", user);

        return "update-post";
    }


    @PostMapping("/home-post")
    public String createPost(HttpSession httpSession, @ModelAttribute("post") Post post){
        User user = (User) httpSession.getAttribute("logUser");
        postService.addPost(user, post);
        return "redirect:/home";
    }


    @PostMapping("/update")
    public String updatePost(HttpSession session, Post post){
        Post newPost = postService.getPostById(post.getPostId());

        newPost.setBody(post.getBody());

        postService.updatePost(newPost);

        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String deletePost(RedirectAttributes redirectAttributes,
                             HttpSession httpSession,
                             @PathVariable("id") Long id){
        User user = (User) httpSession.getAttribute("logUser");
        ResponseDTO response = new ResponseDTO();
        if(user == null){
            return  "redirect:/index";
        }

        Post post = postService.getPostById(id);
        commentService.deleteAllCommentsInPost(post);
        likeService.deleteAllLikesInPost(post);
        postService.deletePost(post);

        redirectAttributes.addFlashAttribute("message", response.getMessage());

        return "redirect:/home";
    }
}
