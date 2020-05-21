package com.carhierarchy;

import com.carelements.Carcase;
import com.carelements.Engine;
import com.carelements.Wheel;
import com.enums.Brand;
import com.enums.hierarchyenum.RoofRack;

import java.util.Objects;

public class TravelCar extends Automobile {
    private RoofRack roofRack;

    public TravelCar(Brand brand, Carcase carcase, Engine engine, Wheel wheels, int wheelAmount, int maxSpeed, RoofRack roofRack) {
        super(brand, carcase, engine, wheels, wheelAmount, maxSpeed);
        this.roofRack = roofRack;
        this.totalCost += roofRack.COST;
    }

    public RoofRack getRoofRack() {
        return roofRack;
    }

    public void setRoofRack(RoofRack roofRack) {
        this.roofRack = roofRack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TravelCar travelCar = (TravelCar) o;
        return roofRack == travelCar.roofRack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roofRack);
    }

    @Override
    public String toString() {
        return "TravelCar{" +
                roofRack +
                super.toString() +
                '}';
    }
}
