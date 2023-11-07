package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.ListResponse;
import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/lists")
public class ListController {
    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    public List<ListEntity> getLists() {
        return listService.getLists();
        //Tutaj lista obiektow
    }

    @PostMapping
    public void addList(@RequestBody ListEntity listEntity) {
        listService.addList(listEntity);
        //Tutaj tez trzeba zwrocic status 201 - Created oraz utworzony obiekt (ListResponse)
    }

    @DeleteMapping(path = "/{listId}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("listId") Long listId) {
        if (checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        listService.deleteList(listId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{listId}")
    public ResponseEntity<ListResponse> updateList(@PathVariable("listId") Long listId,
                                                   @RequestBody UpdateListRequestBody updateListRequestBody) {
        if (checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        listService.updateList(listId, updateListRequestBody);
        //zwróc obiekt który został utworzony zmapuj go do ListRepesponse
        return null;
    }

    private boolean checkIfListExits(Long listId) {
        return listService.existsById(listId);
    }
}
