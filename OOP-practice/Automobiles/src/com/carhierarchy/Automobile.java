package com.carhierarchy;

import com.carelements.Carcase;
import com.carelements.Engine;
import com.carelements.Wheel;
import com.enums.Brand;

import java.util.Objects;

public abstract class Automobile {
    protected Brand brand;
    protected Carcase carcase;
    protected Engine engine;
    protected Wheel wheels;
    protected int wheelAmount;
    protected int totalCost;
    protected int maxSpeed;


    public Automobile(Brand brand, Carcase carcase, Engine engine, Wheel wheels, int wheelAmount, int maxSpeed) {
        this.brand = brand;
        this.carcase = carcase;
        this.engine = engine;
        this.wheels = wheels;
        this.wheelAmount = wheelAmount;
        this.maxSpeed = maxSpeed;
        this.totalCost = carcase.getCost() + engine.getCost() + wheels.getCost() * wheelAmount + brand.COST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile that = (Automobile) o;
        return wheelAmount == that.wheelAmount &&
                totalCost == that.totalCost &&
                maxSpeed == that.maxSpeed &&
                brand == that.brand &&
                Objects.equals(carcase, that.carcase) &&
                Objects.equals(engine, that.engine) &&
                Objects.equals(wheels, that.wheels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, carcase, engine, wheels, wheelAmount, totalCost, maxSpeed);
    }

    @Override
    public String toString() {
        return  ", " + brand +
                ", " + carcase +
                ", " + engine +
                ", \n" + wheels +
                ", wheelAmount=" + wheelAmount +
                ", totalCost=" + totalCost +
                "$, maxSpeed=" + maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Carcase getCarcase() {
        return carcase;
    }

    public void setCarcase(Carcase carcase) {
        this.carcase = carcase;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheel getWheels() {
        return wheels;
    }

    public void setWheels(Wheel wheels) {
        this.wheels = wheels;
    }

    public int getWheelAmount() {
        return wheelAmount;
    }

    public void setWheelAmount(int wheelAmount) {
        this.wheelAmount = wheelAmount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
