package com.todolist.service;

import com.todolist.data.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    User findByEmailAndPassword(String userName, String password);
    User findBySessionId(String sessionId);
}
