package org.example;

public class DistanceToCountry implements Comparable<DistanceToCountry> {
    private String country;
    private double distance;

    public DistanceToCountry(String country, double distance) {
        this.country = country;
        this.distance = distance;
    }

    public String getCountry() {
        return country;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(DistanceToCountry o) {
        return Double.compare(this.distance, o.distance);
    }
}
