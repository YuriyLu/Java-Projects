package com.carelements;

import com.enums.Color;
import com.enums.Size;

import java.util.Objects;

public class Carcase {
    private Color color;
    private Size size;
    private int cost;

    @Override
    public String toString() {
        return "Carcase{" +
                color + ", " +
                size +
                ", cost=" + cost +
                "$}";
    }

    public Carcase(Color color, Size size, int cost) {
        this.color = color;
        this.size = size;
        this.cost = cost;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carcase carcase = (Carcase) o;
        return cost == carcase.cost &&
                color == carcase.color &&
                size == carcase.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cost);
    }
}
