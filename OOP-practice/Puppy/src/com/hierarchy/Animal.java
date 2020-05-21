package com.hierarchy;

import com.breeds.Breeds;

import java.util.Objects;

public abstract class Animal {
    protected String name;
    protected Breeds breed;

    public Animal(String name, Breeds breed) {
        this.name = name;
        this.breed = breed;
    }

    public abstract void voice();

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", breed=" + breed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) &&
                breed == animal.breed;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(name, breed) % 10);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(Breeds breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public Breeds getBreed() {
        return breed;
    }
}
