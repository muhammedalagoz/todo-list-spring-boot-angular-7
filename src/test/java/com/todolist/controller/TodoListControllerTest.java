package com.todolist.controller;

import com.todolist.data.model.TodoList;
import com.todolist.data.model.User;
import com.todolist.data.repo.TodoItemRepository;
import com.todolist.data.repo.TodoListRepository;
import com.todolist.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class TodoListControllerTest {
    @Mock
    TodoListRepository todoListRepository;
    @Mock
    TodoItemRepository itemRepository;
    @Mock
    UserService userService;
    @InjectMocks
    TodoListController todoListController;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        when(userService.findBySessionId(anyString())).thenReturn(new User());

        TodoList todoList = new TodoList();
        ResponseEntity<TodoList> result = todoListController.create(todoList, "sessionId");
        Assert.assertEquals(todoList, result);
    }

    @Test
    void testList() {
        when(todoListRepository.findByOwner(any())).thenReturn(Arrays.asList(new TodoList()));
        when(userService.findBySessionId(anyString())).thenReturn(new User());

        ResponseEntity result = todoListController.list("sessionId");
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testList2() {
        when(todoListRepository.findByOwnerAAndId(any(), anyLong())).thenReturn(new TodoList());
        when(userService.findBySessionId(anyString())).thenReturn(new User());

        ResponseEntity result = todoListController.list(Long.valueOf(1), "sessionId");
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testUpdate() {
        ResponseEntity result = todoListController.update(new TodoList());
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testComplated() {
        ResponseEntity result = todoListController.complated(Long.valueOf(1));
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testDelete() {
        todoListController.delete(Long.valueOf(1));
    }
}