package com.minder.MoneyMinder.list;

import jakarta.persistence.*;

@Entity
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long listId;
    String name;
    @Transient
    double fullPrice = 0;

    public ListEntity(String name) {
        this.name = name;
    }

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

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Override
    public String toString() {
        return "Nazwa listy: " + name + "\n" + "full price: " + fullPrice;
    }
}
