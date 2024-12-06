package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetStatisticsDTO;
import com.example.demo.entity.Pet;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        Pet existingPet = getPetById(id);
        existingPet.setName(petDetails.getName());
        existingPet.setAnimalType(petDetails.getAnimalType());
        existingPet.setBreed(petDetails.getBreed());
        existingPet.setAge(petDetails.getAge());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long id) {
        Pet pet = getPetById(id);
        petRepository.delete(pet);
    }

    @Override
    public void deletePetsByName(String name) {
        List<Pet> pets = petRepository.findByNameIgnoreCase(name);
        petRepository.deleteAll(pets);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<Map<String, String>> getNameAndBreedOnly() {
        // Fetch all pets and map the required fields to a Map<String, String>
        return petRepository.findAll()
                .stream()
                .map(pet -> Map.of(
                        "name", pet.getName(),
                        "animalType", pet.getAnimalType(),
                        "breed", pet.getBreed()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public PetStatisticsDTO getPetStatistics() {
        return petRepository.getPetStatistics();
    }
}
