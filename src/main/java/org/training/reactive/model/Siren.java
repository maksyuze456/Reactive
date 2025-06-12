package org.training.reactive.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Siren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longtitude;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "triggeredSirens")
    private List<Fire> activeFires;

    public Siren() {

    }

    public Siren(long id, double latitude, double longtitude, Status status, List<Fire> activeFires) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.status = status;
        this.activeFires = activeFires;
    }

    public Siren(double latitude, double longtitude, Status status, List<Fire> activeFires) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.status = status;
        this.activeFires = activeFires;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Fire> getActiveFires() {
        return activeFires;
    }

    public void setActiveFires(List<Fire> activeFires) {
        this.activeFires = activeFires;
    }
}



