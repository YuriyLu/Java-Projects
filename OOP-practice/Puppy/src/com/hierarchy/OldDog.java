package com.hierarchy;

import com.breeds.DogBreed;
import com.enums.DogColor;
import com.enums.DogSize;

public class OldDog extends com.hierarchy.Dog {

    @Override
    public int hashCode() {
        return super.hashCode() + 3;
    }

    public OldDog(String name, DogBreed breed, DogColor color, DogSize size) {
        super(name, breed, color, size);
    }

    @Override
    public String toString() {
        return "OldDog{" +
                super.toString() +
                "}";
    }

    @Override
    public void voice() {
        System.out.println("Wuf-wuf");
    }

    @Override
    public void bite() {
        System.out.println("*Ghf* you lost you hand");
    }

    @Override
    public void run() {
        System.out.println("Don't touch me please...");
    }

    @Override
    public void jump() {
        System.out.println("Mhh, too hard");
    }
}
