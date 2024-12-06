package com.example.demo.service;

import com.example.demo.dto.StatisticsDTO;
import com.example.demo.entity.Household;
import java.util.List;
import java.util.Map;

public interface HouseholdService {
    // Create a new household in the database.
    Household createHousehold(Household household);

    // Retrieve all households in the database.
    List<Household> getAllHouseholds();

    // Retrieve a specific household by ID, with an option to include or exclude pets.
    Household getHouseholdById(Long id);

    // Update an existing household's details (e.g., address, number of occupants).
    Household updateHousehold(Long id, Household householdDetails);

    // Delete a household by ID, cascading the deletion to its pets.
    void deleteHousehold(Long id);

    // Find households with no pets.
    List<Household> findHouseholdsWithoutPets();

    // Get statistics on households (e.g., number of empty or full houses).
    Map<String, Integer> getHouseholdStatistics();


    // Get household with no pets
    List<Household> getHouseholdsWithNoPets();

    StatisticsDTO getStatistics();
}

