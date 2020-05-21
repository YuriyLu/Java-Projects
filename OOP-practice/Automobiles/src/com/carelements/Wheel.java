package com.carelements;

import java.util.Objects;

public class Wheel {
    private int cost;
    private int radius;

    public Wheel(int cost, int radius) {
        this.cost = cost;
        this.radius = radius;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "cost=" + cost +
                "$, radius=" + radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return cost == wheel.cost &&
                radius == wheel.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, radius);
    }
}
