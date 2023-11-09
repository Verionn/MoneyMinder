package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.CreateListRequestBody;
import com.minder.MoneyMinder.list.dto.ListResponse;
import com.minder.MoneyMinder.list.dto.ListsResponse;
import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/lists")
public class ListController {
    private final ListService listService;
    private final ListMapper listMapper = ListMapper.INSTANCE;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    public ResponseEntity<ListsResponse> getLists() {
        return ResponseEntity.ok().body(
                new ListsResponse(listMapper.listOfListEntityToListOfListResponse(listService.getLists())));
    }

    @PostMapping
    public ResponseEntity<ListResponse> addList(@RequestBody CreateListRequestBody createListRequestBody) {
        return ResponseEntity.status(201).body(
                listMapper.listEntityToListResponse(listService.addList(listMapper.createListRequestBodyToListEntity(createListRequestBody))));
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

        return ResponseEntity.status(200).body(
                listMapper.listEntityToListResponse(listService.updateList(listId, updateListRequestBody)));
    }

    private boolean checkIfListExits(Long listId) {
        return listService.existsById(listId);
    }
}
