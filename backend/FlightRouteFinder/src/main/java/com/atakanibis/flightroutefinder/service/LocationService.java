package com.atakanibis.flightroutefinder.service;

import com.atakanibis.flightroutefinder.entity.Location;

import java.util.List;

public interface LocationService {
    Location saveLocation(Location location);
    Location updateLocation(Long id, Location location);
    void deleteLocation(Long id);
    Location getLocationById(Long id);
    List<Location> getAllLocations();
}
