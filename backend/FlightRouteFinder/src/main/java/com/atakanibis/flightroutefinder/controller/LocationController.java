package com.atakanibis.flightroutefinder.controller;

import com.atakanibis.flightroutefinder.entity.Location;
import com.atakanibis.flightroutefinder.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location create(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    @GetMapping("/{id}")
    public Location getById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAllLocations();
    }
}
