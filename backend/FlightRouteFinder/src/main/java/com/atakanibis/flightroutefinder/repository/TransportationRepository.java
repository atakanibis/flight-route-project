package com.atakanibis.flightroutefinder.repository;

import com.atakanibis.flightroutefinder.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {
}
