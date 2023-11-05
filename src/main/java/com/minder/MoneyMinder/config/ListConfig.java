package com.minder.MoneyMinder.config;

import com.minder.MoneyMinder.item.ItemEntity;
import com.minder.MoneyMinder.item.ItemRepository;
import com.minder.MoneyMinder.list.ListEntity;
import com.minder.MoneyMinder.list.ListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ListConfig {

    @Bean
    CommandLineRunner addSampleLists(ListRepository listRepository, ItemRepository itemRepository){
        return args -> {
            ListEntity daily = new ListEntity(
                    "Na jutro"
            );
            ListEntity weekend = new ListEntity(
                    "Na weekend"
            );

            ItemEntity bread = new ItemEntity(
                    "Bread",
                    3.20,
                    1,
                    "Food",
                    1L
            );
            ItemEntity milk = new ItemEntity(
                    "Milk",
                    3.20,
                    1,
                    "Food",
                    1L
            );
            ItemEntity potato = new ItemEntity(
                    "Potato",
                    3.20,
                    10,
                    "Food",
                    1L
            );
            ItemEntity carrot = new ItemEntity(
                    "Carrot",
                    3.20,
                    3,
                    "Food",
                    2L
            );
            ItemEntity cola = new ItemEntity(
                    "Cola",
                    3.20,
                    1,
                    "Sweets",
                    2L
            );

            listRepository.saveAll(List.of(daily, weekend));
            itemRepository.saveAll(List.of(bread, milk, potato, carrot, cola));

        };
    }
}
