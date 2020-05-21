package com.enums;

public enum Brand {
    ACURA(800), BENTLEY(1200), JAGUAR(1300), FIAT(900), GENESIS(1000), JEEP(950),
    FORD(800), BMW(1000), AUDI(1000), KIA(500), NISSAN(600), PORSCHE(1500),
    ROLLS_ROYCE(2000), MAZDA(600), SMART(1200), TOYOTA(700), VOLVO(680);

    public final int COST;

    Brand(int cost) {
        this.COST = cost;
    }
}
