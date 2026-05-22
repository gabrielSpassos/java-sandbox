package com.gabrielspassos.controller.v1;

import com.gabrielspassos.controller.v1.request.ListRequest;
import com.gabrielspassos.controller.v1.response.ListResponse;
import com.gabrielspassos.mapper.ListMapper;
import com.gabrielspassos.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping("/users/{userId}/lists")
    public ResponseEntity<ListResponse> createList(@PathVariable String userId, @RequestBody ListRequest request) {
        var list = listService.create(userId, request.name());
        var listResponse = ListMapper.toResponse(list);
        return ResponseEntity.status(HttpStatus.CREATED).body(listResponse);
    }

    @GetMapping("/users/{userId}/lists")
    public ResponseEntity<ListResponse> findByUserId(@PathVariable String userId) {
        var list = listService.findByUserId(userId);
        var listResponse = ListMapper.toResponse(list);
        return ResponseEntity.ok(listResponse);
    }

}
