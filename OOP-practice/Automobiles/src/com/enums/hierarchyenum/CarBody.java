package com.enums.hierarchyenum;

public enum CarBody {
    BUGGY(1000), COUPE(800), FLOWER_CAR(2000), HATCHBACK(1500), HEARSE(600),
    KOMBI(1500), LIMOUSINE(5000), MINIVAN(2000), PICKUP(1800);

    public final int COST;

    CarBody(int cost) {
        this.COST = cost;
    }
}
