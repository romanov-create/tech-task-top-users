package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {

    private final int limitScoreByUser = 20;
    private final int limitScoreByLevel = 20;
    private final List<User> users = new ArrayList<>();
    private final Map<Integer, ArrayList<User>> userScoreTop = new HashMap<>();
    private final Map<Integer, ArrayList<User>> levelScoreTop = new HashMap<>();

    @PostConstruct
    void initDB() {
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 50; j++) {
                User user = new User(i, j, 10 + j);
                users.add(user);

                putResultByUser(i, user);
                putUserByLevel(j, user);
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
        putUserByLevel(levelId, user);

        return user;
    }

    private ArrayList<User> getTop20ByUser(int id) {
        return users.stream()
                .filter(user -> user.getUserId() == id)
                .sorted(Comparator.comparingInt(User::getLevelId).reversed())
                .sorted(Comparator.comparingInt(User::getResult).reversed())
                .limit(limitScoreByUser)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<User> getTop20ByLevel(int id) {
        return users.stream()
                .filter(user -> user.getLevelId() == id)
                .sorted(Comparator.comparingInt(User::getUserId).reversed())
                .sorted(Comparator.comparingInt(User::getResult).reversed())
                .limit(limitScoreByLevel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void putResultByUser(int userId, User user) {
        if (userScoreTop.containsKey(userId)) {
            if (userScoreTop.get(userId).size() < limitScoreByUser) {
                userScoreTop.get(userId).add(user);
            } else {
                userScoreTop.replace(userId, getTop20ByUser(userId));
            }
        } else {
            ArrayList<User> tempList = new ArrayList<>();
            tempList.add(user);
            userScoreTop.put(userId, tempList);
        }
    }

    void putUserByLevel(int levelId, User user) {
        if (levelScoreTop.containsKey(levelId)) {
            if (levelScoreTop.get(levelId).size() < limitScoreByLevel) {
                levelScoreTop.get(levelId).add(user);
            } else {
                levelScoreTop.replace(levelId, getTop20ByLevel(levelId));
            }
        } else {
            ArrayList<User> tempList = new ArrayList<>();
            tempList.add(user);
            levelScoreTop.put(levelId, tempList);
        }
    }
}
