package com.example.demo.dto;


public class StatisticsDTO {
    private int emptyHouseholds;
    private int fullHouseholds;
    private float averagePetAge;

    // Getters and Setters
    public int getEmptyHouseholds() {
        return emptyHouseholds;
    }

    public void setEmptyHouseholds(int emptyHouseholds) {
        this.emptyHouseholds = emptyHouseholds;
    }

    public int getFullHouseholds() {
        return fullHouseholds;
    }

    public void setFullHouseholds(int fullHouseholds) {
        this.fullHouseholds = fullHouseholds;
    }

    public float getAveragePetAge() {
        return averagePetAge;
    }

    public void setAveragePetAge(float averagePetAge) {
        this.averagePetAge = averagePetAge;
    }


}
