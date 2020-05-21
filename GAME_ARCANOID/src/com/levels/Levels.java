package com.levels;

import com.model.Brick;

import java.util.ArrayList;

public class Levels {
    private ArrayList<ArrayList<Brick>> levels;

    public Levels() {
    }

    public Levels(ArrayList<ArrayList<Brick>> levels) {
        this.levels = levels;
    }

    ArrayList<Brick> getLevel(int i){
        return levels.get(i);
    }
}
