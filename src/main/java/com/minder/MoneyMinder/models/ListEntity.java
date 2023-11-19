package com.minder.MoneyMinder.models;

import jakarta.persistence.*;

@Entity
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long listId;
    String name;
    String description;

    public ListEntity() {
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nazwa listy: " + name + "\n";
    }
}
