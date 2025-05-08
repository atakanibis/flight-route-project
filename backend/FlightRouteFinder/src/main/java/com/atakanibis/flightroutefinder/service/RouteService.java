package com.atakanibis.flightroutefinder.service;

import com.atakanibis.flightroutefinder.dto.RouteDto;
import com.atakanibis.flightroutefinder.entity.Location;
import com.atakanibis.flightroutefinder.entity.Transportation;

import java.time.LocalDate;
import java.util.List;

public interface RouteService {
    List<RouteDto> findValidRoutes(Location origin, Location destination, LocalDate date);
}
