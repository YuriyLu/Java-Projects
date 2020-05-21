package com.hierarchy;

import com.breeds.DogBreed;
import com.enums.DogColor;
import com.enums.DogSize;

public class AdultDog extends Dog {

    @Override
    public int hashCode() {
        return super.hashCode() + 2;
    }

    public AdultDog(String name, DogBreed breed, DogColor color, DogSize size) {
        super(name, breed, color, size);
    }

    @Override
    public String toString() {
        return "AdultDog{" +
                super.toString() +
                '}';
    }

    @Override
    public void voice() {
        System.out.println("Gaf-Gaf");
    }

    @Override
    public void bite() {
        System.out.println("*Klats* it was average bite'");
    }

    @Override
    public void run() {
        System.out.println("Yeah! Jogging!");
    }

    @Override
    public void jump() {
        System.out.println("Just jump");
    }
}
