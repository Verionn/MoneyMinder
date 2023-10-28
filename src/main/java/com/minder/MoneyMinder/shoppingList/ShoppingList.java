package com.minder.MoneyMinder.shoppingList;

import com.minder.MoneyMinder.item.ShoppingItem;

import java.util.ArrayList;

public class ShoppingList {
    String name;
    ArrayList<ShoppingItem> items;

    public ShoppingList(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nazwa listy: " + name;
    }
}
