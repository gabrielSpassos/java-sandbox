package com.gabrielspassos.service;

import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.exception.BadRequestException;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public ListEntity create(String userId, String name) {
        var userIdAsUUID = toUUID(userId);
        var finalName = nonNull(name) ? name : "Untitled";

        ListEntity listEntity = new ListEntity();
        listEntity.setUserId(userIdAsUUID);
        listEntity.setName(finalName);

        return listRepository.save(listEntity);
    }

    public ListEntity findByUserId(String userId) {
        var userIdAsUUID = toUUID(userId);

        return listRepository.findByUserId(userIdAsUUID)
                .orElseThrow(() -> new NotFoundException("List not found", "LIST_NOT_FOUND"));
    }

    private UUID toUUID(String uuidAsString) {
        try {
            return UUID.fromString(uuidAsString);
        } catch (Exception e) {
            throw new BadRequestException("invalid id", "INVALID_ID");
        }
    }
}
