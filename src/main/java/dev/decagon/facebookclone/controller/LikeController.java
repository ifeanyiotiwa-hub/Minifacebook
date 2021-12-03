package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.service.LikeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

@Controller
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like_post")
    public @ResponseBody String likePost(HttpServletRequest request, HttpSession session){
        try{
            User user = (User) session.getAttribute("logUser");
            Long postId = Long.parseLong(request.getParameter("postId"));

            String action = request.getParameter("action");

            if(likeService.likePost(user, postId, action))
                return "successful";
        }
        catch(Exception e){
            session.setAttribute("message", "Internal Server error");
        }
        return "";
    }
}
