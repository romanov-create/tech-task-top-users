package com.example.techtask.service;

import com.example.techtask.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserInfoTop20(int userId);

    List<User> getLevelInfoTop20(int levelId);

    User setInfo(int userId, int levelId, int resul);
}
