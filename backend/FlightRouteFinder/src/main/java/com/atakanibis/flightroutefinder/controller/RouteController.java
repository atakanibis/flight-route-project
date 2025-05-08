package com.atakanibis.flightroutefinder.controller;

import com.atakanibis.flightroutefinder.dto.RouteDto;
import com.atakanibis.flightroutefinder.entity.Location;
import com.atakanibis.flightroutefinder.repository.LocationRepository;
import com.atakanibis.flightroutefinder.service.RouteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;
    private final LocationRepository locationRepository;

    public RouteController(RouteService routeService, LocationRepository locationRepository) {
        this.routeService = routeService;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<RouteDto> getRoutes(
            @RequestParam Long origin,
            @RequestParam Long destination,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        Location originLoc = locationRepository.findById(origin).orElseThrow();
        Location destinationLoc = locationRepository.findById(destination).orElseThrow();
        return routeService.findValidRoutes(originLoc, destinationLoc, date);
    }
}
