package com.example.demo.dto;

public class PetDTO {

    private String name; // Name of the pet
    private String animalType; // Type of animal (e.g., Dog, Cat)
    private String breed; // Breed of the pet

    public PetDTO(String name, String animalType, String breed) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
    }

    // Getters and setters for the fields
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
}
