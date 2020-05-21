package com.hierarchy;

import com.breeds.DogBreed;
import com.enums.DogColor;
import com.enums.DogSize;

import java.util.Objects;

public abstract class Dog extends com.hierarchy.Animal {
    protected DogColor color;
    protected DogSize size;
    protected int age;

    public Dog(String name, DogBreed breed, DogColor color, DogSize size) {
        super(name, breed);
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return " name=" + name + ", breed=" + breed + ", color=" + color  +
                ", size=" + size +
                ' ';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return color == dog.color &&
                size == dog.size;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(super.hashCode(), color, size) % 1000);
    }

    public abstract void bite();
    public abstract void run();
    public abstract void jump();

    public void setColor(DogColor color) {
        this.color = color;
    }

    public void setSize(DogSize size) {
        this.size = size;
    }

    public DogColor getColor() {
        return color;
    }

    public DogSize getSize() {
        return size;
    }
}
