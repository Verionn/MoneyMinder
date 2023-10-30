package com.minder.MoneyMinder.config;

import com.minder.MoneyMinder.item.ShoppingItem;
import com.minder.MoneyMinder.item.ShoppingItemRepository;
import com.minder.MoneyMinder.shoppingList.ShoppingList;
import com.minder.MoneyMinder.shoppingList.ShoppingListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShoppingListConfig {

    @Bean
    CommandLineRunner addSampleLists(ShoppingListRepository shoppingListRepository, ShoppingItemRepository shoppingItemRepository){
        return args -> {
            ShoppingList daily = new ShoppingList(
                    "Na jutro"
            );
            ShoppingList weekend = new ShoppingList(
                    "Na weekend"
            );

            ShoppingItem bread = new ShoppingItem(
                    "Bread",
                    3.20,
                    1,
                    "Food",
                    1
            );
            ShoppingItem milk = new ShoppingItem(
                    "Milk",
                    3.20,
                    1,
                    "Food",
                    1
            );
            ShoppingItem potato = new ShoppingItem(
                    "Potato",
                    3.20,
                    10,
                    "Food",
                    1
            );
            ShoppingItem carrot = new ShoppingItem(
                    "Carrot",
                    3.20,
                    3,
                    "Food",
                    1
            );
            ShoppingItem cola = new ShoppingItem(
                    "Cola",
                    3.20,
                    1,
                    "Sweets",
                    1
            );

            daily.addItems(bread);
            daily.addItems(milk);
            weekend.addItems(potato);
            weekend.addItems(cola);
            weekend.addItems(carrot);

            shoppingListRepository.saveAll(List.of(daily, weekend));
            shoppingItemRepository.saveAll(List.of(bread, milk, potato, carrot, cola));

        };
    }
}
