package com.atakanibis.flightroutefinder.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location origin;

    @ManyToOne
    private Location destination;

    @Enumerated(EnumType.STRING)
    private TransportationType type;

    @ElementCollection
    private List<Integer> operatingDays;


    public Long getId() {
        return id;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public TransportationType getType() {
        return type;
    }

    public void setType(TransportationType type) {
        this.type = type;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(List<Integer> operatingDays) {
        this.operatingDays = operatingDays;
    }
}
