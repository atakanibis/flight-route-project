package com.atakanibis.flightroutefinder.service.impl;

import com.atakanibis.flightroutefinder.entity.Location;
import com.atakanibis.flightroutefinder.repository.LocationRepository;
import com.atakanibis.flightroutefinder.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location location) {
        Location existing = locationRepository.findById(id).orElseThrow();
        existing.setName(location.getName());
        existing.setCity(location.getCity());
        existing.setCountry(location.getCountry());
        existing.setLocationCode(location.getLocationCode());
        return locationRepository.save(existing);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
