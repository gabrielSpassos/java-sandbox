package com.gabrielspassos.api;

import com.gabrielspassos.api.request.PetRequest;
import com.gabrielspassos.dto.PetDTO;
import com.gabrielspassos.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDTO> createPet(@RequestBody PetRequest request) {
        PetDTO petDTO = petService.createPet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(petDTO);
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> findPets() {
        List<PetDTO> pets = petService.findPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable String id) {
        PetDTO petDTO = petService.findPetById(id);
        return ResponseEntity.ok(petDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable String id, @RequestBody PetRequest request) {
        PetDTO petDTO = petService.updatePet(id, request);
        return ResponseEntity.ok(petDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PetDTO> deletePerson(@PathVariable String id) {
        PetDTO petDTO = petService.deletePerson(id);
        return ResponseEntity.ok(petDTO);
    }
}
