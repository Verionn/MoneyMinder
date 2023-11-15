package com.minder.MoneyMinder.controllers.list;

import com.minder.MoneyMinder.controllers.list.dto.*;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.ListService;
import com.minder.MoneyMinder.services.mappers.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/lists")
public class ListController {
    private final ListService listService;
    private final ItemService itemService;
    private final ListMapper listMapper = ListMapper.INSTANCE;

    @Autowired
    public ListController(ListService listService, ItemService itemService) {
        this.itemService = itemService;
        this.listService = listService;
    }

    @GetMapping(path = "/{listId}")
    public ResponseEntity<ListResponse> getSpecificList(@PathVariable Long listId) {
        if (!checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        return listService.getList(listId).map(listRecord -> ResponseEntity.ok().body(
                        listMapper.listEntityToListResponse(listRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        if (!checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        itemService.deleteItemsByListId(listId);

        listService.deleteList(listId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{listId}")
    public ResponseEntity<ListResponse> updateList(@PathVariable("listId") Long listId,
                                                   @RequestBody UpdateListRequestBody updateListRequestBody) {
        if (!checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfDataIsCorrect(updateListRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(200).body(
                listMapper.listEntityToListResponse(listService.updateList(listId, updateListRequestBody)));
    }

    @GetMapping(path = "/{listId}/fullprice")
    public ResponseEntity<FullPriceResponse> getFullPrice(@PathVariable Long listId) {
        if (!checkIfListExits(listId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new FullPriceResponse(listService.getFullPrice(listId)));
    }

    private boolean checkIfDataIsCorrect(UpdateListRequestBody updateListRequestBody) {
        return !updateListRequestBody.name().isBlank();
    }

    private boolean checkIfListExits(Long listId) {
        return listService.existsById(listId);
    }
}
