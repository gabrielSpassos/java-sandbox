package com.gabrielspassos.service;

import com.gabrielspassos.api.request.PetRequest;
import com.gabrielspassos.domain.PetEntity;
import com.gabrielspassos.dto.PetDTO;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.gabrielspassos.constant.ErrorsCodes.PET_NOT_FOUND;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetDTO createPet(PetRequest request) {
        var petEntity = new PetEntity();
        petEntity.setId(UUID.randomUUID().toString());
        petEntity.setName(request.getName());
        petEntity.setCreatedAt(Timestamp.from(ZonedDateTime.now().toInstant()));
        petEntity.setIsNew(true);

        var savedPet = petRepository.save(petEntity);
        return new PetDTO(savedPet);
    }

    public List<PetDTO> findPets() {
        return StreamSupport.stream(petRepository.findAll().spliterator(), false)
                .map(PetDTO::new)
                .collect(Collectors.toList());
    }

    public PetDTO findPetById(String id) {
        var petEntity = findById(id);
        return new PetDTO(petEntity);
    }

    public PetDTO updatePet(String id, PetRequest request) {
        var petEntity = findById(id);

        petEntity.setName(request.getName());

        var updatedPet = petRepository.save(petEntity);
        return new PetDTO(updatedPet);
    }

    public PetDTO deletePerson(String id) {
        var petEntity = findById(id);
        petRepository.delete(petEntity);
        return new PetDTO(petEntity);
    }

    private PetEntity findById(String id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PET_NOT_FOUND, String.format("pet not found with id: %s", id)));
    }
}
