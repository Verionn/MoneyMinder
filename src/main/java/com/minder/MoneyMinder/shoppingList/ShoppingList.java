package com.minder.MoneyMinder.shoppingList;

import com.minder.MoneyMinder.item.ShoppingItem;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class ShoppingList {
    @Id
    @SequenceGenerator(
            name = "shoppinglist_sequence",
            sequenceName = "shoppinglist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shoppinglist_sequence"
    )
    int shoppingListId;
    String name;
    @Transient
    double fullPrice = 0;

    public ShoppingList(String name) {
        this.name = name;
    }

    public ShoppingList() {
    }

    public int getShoppingListID() {
        return shoppingListId;
    }

    public void setShoppingListID(int shoppingListID) {
        this.shoppingListId = shoppingListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public String toString() {
        return "Nazwa listy: " + name +
                "\n" + "full price: " + fullPrice;
    }
}
