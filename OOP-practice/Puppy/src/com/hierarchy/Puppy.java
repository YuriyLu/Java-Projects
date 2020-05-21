package com.hierarchy;

import com.breeds.DogBreed;
import com.enums.DogColor;
import com.enums.DogSize;

public class Puppy extends com.hierarchy.Dog {

    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }

    public Puppy(String name, DogBreed breed, DogColor color, DogSize size) {
        super(name, breed, color, size);
    }

    @Override
    public String toString() {
        return "Puppy{" +
                super.toString() +
                "}";
    }

    @Override
    public void voice() {
        System.out.println("Tyaf-tyaf");
    }

    @Override
    public void bite() {
        System.out.println("*Puf* it was a little bite!");
    }

    @Override
    public void run() {
        System.out.println("Ruuuuuuuuuuuun!");
    }

    @Override
    public void jump() {
        System.out.println("Oh, too low...");
    }
}
