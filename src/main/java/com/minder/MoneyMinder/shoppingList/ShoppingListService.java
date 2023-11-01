package com.minder.MoneyMinder.shoppingList;

import com.minder.MoneyMinder.shoppingList.dto.UpdateShoppingListRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingListRepository.findAll();
    }

    public void addShoppingList(ShoppingList shoppingList){
        shoppingListRepository.save(shoppingList);
    }

    public void deleteShoppingList(long shoppingListID) {
        boolean exists = shoppingListRepository.existsById(shoppingListID);
        if(!exists){
            throw new IllegalStateException("List does not exist!");
        }
        shoppingListRepository.deleteById(shoppingListID);
    }

    @Transactional
    public void updateShoppingList(Long shoppingListID, UpdateShoppingListRequestBody updateShoppingListRequestBody) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListID).
                orElseThrow(() -> new IllegalStateException("List with id: " + shoppingListID + " does not exist"));

        double fullPrice = updateShoppingListRequestBody.fullPrice();
        String name = updateShoppingListRequestBody.name();

        if(fullPrice > 0){
            shoppingList.setFullPrice(fullPrice);
        }

        if(name != null && name.length() > 0 && !shoppingList.getName().equals(name)){
            shoppingList.setName(name);
        }
    }
}
