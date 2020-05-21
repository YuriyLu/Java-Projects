package com.model;

public class Brick extends BaseObject {
    private Bonus bonus;
    private int beginnerStrength;
    private int strength;
    private int level;
    private boolean deleted = false;

    public Brick(int x, int y, int strength, int width, int height) {
        super(x, y, width, height);
        this.strength = strength;
        this.beginnerStrength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCollision(){
        deleted = true;
    }

    public boolean destroyed(){
        return deleted;
    }

    public int getScore(){
        int BASIC_SCORE = 100;
        return BASIC_SCORE * (beginnerStrength);
    }

    public void hit(){
        strength--;
    }

    public int getBeginnerStrength() {
        return beginnerStrength;
    }
}
