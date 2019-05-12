package com.todolist.service;

import com.todolist.data.model.User;
import com.todolist.data.repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<User> result = userServiceImpl.findAll();
        Assertions.assertThat(Arrays.asList(new User()).equals(result));
    }

    @Test
    void testSave() {
        User result = userServiceImpl.save(new User());
        Assertions.assertThat(new User().equals(result));
    }

    @Test
    void testFindByEmailAndPassword() {
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(new User());

        User result = userServiceImpl.findByEmailAndPassword("email", "password");
        Assertions.assertThat(new User().equals(result));
    }

    @Test
    void testFindBySessionId() {
        when(userRepository.findBySessionId(anyString())).thenReturn(new User());

        User result = userServiceImpl.findBySessionId("sessionId");
        Assertions.assertThat(new User().equals(result));
    }
}