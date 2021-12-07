package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.dto.LogInDto;
import dev.decagon.facebookclone.dto.ResponseDTO;
import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.service.UserService;

import dev.decagon.facebookclone.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Method to return the registration View page
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/")
    public String getLoginPage(Model model){
        model.addAttribute("user",new LogInDto());
        return "index";
    }

    @GetMapping("/userprofile")
    public String gotoUserProfile(Model model, HttpSession session){
        User user = (User) session.getAttribute("logUser");
        if(user == null)
            return "/index";

        model.addAttribute("loggedUser", user);


        return "userprofile";
    }


    @PostMapping("/register")
    public String registerUser(User user, Model model,
                               RedirectAttributes redirectAttributes){
        ResponseDTO response = userService.addUser(user);

        if(response.getStatus()){
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/";
        }
        model.addAttribute("message", response.getMessage());
        return "register";
    }

    @PostMapping("/login")
    public String logInUser(User user, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseDTO response = userService.logInUser(user);

        if(response.getStatus()){
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            redirectAttributes.addFlashAttribute("user", response.getData());
            session.setAttribute("logUser", response.getData());
            return "redirect:/home";
        }
        model.addAttribute("message", response.getMessage());
        return "index";
    }

    @GetMapping("/logout")
    public String logUserOut(Model model, HttpSession session){
        if(session != null){
            session.invalidate();
        }

        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "redirect:/";
    }
}
