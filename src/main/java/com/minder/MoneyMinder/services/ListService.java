package com.minder.MoneyMinder.services;

import com.minder.MoneyMinder.controllers.list.dto.UpdateListRequestBody;
import com.minder.MoneyMinder.models.ListEntity;

import java.util.List;
import java.util.Optional;

public interface ListService {

    public Optional<ListEntity> getList(Long listId);

    public List<ListEntity> getLists();

    public ListEntity addList(ListEntity listEntity);

    public void deleteList(Long listId);

    public Optional<ListEntity> updateList(Long listId, UpdateListRequestBody updateListRequestBody);

    public double getFullPrice(Long listId);

    public boolean existsById(Long listId);
}
