package com.todolist.service;

import com.todolist.data.model.User;
import com.todolist.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterator<User> iterator = userRepository.findAll().iterator();
        while (iterator.hasNext()) {
            users.add(iterator.next());
        }
        return users;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findBySessionId(String sessionId) {
        return userRepository.findBySessionId(sessionId);
    }
}
