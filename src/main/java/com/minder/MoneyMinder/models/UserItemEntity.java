package com.minder.MoneyMinder.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class UserItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long itemId;
    Long listId;
    Long categoryId;
    String name;
    Double price;
    Integer amount;
    Long weight;
    LocalDateTime timeCreated;
    LocalDateTime timeBought;

    public UserItemEntity(Long itemId, Long listId, Long categoryId, String name, Double price, Integer amount, Long weight, LocalDateTime timeCreated, LocalDateTime timeBought) {
        this.itemId = itemId;
        this.listId = listId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.weight = weight;
        this.timeCreated = timeCreated;
        this.timeBought = timeBought;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimeBought() {
        return timeBought;
    }

    public void setTimeBought(LocalDateTime timeBought) {
        this.timeBought = timeBought;
    }
}
