package com.atakanibis.flightroutefinder.dto;

public class StepDto {
    private String origin;
    private String destination;
    private String type;

    public StepDto(String origin, String destination, String type) {
        this.origin = origin;
        this.destination = destination;
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }
}
