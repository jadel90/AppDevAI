package com.example.demo.controller;

import com.example.demo.entity.Household;
import com.example.demo.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    // Inject HouseholdService to handle business logic.
    private final HouseholdService householdService;

    @Autowired
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    // Endpoint to create a new household
    // URL: POST /api/households
    @PostMapping
    public Household createHousehold(@RequestBody Household household) {
        return householdService.createHousehold(household);
    }

    // Endpoint to retrieve all households
    // URL: GET /api/households
    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    // Endpoint to retrieve a household by ID, with an option to include/exclude pets
    // URL: GET /api/households/{id}?includePets={true/false}
    @GetMapping("/{id}")
    public Household getHouseholdById(
            @PathVariable Long id,
            @RequestParam(name = "includePets", defaultValue = "true") boolean includePets) {
        return householdService.getHouseholdById(id);
    }

    // Endpoint to update a household by ID
    // URL: PUT /api/households/{id}
    @PutMapping("/{id}")
    public Household updateHousehold(@PathVariable Long id, @RequestBody Household householdDetails) {
        return householdService.updateHousehold(id, householdDetails);
    }

    // Endpoint to delete a household by ID (cascade delete pets)
    // URL: DELETE /api/households/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHousehold(@PathVariable Long id) {
        householdService.deleteHousehold(id);
        return ResponseEntity.ok("Household deleted successfully.");
    }

    // Endpoint to retrieve households with no pets
    // URL: GET /api/households/empty
    @GetMapping("/empty")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.getHouseholdsWithNoPets();
    }

    // Endpoint to get statistics about households
    // URL: GET /api/households/statistics
    @GetMapping("/statistics")
    public Map<String, Integer> getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }
}
