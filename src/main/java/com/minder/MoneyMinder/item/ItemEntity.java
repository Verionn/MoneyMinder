package com.minder.MoneyMinder.item;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemId;
    Long listId;
    String name;
    double price;
    int amount;
    String category;
    long weight;
    LocalDateTime timeCreated;

    public ItemEntity() {
    }

    public Long getItemId() {
        return itemId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime creationDate) {
        this.timeCreated = creationDate;
    }

    @Override
    public String toString() {
        return "Item: " + name + "\nID: " + itemId + "\nLista: " + listId + "\nIlosc: " + amount + "\nCena: " + price + "\nKategoria: " + category + "\n\n";
    }
}
