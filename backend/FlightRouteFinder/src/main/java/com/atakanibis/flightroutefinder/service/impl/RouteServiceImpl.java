package com.atakanibis.flightroutefinder.service.impl;

import com.atakanibis.flightroutefinder.dto.RouteDto;
import com.atakanibis.flightroutefinder.dto.StepDto;
import com.atakanibis.flightroutefinder.entity.Location;
import com.atakanibis.flightroutefinder.entity.Transportation;
import com.atakanibis.flightroutefinder.entity.TransportationType;
import com.atakanibis.flightroutefinder.repository.TransportationRepository;
import com.atakanibis.flightroutefinder.service.RouteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final TransportationRepository transportationRepository;

    public RouteServiceImpl(TransportationRepository transportationRepository) {
        this.transportationRepository = transportationRepository;
    }

    @Override
    public List<RouteDto> findValidRoutes(Location origin, Location destination, LocalDate date) {
        List<Transportation> all = transportationRepository.findAll();
        List<RouteDto> results = new ArrayList<>();

        for (Transportation t1 : all) {
            if (!t1.getOrigin().equals(origin)) continue;

            if (t1.getDestination().equals(destination)) {
                List<Transportation> route = List.of(t1);
                if (isValidRoute(route, date)) {
                    results.add(toRouteDto(route));
                }
            }

            for (Transportation t2 : all) {
                if (!t1.getDestination().equals(t2.getOrigin())) continue;

                if (t2.getDestination().equals(destination)) {
                    List<Transportation> route = List.of(t1, t2);
                    if (isValidRoute(route, date)) {
                        results.add(toRouteDto(route));
                    }
                }

                for (Transportation t3 : all) {
                    if (!t2.getDestination().equals(t3.getOrigin())) continue;

                    if (t3.getDestination().equals(destination)) {
                        List<Transportation> route = List.of(t1, t2, t3);
                        if (isValidRoute(route, date)) {
                            results.add(toRouteDto(route));
                        }
                    }
                }
            }
        }

        return results;
    }

    private RouteDto toRouteDto(List<Transportation> route) {
        List<StepDto> steps = new ArrayList<>();
        for (Transportation t : route) {
            steps.add(new StepDto(
                    t.getOrigin().getName(),
                    t.getDestination().getName(),
                    t.getType().name()
            ));
        }
        return new RouteDto(steps);
    }



    private boolean isValidRoute(List<Transportation> route, LocalDate date) {
        if (route.size() > 3) return false;

        int flightCount = 0;
        int beforeFlightCount = 0;
        int afterFlightCount = 0;
        boolean flightPassed = false;

        // Tarih filtresi varsa gün numarasını al
        Integer dayOfWeek = date != null ? date.getDayOfWeek().getValue() : null;

        for (Transportation t : route) {
            if (dayOfWeek != null && (t.getOperatingDays() == null || !t.getOperatingDays().contains(dayOfWeek))) {
                return false; // O gün çalışmıyor
            }

            if (t.getType() == TransportationType.FLIGHT) {
                flightCount++;
                flightPassed = true;
            } else {
                if (!flightPassed) beforeFlightCount++;
                else afterFlightCount++;
            }
        }

        //if (route.size() == 1 && flightCount == 0) return true; // uçuşsuz rota bulursa yine de rotayı döndürür.
        return flightCount == 1 && beforeFlightCount <= 1 && afterFlightCount <= 1;
    }

}
