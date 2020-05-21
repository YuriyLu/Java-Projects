package com.model;

public class Board extends BaseObject {

    int rotationElement;

    public Board(int x, int y, int width, int height) {
        super(x, y, width, height);
        rotationElement = width / 7;
    }

    public int getRotationElement() {
        return rotationElement;
    }
}
