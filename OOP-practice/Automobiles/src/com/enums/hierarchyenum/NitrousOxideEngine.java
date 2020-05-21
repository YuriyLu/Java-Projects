package com.enums.hierarchyenum;

public enum  NitrousOxideEngine {
    DRY(4900), WET(5000), SINGLE_NOZZLE(3000), DIRECT_PORT(6000), PLATE(3500),
    BAR(3000), PROPANE_OR_CNG(5000);

    public final int COST;

    NitrousOxideEngine(int cost) {
        this.COST = cost;
    }
}
