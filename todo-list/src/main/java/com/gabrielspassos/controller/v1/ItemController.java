package com.gabrielspassos.controller.v1;

import com.gabrielspassos.controller.v1.request.CreateItemRequest;
import com.gabrielspassos.controller.v1.request.UpdateItemRequest;
import com.gabrielspassos.controller.v1.response.ItemResponse;
import com.gabrielspassos.mapper.ItemMapper;
import com.gabrielspassos.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/lists/{listId}/items")
    public ResponseEntity<ItemResponse> createItem(@PathVariable String listId, @Valid @RequestBody CreateItemRequest request) {
        var item = itemService.create(listId, request.description());
        var itemResponse = ItemMapper.toResponse(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponse);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable String itemId, @Valid @RequestBody UpdateItemRequest request) {
        var item = itemService.updateStatus(itemId, request.status());
        var itemResponse = ItemMapper.toResponse(item);
        return ResponseEntity.ok(itemResponse);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity deleteItem(@PathVariable String itemId) {
        itemService.removeItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lists/{listId}/items")
    public ResponseEntity<List<ItemResponse>> findByListId(@PathVariable String listId) {
        var items = itemService.findByListId(listId);
        var itemsResponse = items.stream().map(ItemMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(itemsResponse);
    }
}
