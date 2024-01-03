package com.minder.MoneyMinder.controllers.purchasedItem.dto;

import java.time.LocalDateTime;

public record PurchasedItemResponse(Long id,
                                    Long listId,
                                    Long categoryId,
                                    Long userId,
                                    String name,
                                    Double price,
                                    Integer amount,
                                    Long weight,
                                    LocalDateTime timeCreated,
                                    LocalDateTime timeBought) {
}
