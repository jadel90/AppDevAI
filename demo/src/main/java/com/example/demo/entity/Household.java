package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Household {

    @Id
    @Column(length = 8)
    private String eircode; // Primary key for household

    @Column(name = "number_of_occupants", nullable = false)
    private int numberOfOccupants; // Current number of occupants

    @Column(name = "max_number_of_occupants", nullable = false)
    private int maxNumberOfOccupants; // Maximum allowed number of occupants

    @Column(name = "owner_occupied", nullable = false)
    private boolean ownerOccupied; // Indicates if the household is owner-occupied

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_eircode", referencedColumnName = "eircode", nullable = true)
    private Household parentHousehold; // Reference to the parent household

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets; // List of pets associated with the household

    // Getters and Setters
    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public int getNumberOfOccupants() {
        return numberOfOccupants;
    }

    public void setNumberOfOccupants(int numberOfOccupants) {
        this.numberOfOccupants = numberOfOccupants;
    }

    public int getMaxNumberOfOccupants() {
        return maxNumberOfOccupants;
    }

    public void setMaxNumberOfOccupants(int maxNumberOfOccupants) {
        this.maxNumberOfOccupants = maxNumberOfOccupants;
    }

    public boolean isOwnerOccupied() {
        return ownerOccupied;
    }

    public void setOwnerOccupied(boolean ownerOccupied) {
        this.ownerOccupied = ownerOccupied;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Household getParentHousehold() {
        return parentHousehold;
    }

    public void setParentHousehold(Household parentHousehold) {
        this.parentHousehold = parentHousehold;
    }
}
