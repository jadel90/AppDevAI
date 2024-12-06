package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.service.PetService;
import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    // Inject PetService to handle business logic.
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Endpoint to create a new pet.
    // POST /api/pets
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    // Endpoint to retrieve all pets.
    // GET /api/pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Endpoint to retrieve a pet by ID.
    // GET /api/pets/{id}
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    // Endpoint to update a pet by ID.
    // PUT /api/pets/{id}
    @PutMapping("/{id}")
    public Pet updatePetById(@PathVariable Long id, @RequestBody Pet petDetails) {
        return petService.updatePet(id, petDetails);
    }

    // Endpoint to delete a pet by ID.
    // DELETE /api/pets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.ok("Pet deleted successfully.");
    }

    // Endpoint to delete pets by name.
    // DELETE /api/pets?name={name}
    @DeleteMapping
    public ResponseEntity<String> deletePetsByName(@RequestParam String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.ok("Pets with name '" + name + "' deleted successfully.");
    }

    // Endpoint to retrieve pets filtered by animal type.
    // GET /api/pets/animalType/{animalType}
    @GetMapping("/animalType/{animalType}")
    public List<Pet> getPetsByAnimalType(@PathVariable String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    // Endpoint to retrieve pets filtered by breed, ordered by age.
    // GET /api/pets/breed/{breed}
    @GetMapping("/breed/{breed}")
    public List<Pet> getPetsByBreed(@PathVariable String breed) {
        return petService.findPetsByBreed(breed);
    }

    // Endpoint to retrieve only name, animal type, and breed of all pets.
// GET /api/pets/summary
    @GetMapping("/summary")
    public List<PetDTO> getPetSummary() {
        List<Map<String, String>> rawSummary = petService.getNameAndBreedOnly();
        // Map the raw data to PetDTO objects
        return rawSummary.stream()
                .map(entry -> new PetDTO(entry.get("name"), entry.get("animalType"), entry.get("breed")))
                .collect(Collectors.toList());
    }


    // Endpoint to get pet statistics.
    // GET /api/pets/statistics
    @GetMapping("/statistics")
    public PetStatisticsDTO getPetStatistics() {
        return petService.getPetStatistics();
    }
}
