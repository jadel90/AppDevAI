package com.example.demo.unit_test;


import com.example.demo.entity.Pet;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet testPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPet = new Pet();
        testPet.setId(1L);
        testPet.setName("Buddy");
        testPet.setAnimalType("Dog");
        testPet.setBreed("Labrador");
        testPet.setAge(3);
    }

    @Test
    void createPet_ShouldReturnSavedPet() {
        when(petRepository.save(testPet)).thenReturn(testPet);

        Pet createdPet = petService.createPet(testPet);

        assertNotNull(createdPet);
        assertEquals("Buddy", createdPet.getName());
        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void getAllPets_ShouldReturnListOfPets() {
        when(petRepository.findAll()).thenReturn(List.of(testPet));

        List<Pet> pets = petService.getAllPets();

        assertNotNull(pets);
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void getPetById_ShouldReturnPet_WhenPetExists() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(testPet));

        Pet foundPet = petService.getPetById(1L);

        assertNotNull(foundPet);
        assertEquals("Buddy", foundPet.getName());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    void updatePet_ShouldUpdateAndReturnPet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(testPet));
        when(petRepository.save(testPet)).thenReturn(testPet);

        testPet.setName("Max");
        Pet updatedPet = petService.updatePet(1L, testPet);

        assertNotNull(updatedPet);
        assertEquals("Max", updatedPet.getName());
        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void deletePetById_ShouldDeletePet_WhenPetExists() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(testPet));
        doNothing().when(petRepository).delete(testPet);

        petService.deletePetById(1L);

        verify(petRepository, times(1)).delete(testPet);
    }
}
