package com.minder.MoneyMinder.item;

import com.minder.MoneyMinder.category.Category;

public class ShoppingItem {
    String name;
    double price;
    Category category;

    public ShoppingItem(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
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
