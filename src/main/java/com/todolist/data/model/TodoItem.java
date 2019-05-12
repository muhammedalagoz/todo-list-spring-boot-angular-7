package com.todolist.data.model;

import javax.persistence.*;

@Entity
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
