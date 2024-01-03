package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.list.dto.UpdateListRequestBody;
import com.minder.MoneyMinder.models.ListEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {

    Optional<ListEntity> getList(Long listId);

    List<ListEntity> getLists(Long userId);

    ListEntity addList(ListEntity listEntity, Long userId);

    void deleteList(Long listId);

    Optional<ListEntity> updateList(Long listId, UpdateListRequestBody updateListRequestBody);

    double getFullPrice(Long listId, Long userId);

    boolean existsByListIdAndUserId(Long listId, Long userId);
}
