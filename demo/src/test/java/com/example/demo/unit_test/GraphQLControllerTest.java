package com.example.demo.unit_test;


import com.example.demo.controller.GraphQLController;
import com.example.demo.dto.StatisticsDTO;
import com.example.demo.entity.Household;
import com.example.demo.entity.Pet;
import com.example.demo.input.HouseholdInput;
import com.example.demo.service.HouseholdService;
import com.example.demo.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class GraphQLControllerTest {

    @Mock
    private HouseholdService householdService;

    @Mock
    private PetService petService;

    @InjectMocks
    private GraphQLController graphQLController;

    private Household testHousehold;
    private Pet testPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testHousehold = new Household();
        testHousehold.setEircode("EIR12345");
        testHousehold.setNumberOfOccupants(3);
        testHousehold.setMaxNumberOfOccupants(5);
        testHousehold.setOwnerOccupied(true);

        testPet = new Pet();
        testPet.setId(1L);
        testPet.setName("Buddy");
        testPet.setAnimalType("Dog");
        testPet.setBreed("Golden Retriever");
        testPet.setAge(5);
    }

    // Test: Query getAllHouseholds
    @Test
    void getAllHouseholds_ShouldReturnListOfHouseholds() {
        when(householdService.getAllHouseholds()).thenReturn(List.of(testHousehold));

        List<Household> households = graphQLController.getAllHouseholds();

        assertNotNull(households);
        assertEquals(1, households.size());
        assertEquals("EIR12345", households.get(0).getEircode());
        verify(householdService, times(1)).getAllHouseholds();
    }

    // Test: Query getAllPetsByAnimalType
    @Test
    void getAllPetsByAnimalType_ShouldReturnListOfPets() {
        when(petService.findPetsByAnimalType("Dog")).thenReturn(List.of(testPet));

        List<Pet> pets = graphQLController.getAllPetsByAnimalType("Dog");

        assertNotNull(pets);
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
        verify(petService, times(1)).findPetsByAnimalType("Dog");
    }

    // Test: Query getHousehold
    @Test
    void getHousehold_ShouldReturnHousehold() {
        when(householdService.getHouseholdById(Long.valueOf("EIR12345"))).thenReturn(testHousehold);

        Household household = graphQLController.getHousehold(Long.valueOf("EIR12345"));

        assertNotNull(household);
        assertEquals("EIR12345", household.getEircode());
        verify(householdService, times(1)).getHouseholdById(Long.valueOf("EIR12345"));
    }

    // Test: Query getPet
    @Test
    void getPet_ShouldReturnPet() {
        when(petService.getPetById(1L)).thenReturn(testPet);

        Pet pet = graphQLController.getPet(1L);

        assertNotNull(pet);
        assertEquals("Buddy", pet.getName());
        verify(petService, times(1)).getPetById(1L);
    }

    // Test: Query getStatistics
    @Test
    void getStatistics_ShouldReturnStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        when(householdService.getStatistics()).thenReturn(statisticsDTO);

        StatisticsDTO stats = graphQLController.getStatistics();

        assertNotNull(stats);
        assertEquals(2, stats.getEmptyHouseholds());
        assertEquals(3, stats.getFullHouseholds());
        assertEquals(4.5f, stats.getAveragePetAge());
        verify(householdService, times(1)).getStatistics();
    }

    // Test: Mutation createHousehold
    @Test
    void createHousehold_ShouldReturnCreatedHousehold() {
        HouseholdInput input = new HouseholdInput();
        when(householdService.createHousehold(any(Household.class))).thenReturn(testHousehold);

        Household createdHousehold = graphQLController.createHousehold(input);

        assertNotNull(createdHousehold);
        assertEquals("EIR12345", createdHousehold.getEircode());
        verify(householdService, times(1)).createHousehold(any(Household.class));
    }

    // Test: Mutation deleteHouseholdById
    @Test
    void deleteHouseholdById_ShouldReturnSuccessMessage() {
        doNothing().when(householdService).deleteHousehold(Long.valueOf("EIR12345"));

        String response = graphQLController.deleteHouseholdById(Long.valueOf("EIR12345"));

        assertEquals("Household deleted successfully.", response);
        verify(householdService, times(1)).deleteHousehold(Long.valueOf("EIR12345"));
    }

    // Test: Mutation deletePetById
    @Test
    void deletePetById_ShouldReturnSuccessMessage() {
        doNothing().when(petService).deletePetById(1L);

        String response = graphQLController.deletePetById(1L);

        assertEquals("Pet deleted successfully.", response);
        verify(petService, times(1)).deletePetById(1L);
    }
}
