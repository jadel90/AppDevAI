package com.example.demo.repository;

import com.example.demo.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
    // Find households with no pets.
    List<Household> findByPetsIsNull();

    // Find households by the number of occupants.
    List<Household> findByNumberOfOccupants(int numberOfOccupants);

    List<Household> findByPetsIsEmpty();
}