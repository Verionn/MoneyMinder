package com.minder.MoneyMinder.item;

import jakarta.persistence.*;

@Entity
@Table
public class ItemEntity {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )

    Long itemId;
    Long listId;
    String name;
    double price;
    int amount;
    String category;

    public ItemEntity(String name, double price, int amount, String category, Long listId) {
        this.listId = listId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public ItemEntity() {
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

    @Override
    public String toString() {
        return "Item: " + name + "\nID: " + itemId + "\nLista: " + listId + "\nIlosc: " + amount + "\nCena: " + price + "\nKategoria: " + category + "\n\n";
    }
}
