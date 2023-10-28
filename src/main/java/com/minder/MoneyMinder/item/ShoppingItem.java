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
            strategy = GenerationType.AUTO,
            generator = "shoppingitem_sequence"
    )

    long id;
    long shoppingListID;
    String name;
    double price;
    int amount;
    String category;

    public ShoppingItem(String name, double price, int amount, String category, long shoppingListID) {
        this.shoppingListID = shoppingListID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public ShoppingItem() {
    }

    @Override
    public String toString() {
        return "item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
