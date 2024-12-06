package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
// did not include @Table(name="pets")

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for a pet.

    @Column(nullable = false)
    private String name; // Name of the pet.

    @Column(nullable = false)
    private String animalType; // Type of animal (e.g., Dog, Cat).

    private String breed; // Breed of the pet.

    private int age; // Age of the pet.

    @ManyToOne
    @JoinColumn(name = "household_id", nullable = false)
    private Household household; // The household to which the pet belongs.

    // Ensure that every pet must belong to a household.


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

}
