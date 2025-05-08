package com.atakanibis.flightroutefinder.dto;

import java.util.List;

public class RouteDto {
    private List<StepDto> steps;

    public RouteDto(List<StepDto> steps) {
        this.steps = steps;
    }

    public List<StepDto> getSteps() {
        return steps;
    }
}
