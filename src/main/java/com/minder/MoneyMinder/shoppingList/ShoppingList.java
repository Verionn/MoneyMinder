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
    int shoppingListID;
    String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shoppinglist_shoppingitem",
            joinColumns = @JoinColumn(name = "shoppingListID"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<ShoppingItem> items = new HashSet<>();

    @Transient
    double fullPrice = 0;

    public ShoppingList(String name) {
        this.name = name;
    }

    public ShoppingList() {
    }

    public int getShoppingListID() {
        return shoppingListID;
    }

    public void setShoppingListID(int shoppingListID) {
        this.shoppingListID = shoppingListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(Set<ShoppingItem> items) {
        this.items = items;
    }

    public void addItems(ShoppingItem shoppingItem){
        items.add(shoppingItem);
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public String toString() {
        return "Nazwa listy: " + name + "\n";
    }
}
