package com.enums.hierarchyenum;

public enum RoofRack {
    RAIN_GUTTER(5000), BARE_ROOF(6000), FIXED_POINT(4400), SIDE_RAILS(5600), FACTORY_BARS(2300);

    public final int COST;

    RoofRack(int cost) {
        this.COST = cost;
    }
}
