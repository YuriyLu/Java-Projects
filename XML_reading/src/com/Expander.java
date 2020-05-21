package com;

import java.io.Serializable;

public class Expander implements Serializable {
    private int id;
    private String type;
    private String material;
    private int power;

    public Expander(int id, String type, String material, int power) {
        this.id = id;
        this.type = type;
        this.material = material;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Expander{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", power=" + power +
                '}';
    }
}
