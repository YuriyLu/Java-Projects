package com.carhierarchy;

import com.carelements.Carcase;
import com.carelements.Engine;
import com.carelements.Wheel;
import com.enums.Brand;
import com.enums.hierarchyenum.NitrousOxideEngine;

import java.util.Objects;

public class SportCar extends Automobile {
    private NitrousOxideEngine nitrousOxideEngine;

    public SportCar(Brand brand, Carcase carcase, Engine engine, Wheel wheels, int wheelAmount, int maxSpeed,
                    NitrousOxideEngine nitrousOxideEngine) {
        super(brand, carcase, engine, wheels, wheelAmount, maxSpeed);
        this.nitrousOxideEngine = nitrousOxideEngine;
        this.totalCost += nitrousOxideEngine.COST;
    }

    public NitrousOxideEngine getNitrousOxideEngine() {
        return nitrousOxideEngine;
    }

    public void setNitrousOxideEngine(NitrousOxideEngine nitrousOxideEngine) {
        this.nitrousOxideEngine = nitrousOxideEngine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SportCar sportCar = (SportCar) o;
        return nitrousOxideEngine == sportCar.nitrousOxideEngine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nitrousOxideEngine);
    }

    @Override
    public String toString() {
        return "SportCar{" +
                nitrousOxideEngine +
                super.toString() +
                '}';
    }
}
