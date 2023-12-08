package com.minder.MoneyMinder.controllers.purchasedItem.dto;

import java.time.LocalDateTime;

public record PurchasedItemResponse(Long itemId,
                                    Long listId,
                                    Long categoryId,
                                    String name,
                                    Double price,
                                    Integer amount,
                                    Long weight,
                                    LocalDateTime timeCreated,
                                    LocalDateTime timeBought) {
}
