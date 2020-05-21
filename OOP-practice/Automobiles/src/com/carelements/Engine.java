package com.carelements;

import java.util.Objects;

public class Engine {
    private int capacity;
    private int power;
    private int cost;
    private double fuelConsumption;

    public Engine(int capacity, int power, int cost, double fuelConsumption) {
        this.capacity = capacity;
        this.power = power;
        this.cost = cost;
        this.fuelConsumption = fuelConsumption;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "capacity=" + capacity +
                ", power=" + power +
                ", cost=" + cost +
                "$, fuelConsumption=" + fuelConsumption +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return capacity == engine.capacity &&
                power == engine.power &&
                cost == engine.cost &&
                fuelConsumption == engine.fuelConsumption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, power, cost, fuelConsumption);
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
