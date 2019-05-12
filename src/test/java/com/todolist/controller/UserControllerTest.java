package com.todolist.controller;

import com.todolist.data.model.User;
import com.todolist.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        when(userService.save(any())).thenReturn(new User());

        ResponseEntity result = userController.create(new User());
        Assert.assertEquals(result, new User());
    }

    @Test
    public void testList() {
        when(userService.findAll()).thenReturn(Arrays.asList(new User()));

        ResponseEntity result = userController.list();
        Assert.assertEquals(result, Arrays.asList(new User()));
    }

    @Test
    public void testGet() {
        when(userService.findBySessionId(anyString())).thenReturn(new User());

        ResponseEntity result = userController.get("sessionId");
        Assert.assertEquals(result, new User());
    }
}