package com.gabrielspassos.service;

import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.mapper.UUIDMapper;
import com.gabrielspassos.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class ListService {

    @Autowired
    private UserService userService;

    @Autowired
    private ListRepository listRepository;

    public ListEntity create(String userId, String name) {
        // create list for existing user
        var user = userService.findById(userId);

        var userIdAsUUID = UUIDMapper.toUUID(userId);
        var finalName = nonNull(name) ? name : "Untitled";

        ListEntity listEntity = new ListEntity();
        listEntity.setUserId(userIdAsUUID);
        listEntity.setName(finalName);

        return listRepository.save(listEntity);
    }

    public ListEntity findByUserId(String userId) {
        var userIdAsUUID = UUIDMapper.toUUID(userId);

        return listRepository.findByUserId(userIdAsUUID)
                .orElseThrow(() -> new NotFoundException("List not found", "LIST_NOT_FOUND"));
    }

}
