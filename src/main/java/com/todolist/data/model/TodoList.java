package com.todolist.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo_list")
public class TodoList {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private User owner;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="todoList", cascade={CascadeType.ALL})
    private List<TodoItem> items = new ArrayList<>();

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

    public List<TodoItem> getItems() {
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
