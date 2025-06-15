package org.training.reactive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private double latitude;
    private double longtitude;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "triggered_sirens",
                joinColumns = @JoinColumn(name = "fire_id"),
                inverseJoinColumns = @JoinColumn(name = "siren_id")
    )
    private List<Siren> triggeredSirens;

    public Fire() {

    }

    public Fire(double latitude, double longtitude, Status status, List<Siren> triggeredSirens) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.status = status;
        this.triggeredSirens = triggeredSirens;
    }

    public Fire(Long id, double latitude, double longtitude, Status status, List<Siren> triggeredSirens) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.status = status;
        this.triggeredSirens = triggeredSirens;
    }

    public Fire(double latitude, double longtitude, Status status) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Siren> getTriggeredSirens() {
        return triggeredSirens;
    }

    public void setTriggeredSirens(List<Siren> triggeredSirens) {
        this.triggeredSirens = triggeredSirens;
    }
}
