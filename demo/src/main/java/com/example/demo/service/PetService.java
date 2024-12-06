package com.example.demo.service;


import com.example.demo.dto.PetStatisticsDTO;
import com.example.demo.entity.Pet;

import java.util.List;
import java.util.Map;

public interface PetService {
    // Create a new pet and associate it with a household.
    Pet createPet(Pet pet);

    // Retrieve all pets in the database.
    List<Pet> getAllPets();

    // Retrieve a specific pet by ID.
    Pet getPetById(Long id);

    // Update the details of an existing pet (e.g., name, age).
    Pet updatePet(Long id, Pet petDetails);

    // Delete a pet by its ID.
    void deletePetById(Long id);

    // Delete all pets with a specific name, ignoring case.
    void deletePetsByName(String name);

    // Find pets filtered by their animal type.
    List<Pet> findPetsByAnimalType(String animalType);

    // Find pets filtered by their breed, ordered by age.
    List<Pet> findPetsByBreed(String breed);

    // Retrieve only the name, animal type, and breed of all pets.
    List<Map<String, String>> getNameAndBreedOnly();

    // Get statistics about pets (e.g., average age, oldest pet).
    PetStatisticsDTO getPetStatistics();


}
