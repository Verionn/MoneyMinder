package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Double.sum;

@Service
public class ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
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

    public ResponseEntity<Double> getFullPrice(Long listId) {
        return ResponseEntity.ok().body(listRepository.findTotalAmountByListId(listId));
    }

    public boolean existsById(Long listId) {
        return listRepository.existsById(listId);
    }
}
