package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.UpdateListRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ListEntity> getList() {
        return listService.getList();
    }

    @PostMapping
    public void addList(@RequestBody ListEntity listEntity) {
        listService.addList(listEntity);
    }

    @DeleteMapping(path = "/{listId}")
    public void deleteList(@PathVariable("listId") Long listId) {
        listService.deleteList(listId);
    }

    @PutMapping(path = "/{listId}")
    public void updateList(@PathVariable("listId") Long listId,
                           @RequestBody UpdateListRequestBody updateListRequestBody) {
        listService.updateList(listId, updateListRequestBody);
    }
}
