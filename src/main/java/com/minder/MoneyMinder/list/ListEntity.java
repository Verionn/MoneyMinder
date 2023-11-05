package com.minder.MoneyMinder.list;

import jakarta.persistence.*;

@Entity
@Table
public class ListEntity {
    @Id
    @SequenceGenerator(name = "list_sequence", sequenceName = "list_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_sequence")
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
