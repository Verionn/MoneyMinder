package com.minder.MoneyMinder.item;

import jakarta.persistence.*;

@Entity
@Table
public class ShoppingItem {
    @Id
    @SequenceGenerator(
            name = "shoppingitem_sequence",
            sequenceName = "shoppingitem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shoppingitem_sequence"
    )

    long id;
    long shoppingListId;
    String name;
    double price;
    int amount;
    String category;

    public ShoppingItem(String name, double price, int amount, String category, Long shoppingListId) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public ShoppingItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListID) {
        this.shoppingListId = shoppingListID;
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
        return "Item: " + name + "\nID: " + id + "\nLista: " + shoppingListId + "\nIlosc: " + amount + "\nCena: " + price + "\nKategoria: " + category + "\n\n";
    }
}
