package com.carhierarchy;

import com.carelements.Carcase;
import com.carelements.Engine;
import com.carelements.Wheel;
import com.enums.Brand;
import com.enums.hierarchyenum.CarBody;

import java.util.Objects;

public class BodyCar extends Automobile {
    private CarBody carBody;

    public BodyCar(Brand brand, Carcase carcase, Engine engine, Wheel wheels, int wheelAmount, int maxSpeed, CarBody carBody) {
        super(brand, carcase, engine, wheels, wheelAmount, maxSpeed);
        this.carBody = carBody;
        this.totalCost += carBody.COST;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BodyCar bodyCar = (BodyCar) o;
        return carBody == bodyCar.carBody;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carBody);
    }

    @Override
    public String toString() {
        return "BodyCar{" +
                carBody +
                 super.toString() +
                '}';
    }
}
