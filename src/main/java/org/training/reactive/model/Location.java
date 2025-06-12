package org.training.reactive.model;

public class Location {
    private double latitude, longtitude;

    public Location(double x, double y) {
        this.latitude = x;
        this.longtitude = y;
    }

    public double getLatitude() { return latitude; }
    public double getLongtitude() { return longtitude; }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longtitude + ")";
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
