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

    public List<ListEntity> getList() {
        return listRepository.findAll();
    }

    public void addList(ListEntity listEntity) {
        listRepository.save(listEntity);
    }

    public void deleteList(Long listId) {
        boolean exists = listRepository.existsById(listId);
        if (!exists) {
            throw new IllegalStateException("List does not exist!");
        }
        listRepository.deleteById(listId);
    }

    @Transactional
    public void updateList(Long listId, UpdateListRequestBody updateListRequestBody) {
        ListEntity listEntity = listRepository.findById(listId).
                orElseThrow(() -> new IllegalStateException("List with id: " + listId + " does not exist"));

        double fullPrice = updateListRequestBody.fullPrice();
        String name = updateListRequestBody.name();

        if (fullPrice > 0) {
            listEntity.setFullPrice(fullPrice);
        }

        if (name != null && !name.isEmpty() && !listEntity.getName().equals(name)) {
            listEntity.setName(name);
        }
    }
}
