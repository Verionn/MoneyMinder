package com.minder.MoneyMinder.services.implementations;

import com.minder.MoneyMinder.repositories.ListRepository;
import com.minder.MoneyMinder.controllers.list.dto.UpdateListRequestBody;
import com.minder.MoneyMinder.models.ListEntity;
import com.minder.MoneyMinder.services.ListService;
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
        return listRepository.save(updateListEntity(listEntity));
    }

    public void deleteList(Long listId) {
        listRepository.deleteById(listId);
    }

    public Optional<ListEntity> updateList(Long listId, UpdateListRequestBody updateListRequestBody) {
        return listRepository.findById(listId)
                .map(listEntity -> updateListEntity(listEntity, updateListRequestBody))
                .map(listRepository::save);
    }

    public double getFullPrice(Long listId) {
        return listRepository.findTotalAmountByListId(listId);
    }

    public boolean existsById(Long listId) {
        return listRepository.existsById(listId);
    }

    private ListEntity updateListEntity(ListEntity listEntity, UpdateListRequestBody updateListRequestBody) {
        listEntity.setName(updateListRequestBody.name());
        if (updateListRequestBody.description() == null) {
            listEntity.setDescription("");
        } else {
            listEntity.setDescription(updateListRequestBody.description());
        }
        return listEntity;
    }

    private ListEntity updateListEntity(ListEntity listEntity) {
        if (listEntity.getDescription() == null) {
            listEntity.setDescription("");
        }
        listEntity.setDescription(listEntity.getDescription());
        return listEntity;
    }
}
