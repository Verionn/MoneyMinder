package com.minder.MoneyMinder.controllers.list;

import com.minder.MoneyMinder.controllers.list.dto.*;
import com.minder.MoneyMinder.services.ItemService;
import com.minder.MoneyMinder.services.ListService;
import com.minder.MoneyMinder.services.UserService;
import com.minder.MoneyMinder.services.mappers.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/lists")
public class ListController {
    private final ListService listService;
    private final ItemService itemService;
    private final UserService userService;
    private final ListMapper listMapper = ListMapper.INSTANCE;

    @Autowired
    public ListController(ListService listService, ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.listService = listService;
        this.userService = userService;
    }

    @GetMapping(path = "/{listId}")
    public ResponseEntity<ListResponse> getList(@PathVariable Long listId) {

        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(listId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        return listService.getList(listId)
                .map(listRecord -> ResponseEntity.ok().body(listMapper.listEntityToListResponse(listRecord)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ListsResponse> getLists() {

        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(
                new ListsResponse(listMapper.listOfListEntityToListOfListResponse(
                        listService.getLists(user.getLeft().userId()))));
    }

    @PostMapping
    public ResponseEntity<ListResponse> addList(@RequestBody CreateListRequestBody createListRequestBody) {

        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfCreateListRequestBodyIsValid(createListRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(
                listMapper.listEntityToListResponse(
                        listService.addList(
                                listMapper.createListRequestBodyToListEntity(createListRequestBody), user.getLeft().userId())));
    }

    @DeleteMapping(path = "/{listId}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable("listId") Long listId) {

        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(listId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        itemService.deleteItemsByListId(listId);

        listService.deleteList(listId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{listId}")
    public ResponseEntity<ListResponse> updateList(@PathVariable("listId") Long listId,
                                                   @RequestBody UpdateListRequestBody updateListRequestBody) {

        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(listId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfUpdateListRequestBodyIsValid(updateListRequestBody)) {
            return ResponseEntity.badRequest().build();
        }

        return listService.updateList(listId, updateListRequestBody)
                .map(listEntity -> ResponseEntity.ok().body(listMapper.listEntityToListResponse(listEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{listId}/fullprice")
    public ResponseEntity<FullPriceResponse> getFullPrice(@PathVariable Long listId) {
        var user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (user.isRight()) {
            return ResponseEntity.notFound().build();
        }

        if (!checkIfListExists(listId, user.getLeft().userId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new FullPriceResponse(
                listService.getFullPrice(listId, user.getLeft().userId())));
    }

    private boolean checkIfUpdateListRequestBodyIsValid(UpdateListRequestBody updateListRequestBody) {
        return !updateListRequestBody.name().isBlank();
    }

    private boolean checkIfCreateListRequestBodyIsValid(CreateListRequestBody createListRequestBody) {
        return !createListRequestBody.name().isBlank();
    }

    private boolean checkIfListExists(Long listId, Long userId) {
        return listService.existsByListIdAndUserId(listId, userId);
    }
}
