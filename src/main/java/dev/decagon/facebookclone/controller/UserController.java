package dev.decagon.facebookclone.controller;


import dev.decagon.facebookclone.dto.LogInDto;
import dev.decagon.facebookclone.entity.User;
import dev.decagon.facebookclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

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
}
