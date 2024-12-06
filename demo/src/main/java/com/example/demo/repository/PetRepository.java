package com.example.demo.repository;

import com.example.demo.dto.PetStatisticsDTO;
import com.example.demo.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Find pets by their animal type, ignoring case.
    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    // Find pets by their breed, ordered by age.
    List<Pet> findByBreedOrderByAge(String breed);

    // Get pet statistics: average age, oldest age, and total count.
    @Query("SELECT PetStatisticsDTO(AVG(p.age), MAX(p.age), COUNT(p)) FROM Pet p")
    PetStatisticsDTO getPetStatistics();

    // Find pets by name, ignoring case.
    List<Pet> findByNameIgnoreCase(String name);
}
