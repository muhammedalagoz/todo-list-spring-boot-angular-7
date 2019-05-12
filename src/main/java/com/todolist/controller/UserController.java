package com.todolist.controller;

import com.todolist.data.model.User;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/user-list")
    public ResponseEntity list(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            String sessionId = UUID.randomUUID().toString();
            user.setSessionId(sessionId);
            userService.save(user);

            return ResponseEntity.ok(sessionId);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("{sessionId}")
    public ResponseEntity get(@PathVariable String sessionId){
        return ResponseEntity.ok(userService.findBySessionId(sessionId));
    }
}