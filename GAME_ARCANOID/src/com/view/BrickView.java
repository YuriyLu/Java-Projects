package com.view;

import com.model.Brick;

import java.awt.*;

public class BrickView {
    private Brick brick;

    public BrickView(Brick brick) {
        this.brick = brick;
    }

    public void drawBrick(Graphics g) {
        if (brick.getStrength() < brick.getBeginnerStrength()) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        g.setColor(colorByLevel(brick.getLevel()));
        g.fillRect(brick.getX() + 5, brick.getY() + 5, brick.getWidth() - 10, brick.getHeight() - 10);
    }

    private Color colorByLevel(int level) {
        switch (level) {
            case 1:
                return new Color(0, 187, 19);
            case 2:
                return new Color(0, 186, 76);
            case 3:
                return new Color(0, 184, 157);
            case 4:
                return new Color(0, 124, 184);
            case 5:
                return new Color(0, 8, 184);
            case 6:
                return new Color(113, 0, 186);
            case 7:
                return new Color(186, 0, 134);
            case 8:
                return new Color(183, 0, 122);
            case 9:
                return new Color(184, 0, 54);
            case 10:
                return new Color(184, 12, 0);
            case 11:
                return new Color(184, 58, 0);
            case 12:
                return new Color(184, 135, 0);
            case 13:
                return new Color(183, 185, 0);
            case 14:
                return new Color(123, 183, 0);
            case 15:
                return new Color(108, 184, 1);
            case 16:
                return new Color(155, 186, 55);
            default:
                return new Color(0, 184, 14);
        }
    }

}
