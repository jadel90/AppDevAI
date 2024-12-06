package com.example.demo.unit_test;



import com.example.demo.entity.Household;
import com.example.demo.repository.HouseholdRepository;
import com.example.demo.service.HouseholdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseholdServiceTest {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private Household testHousehold;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testHousehold = new Household();
        testHousehold.setNumberOfOccupants(3);
        testHousehold.setMaxNumberOfOccupants(5);
    }

    @Test
    void createHousehold_ShouldReturnSavedHousehold() {
        when(householdRepository.save(testHousehold)).thenReturn(testHousehold);

        Household createdHousehold = householdService.createHousehold(testHousehold);

        assertNotNull(createdHousehold);
        assertEquals(3, createdHousehold.getNumberOfOccupants());
        verify(householdRepository, times(1)).save(testHousehold);
    }

    @Test
    void getAllHouseholds_ShouldReturnListOfHouseholds() {
        when(householdRepository.findAll()).thenReturn(List.of(testHousehold));

        List<Household> households = householdService.getAllHouseholds();

        assertNotNull(households);
        assertEquals(1, households.size());
        assertEquals(3, households.get(0).getNumberOfOccupants());
        verify(householdRepository, times(1)).findAll();
    }

    @Test
    void getHouseholdById_ShouldReturnHousehold_WhenHouseholdExists() {
        when(householdRepository.findById(1L)).thenReturn(Optional.of(testHousehold));

        Household foundHousehold = householdService.getHouseholdById(1L);

        assertNotNull(foundHousehold);
        assertEquals(3, foundHousehold.getNumberOfOccupants());
        verify(householdRepository, times(1)).findById(1L);
    }

    @Test
    void updateHousehold_ShouldUpdateAndReturnHousehold() {
        when(householdRepository.findById(1L)).thenReturn(Optional.of(testHousehold));
        when(householdRepository.save(testHousehold)).thenReturn(testHousehold);

        testHousehold.setNumberOfOccupants(4);
        Household updatedHousehold = householdService.updateHousehold(1L, testHousehold);

        assertNotNull(updatedHousehold);
        assertEquals(4, updatedHousehold.getNumberOfOccupants());
        verify(householdRepository, times(1)).save(testHousehold);
    }

    @Test
    void deleteHousehold_ShouldDeleteHousehold_WhenHouseholdExists() {
        when(householdRepository.findById(1L)).thenReturn(Optional.of(testHousehold));
        doNothing().when(householdRepository).delete(testHousehold);

        householdService.deleteHousehold(1L);

        verify(householdRepository, times(1)).delete(testHousehold);
    }
}
