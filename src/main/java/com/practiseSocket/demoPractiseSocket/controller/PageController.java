package com.practiseSocket.demoPractiseSocket.controller;

import com.practiseSocket.demoPractiseSocket.model.User;
import com.practiseSocket.demoPractiseSocket.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserRepo repo;

    @GetMapping("/")
    public ModelAndView homePage() {
        return new ModelAndView("index");
    }

    @GetMapping("/chat-mal")
    public ModelAndView chatPage(HttpSession session, Model model) {
//        User user= (User) session.getAttribute("userInfo");
//        model.addAttribute("user",user.getEmail());
        return new ModelAndView("layout/user-chat");
    }

    @GetMapping("/user-add")
    public ModelAndView home2Page() {
        return new ModelAndView("layout/addUser", "userBean", new User());
    }


    @PostMapping("/login")
    public String loginPage(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session) {
       User user =repo.findOneByEmail(email.trim()).orElse(null);
       if(user != null) {
           session.setAttribute("userInfo",user);
       }
       return "redirect:/user-add";
    }
}
