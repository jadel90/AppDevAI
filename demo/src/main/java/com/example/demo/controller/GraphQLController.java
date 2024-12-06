package com.example.demo.controller;


import com.example.demo.dto.StatisticsDTO;
import com.example.demo.entity.Household;
import com.example.demo.entity.Pet;
import com.example.demo.input.HouseholdInput;
import com.example.demo.service.HouseholdService;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PetService petService;

    // Query: Get all households
    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    // Query: Get all pets by animal type
    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    // Query: Get a specific household
    @QueryMapping
    public Household getHousehold(Long eircode) {
        return householdService.getHouseholdById(eircode);
    }

    // Query: Get a specific pet
    @QueryMapping
    public Pet getPet(Long id) {
        return petService.getPetById(id);
    }

    // Query: Get statistics
    @QueryMapping
    public StatisticsDTO getStatistics() {
        return householdService.getStatistics();
    }

    // Mutation: Create a new household
    @MutationMapping
    public Household createHousehold(HouseholdInput input) {
        Household household = new Household();
        household.setEircode(input.getEircode());
        household.setNumberOfOccupants(input.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(input.getMaxNumberOfOccupants());
        household.setOwnerOccupied(input.isOwnerOccupied());
        return householdService.createHousehold(household);
    }

    // Mutation: Delete a household by ID
    @MutationMapping
    public String deleteHouseholdById(Long eircode) {
        householdService.deleteHousehold(eircode);
        return "Household deleted successfully.";
    }

    // Mutation: Delete a pet by ID
    @MutationMapping
    public String deletePetById(Long id) {
        petService.deletePetById(id);
        return "Pet deleted successfully.";
    }
}
