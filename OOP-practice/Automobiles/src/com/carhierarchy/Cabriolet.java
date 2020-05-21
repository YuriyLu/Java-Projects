package com.carhierarchy;

import com.carelements.Carcase;
import com.carelements.Engine;
import com.carelements.Wheel;
import com.enums.Brand;
import com.enums.Color;
import com.enums.hierarchyenum.SlidingRoof;

import java.util.Objects;

public class Cabriolet extends Automobile {
    private SlidingRoof slidingRoof;

    public Cabriolet(Brand brand, Carcase carcase, Engine engine, Wheel wheels, int wheelAmount, int maxSpeed, SlidingRoof slidingRoof) {
        super(brand, carcase, engine, wheels, wheelAmount, maxSpeed);
        this.slidingRoof = slidingRoof;
        this.totalCost += slidingRoof.COST;
    }

    public SlidingRoof getSlidingRoof() {
        return slidingRoof;
    }

    public void setSlidingRoof(SlidingRoof slidingRoof) {
        this.slidingRoof = slidingRoof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cabriolet cabriolet = (Cabriolet) o;
        return slidingRoof == cabriolet.slidingRoof;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), slidingRoof);
    }

    @Override
    public String toString() {
        return "Cabriolet{" +
                slidingRoof +
                super.toString() +
                '}';
    }
}
