package com.minder.MoneyMinder.shoppingList;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    public List<String> getShoppingList() {
        List<String> shoppingLists = new ArrayList<>();
        shoppingLists.add("testowa nazwa 1");
        shoppingLists.add("testowa nazwa 2");
        shoppingLists.add("testowa nazwa 3");
        return shoppingLists;
    }
}
