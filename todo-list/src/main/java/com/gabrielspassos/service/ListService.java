package com.gabrielspassos.service;

import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public ListEntity create(UUID userId, String name) {
        var finalName = nonNull(name) ? name : "Untitled";

        ListEntity listEntity = new ListEntity();
        listEntity.setUserId(userId);
        listEntity.setName(finalName);

        return listRepository.save(listEntity);
    }

    public ListEntity findByUserId(UUID userId) {
        return listRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("List not found", "LIST_NOT_FOUND"));
    }
}
