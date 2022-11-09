package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    void getUserInfoTop20Test() {
        userService.setInfo(1, 1, 5);
        userService.setInfo(1, 2, 6);
        userService.setInfo(1, 3, 7);
        userService.setInfo(1, 4, 8);
        userService.setInfo(1, 5, 9);

        List<User> userInfoTop20 = userService.getUserInfoTop20(1);

        Assertions.assertEquals(5, userInfoTop20.size());
        Assertions.assertEquals(9, userInfoTop20.get(0).getResult());
        Assertions.assertEquals(5, userInfoTop20.get(4).getResult());
    }

    @Test
    void getLevelInfoTop20Test() {
        userService.setInfo(1, 1, 5);
        userService.setInfo(2, 1, 6);
        userService.setInfo(3, 1, 7);
        userService.setInfo(4, 1, 8);
        userService.setInfo(5, 1, 9);

        List<User> userInfoTop20 = userService.getLevelInfoTop20(1);

        Assertions.assertEquals(5, userInfoTop20.size());
        Assertions.assertEquals(9, userInfoTop20.get(0).getResult());
        Assertions.assertEquals(5, userInfoTop20.get(4).getResult());
    }

    @Test
    void setInfoTest() {
        User user = userService.setInfo(1, 2, 10);

        Assertions.assertEquals(10, user.getResult());
        Assertions.assertEquals(1, user.getUserId());
        Assertions.assertEquals(2, user.getLevelId());

    }
}