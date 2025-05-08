package com.atakanibis.flightroutefinder.service;

import com.atakanibis.flightroutefinder.entity.Transportation;

import java.util.List;

public interface TransportationService {
    Transportation saveTransportation(Transportation transportation);
    Transportation updateTransportation(Long id, Transportation transportation);
    void deleteTransportation(Long id);
    Transportation getTransportationById(Long id);
    List<Transportation> getAllTransportations();
}
