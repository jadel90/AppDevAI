package com.example.demo.service;

import com.example.demo.dto.StatisticsDTO;
import com.example.demo.entity.Household;
import com.example.demo.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    @Autowired
    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    // Create a new household in the database
    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    // Retrieve all households in the database
    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    // Retrieve a specific household by ID, with an option to include or exclude pets
    @Override
    public Household getHouseholdById(Long id) {
        return householdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Household not found with ID: " + id));
        // You can modify this to exclude pets if necessary
    }

    // Update an existing household's details (e.g., address, number of occupants)
    @Override
    public Household updateHousehold(Long id, Household householdDetails) {
        Household household = getHouseholdById(id);
        //household.setAddress(householdDetails.getAddress());
        //household.setCounty(householdDetails.getCounty());
        household.setNumberOfOccupants(householdDetails.getNumberOfOccupants());
        household.setMaxNumberOfOccupants(householdDetails.getMaxNumberOfOccupants());
        return householdRepository.save(household);
    }

    // Delete a household by ID, cascading the deletion to its pets
    @Override
    public void deleteHousehold(Long id) {
        Household household = getHouseholdById(id);
        householdRepository.delete(household);
    }

    @Override
    public List<Household> findHouseholdsWithoutPets() {
        return List.of();
    }

    // Find households with no pets
    @Override
    public List<Household> getHouseholdsWithNoPets() {
        return householdRepository.findByPetsIsEmpty();
    }

    @Override
    public StatisticsDTO getStatistics() {
        return null;
    }

    // Get statistics on households (e.g., number of empty or full houses)
    @Override
    public Map<String, Integer> getHouseholdStatistics() {
        List<Household> households = householdRepository.findAll();
        int emptyHouseholds = (int) households.stream()
                .filter(h -> h.getNumberOfOccupants() == 0)
                .count();
        int fullHouseholds = (int) households.stream()
                .filter(h -> h.getNumberOfOccupants() == h.getMaxNumberOfOccupants())
                .count();

        return Map.of(
                "emptyHouseholds", emptyHouseholds,
                "fullHouseholds", fullHouseholds
        );
    }
}
