package com.enums.hierarchyenum;

public enum SlidingRoof {
    TEXTILE(10000), DETACHABLE_HARDTOP(12000), RETRACTABLE_HARDTOP(8000);

    public final int COST;

    SlidingRoof(int  cost) {
        this.COST = cost;
    }
}
