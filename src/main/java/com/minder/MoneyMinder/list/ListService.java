package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
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

        double fullPrice = updateListRequestBody.fullPrice();
        String name = updateListRequestBody.name();

        if (fullPrice >= 0) {
            listEntity.setFullPrice(fullPrice);
        }

        if (name != null && !name.isEmpty() && !listEntity.getName().equals(name)) {
            listEntity.setName(name);
        }

        return listEntity;
    }

    public boolean existsById(Long listId) {
        return listRepository.existsById(listId);
    }
}
