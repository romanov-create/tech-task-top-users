package com.example.techtask.controller;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/setinfo")
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return userService.setInfo(user.getUserId(), user.getLevelId(), user.getResult());
    }

    @GetMapping("/userinfo/{userId}")
    @ResponseBody
    public List<User> getUserInfoTop20(@PathVariable int userId) {
        return userService.getUserInfoTop20(userId);
    }

    @GetMapping("/levelinfo/{levelId}")
    @ResponseBody
    public List<User> getLevelInfoTop20(@PathVariable int levelId) {
        return userService.getLevelInfoTop20(levelId);
    }

}
