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

    public List<ListEntity> getLists(Long userId) {
        return listRepository.findAllByUserId(userId);
    }

    public ListEntity addList(ListEntity listEntity, Long userId) {
        return listRepository.save(updateListEntity(listEntity, userId));
    }

    public void deleteList(Long listId) {
        listRepository.deleteById(listId);
    }

    public Optional<ListEntity> updateList(Long listId, UpdateListRequestBody updateListRequestBody) {
        return listRepository.findById(listId)
                .map(listEntity -> updateListEntity(listEntity, updateListRequestBody))
                .map(listRepository::save);
    }

    public double getFullPrice(Long listId, Long userId) {
        return listRepository.findTotalAmountByListIdAndUserId(listId, userId);
    }

    public boolean existsByListIdAndUserId(Long listId, Long userId) {
        return listRepository.existsByListIdAndUserId(listId, userId);
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

    private ListEntity updateListEntity(ListEntity listEntity, Long userId) {
        if (listEntity.getDescription() == null) { //TODO:
            listEntity.setDescription("");          //idk po co to jest, walidacja w liscie powinna byc zeby to nie przeszlo i u gory tak samo
        }
        listEntity.setDescription(listEntity.getDescription());
        listEntity.setUserId(userId);
        return listEntity;
    }
}
