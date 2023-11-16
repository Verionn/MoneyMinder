package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.repositories.ListRepository;
import com.minder.MoneyMinder.controllers.list.dto.UpdateListRequestBody;
import com.minder.MoneyMinder.models.ListEntity;
import com.minder.MoneyMinder.services.ListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public Optional<ListEntity> getList(Long listId) {
        return listRepository.findById(listId);
    }

    public List<ListEntity> getLists() {
        return listRepository.findAll();
    }

    public ListEntity addList(ListEntity listEntity) {
        return listRepository.save(listEntity);
    }

    public void deleteList(Long listId) {
        listRepository.deleteById(listId);
    }

    @Transactional
    public ListEntity updateList(Long listId, UpdateListRequestBody updateListRequestBody) {
        ListEntity listEntity = listRepository.findById(listId).
                orElseThrow();

        listEntity.setName(updateListRequestBody.name());

        return listEntity;
    }

    public double getFullPrice(Long listId) {
        return listRepository.findTotalAmountByListId(listId);
    }

    public boolean existsById(Long listId) {
        return listRepository.existsById(listId);
    }
}
