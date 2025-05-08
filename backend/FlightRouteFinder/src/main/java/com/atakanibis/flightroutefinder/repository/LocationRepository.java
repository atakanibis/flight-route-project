package com.atakanibis.flightroutefinder.repository;

import com.atakanibis.flightroutefinder.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
