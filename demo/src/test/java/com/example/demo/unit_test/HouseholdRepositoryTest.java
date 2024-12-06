package com.example.demo.unit_test;



import com.example.demo.entity.Household;
import com.example.demo.repository.HouseholdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    // Validate saving a household
    @Test
    void save_ShouldPersistHousehold() {
        Household household = new Household();
        household.setEircode("EIR12345");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);

        Household savedHousehold = householdRepository.save(household);

        // Validate the household was persisted
        assertNotNull(savedHousehold);
        assertEquals("EIR12345", savedHousehold.getEircode());
        assertEquals(3, savedHousehold.getNumberOfOccupants());
        assertEquals(5, savedHousehold.getMaxNumberOfOccupants());
    }

    // Validate retrieving a household by eircode
    @Test
    void findById_ShouldReturnHousehold_WhenHouseholdExists() {
        Household household = new Household();
        household.setEircode("EIR12345");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);
        householdRepository.save(household);

        Optional<Household> foundHousehold = householdRepository.findById(Long.valueOf("EIR12345"));

        // Validate the household was retrieved
        assertTrue(foundHousehold.isPresent());
        assertEquals(3, foundHousehold.get().getNumberOfOccupants());
        assertEquals(5, foundHousehold.get().getMaxNumberOfOccupants());
    }

    // Validate retrieving all households
    @Test
    void findAll_ShouldReturnAllHouseholds() {
        Household household1 = new Household();
        household1.setEircode("EIR12345");
        household1.setNumberOfOccupants(3);
        household1.setMaxNumberOfOccupants(5);

        Household household2 = new Household();
        household2.setEircode("EIR67890");
        household2.setNumberOfOccupants(2);
        household2.setMaxNumberOfOccupants(4);

        householdRepository.save(household1);
        householdRepository.save(household2);

        List<Household> households = householdRepository.findAll();

        // Validate the number of households retrieved
        assertEquals(2, households.size());
        assertTrue(households.stream().anyMatch(h -> h.getEircode().equals("EIR12345")));
        assertTrue(households.stream().anyMatch(h -> h.getEircode().equals("EIR67890")));
    }

    // Validate deleting a household
    @Test
    void deleteById_ShouldRemoveHousehold() {
        Household household = new Household();
        household.setEircode("EIR12345");
        household.setNumberOfOccupants(3);
        household.setMaxNumberOfOccupants(5);
        householdRepository.save(household);

        householdRepository.deleteById(Long.valueOf("EIR12345"));

        Optional<Household> deletedHousehold = householdRepository.findById(Long.valueOf("EIR12345"));

        // Validate the household was deleted
        assertFalse(deletedHousehold.isPresent());
    }
}
