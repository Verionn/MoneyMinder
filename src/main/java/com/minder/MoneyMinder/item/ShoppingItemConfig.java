package com.minder.MoneyMinder.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShoppingItemConfig {

    @Bean
    CommandLineRunner commandLineRunner(ShoppingItemRepository shoppingItemRepository){
        return args -> {
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
            shoppingItemRepository.saveAll(List.of(bread, milk, potato, carrot, cola));
        };
    }

}
