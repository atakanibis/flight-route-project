package com.atakanibis.flightroutefinder.controller;

import com.atakanibis.flightroutefinder.entity.Transportation;
import com.atakanibis.flightroutefinder.service.TransportationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportations")
public class TransportationController {

    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @PostMapping
    public Transportation create(@RequestBody Transportation transportation) {
        return transportationService.saveTransportation(transportation);
    }

    @PutMapping("/{id}")
    public Transportation update(@PathVariable Long id, @RequestBody Transportation transportation) {
        return transportationService.updateTransportation(id, transportation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transportationService.deleteTransportation(id);
    }

    @GetMapping("/{id}")
    public Transportation getById(@PathVariable Long id) {
        return transportationService.getTransportationById(id);
    }

    @GetMapping
    public List<Transportation> getAll() {
        return transportationService.getAllTransportations();
    }
}
