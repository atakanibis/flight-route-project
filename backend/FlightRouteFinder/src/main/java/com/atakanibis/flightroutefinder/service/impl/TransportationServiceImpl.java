package com.atakanibis.flightroutefinder.service.impl;

import com.atakanibis.flightroutefinder.entity.Transportation;
import com.atakanibis.flightroutefinder.repository.TransportationRepository;
import com.atakanibis.flightroutefinder.service.TransportationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportationServiceImpl implements TransportationService {

    private final TransportationRepository transportationRepository;

    public TransportationServiceImpl(TransportationRepository transportationRepository) {
        this.transportationRepository = transportationRepository;
    }

    @Override
    public Transportation saveTransportation(Transportation transportation) {
        return transportationRepository.save(transportation);
    }

    @Override
    public Transportation updateTransportation(Long id, Transportation transportation) {
        Transportation existing = transportationRepository.findById(id).orElseThrow();
        existing.setOrigin(transportation.getOrigin());
        existing.setDestination(transportation.getDestination());
        existing.setType(transportation.getType());
        existing.setOperatingDays(transportation.getOperatingDays());
        return transportationRepository.save(existing);
    }

    @Override
    public void deleteTransportation(Long id) {
        transportationRepository.deleteById(id);
    }

    @Override
    public Transportation getTransportationById(Long id) {
        return transportationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }
}
