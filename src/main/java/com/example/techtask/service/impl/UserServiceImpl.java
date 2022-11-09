package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {

    private final int limitScoreByUser = 20;
    private final int limitScoreByLevel = 20;
    private final List<User> users = new ArrayList<>();
    private final Map<Integer, LinkedList<User>> userScoreTop = new HashMap<>();
    private final Map<Integer, LinkedList<User>> levelScoreTop = new HashMap<>();

    @PostConstruct
    void initDB() {
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 50; j++) {
                User user = new User(i, j, 10 + j);
                users.add(user);

                putResultByUser(i, user);
                putResultByLevel(j, user);
            }
        }
    }

    @Override
    public List<User> getUserInfoTop20(int userId) {
        return userScoreTop.get(userId);
    }

    @Override
    public List<User> getLevelInfoTop20(int levelId) {
        return levelScoreTop.get(levelId);
    }

    @Override
    public User setInfo(int userId, int levelId, int resul) {
        User user = new User(userId, levelId, resul);
        users.add(user);

        putResultByUser(userId, user);
        putResultByLevel(levelId, user);

        return user;
    }

    void putResultByUser(int userId, User user) {
        if (userScoreTop.containsKey(userId)) {
            LinkedList<User> userLinkedList = userScoreTop.get(userId);
            userLinkedList.add(user);

            LinkedList<User> collectTemp = userLinkedList.stream()
                    .sorted(Comparator.comparingInt(User::getLevelId).reversed())
                    .sorted(Comparator.comparingInt(User::getResult).reversed())
                    .collect(Collectors.toCollection(LinkedList::new));

            if (collectTemp.size() > limitScoreByUser) {
                collectTemp.removeLast();
            }

            userScoreTop.put(userId, collectTemp);
        } else {
            userScoreTop.put(userId, new LinkedList<>(List.of(user)));
        }
    }

    void putResultByLevel(int levelId, User user) {
        if (levelScoreTop.containsKey(levelId)) {
            LinkedList<User> userLinkedList = levelScoreTop.get(levelId);

            userLinkedList.add(user);

            LinkedList<User> collectTemp = userLinkedList.stream()
                    .sorted(Comparator.comparingInt(User::getUserId).reversed())
                    .sorted(Comparator.comparingInt(User::getResult).reversed())
                    .collect(Collectors.toCollection(LinkedList::new));

            if (collectTemp.size() > limitScoreByUser) {
                collectTemp.removeLast();
            }

            levelScoreTop.put(levelId, collectTemp);
        } else {
            levelScoreTop.put(levelId, new LinkedList<>(List.of(user)));
        }
    }
}
