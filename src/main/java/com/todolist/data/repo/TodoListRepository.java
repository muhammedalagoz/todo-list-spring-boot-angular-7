package com.todolist.data.repo;

import com.todolist.data.model.TodoList;
import com.todolist.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {
    List<TodoList> findByOwner(User user);
    TodoList findByOwnerAAndId(User user, Long id);
}
