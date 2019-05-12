package com.todolist.controller;

import com.todolist.data.model.TodoItem;
import com.todolist.data.model.TodoList;
import com.todolist.data.model.User;
import com.todolist.data.repo.TodoItemRepository;
import com.todolist.data.repo.TodoListRepository;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoItemRepository itemRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<TodoList> create(@RequestBody TodoList todoListRequest,
                                                          String sessionId) {
        TodoList todoList1 = new TodoList();
        todoList1.setName(todoListRequest.getName());
        todoList1.setOwner(userService.findBySessionId(sessionId));

        TodoList todoList = todoListRepository.save(todoList1);
        return new ResponseEntity<>(todoList, HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity list(String sessionId) {
        User user = userService.findBySessionId(sessionId);
        List<TodoList> todoLists = todoListRepository.findByOwner(user);

        return ResponseEntity.ok(todoLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity list(@PathVariable Long id, @RequestParam String sessionId) {
        User user = userService.findBySessionId(sessionId);
        TodoList todoList = todoListRepository.findByOwnerAAndId(user, id);

        return ResponseEntity.ok(todoList);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody TodoList todoListRequest) {
        return ResponseEntity.ok(todoListRepository.save(todoListRequest));
    }

    @GetMapping("/complated/{id}")
    public ResponseEntity complated(@PathVariable Long id) {
        TodoItem todoItem = itemRepository.findById(id).get();
        todoItem.setCompleted(true);
        return ResponseEntity.ok(itemRepository.save(todoItem));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

}
