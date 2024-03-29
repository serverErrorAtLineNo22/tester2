package com.practiseSocket.demoPractiseSocket.controller;

import com.practiseSocket.demoPractiseSocket.model.User;
import com.practiseSocket.demoPractiseSocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public String createdPage(@ModelAttribute("userBean") User user){
        userService.save(user);
        return "layout/addUser";
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
   public User disconnectUser(@Payload User user){
        userService.disconnect(user);
       return user;
   }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }

}
