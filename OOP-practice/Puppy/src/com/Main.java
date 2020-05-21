package com;

import com.breeds.DogBreed;
import com.enums.DogColor;
import com.enums.DogSize;
import com.hierarchy.Dog;
import com.hierarchy.Puppy;

public class Main {
    public static void main(String[] args) {
        Dog guffy = new Puppy("Guffy", DogBreed.BULLDOG, DogColor.CREAM, DogSize.MEDIUM);
        System.out.println("\n" + guffy.toString() + "\n");
        System.out.println(guffy.getName() + "\n");
        guffy.voice();
        guffy.jump();
        guffy.run();
        guffy.bite();
    }
}
