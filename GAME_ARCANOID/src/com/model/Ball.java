package com.model;

public class Ball extends BaseObject {
    private int Lives;
    private int velocityX = 0;
    private int velocityY = -3;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {
        x += velocityX;
        y += velocityY;
    }

    public boolean isIntersect(BaseObject o) {
        int rightBorderObject = o.getX() + o.getWidth();
        int leftBorderObject = o.getX();
        int topBorderObject = o.getY();
        int bottomBorderObject = o.getY() + o.getHeight();

        int rightBall = x + width;
        int leftBall = x;
        int topBall = y;
        int bottomBall = y + height;

        if (bottomBall < topBorderObject) {
            return false;
        }
        if (topBall > bottomBorderObject) {
            return false;
        }
        if (rightBall < leftBorderObject) {
            return false;
        }
        return leftBall <= rightBorderObject;
    }

    public void boardTouch(BaseObject o) {
        if (x + width / 2 != o.getX() + o.getWidth() / 2) {
            int ballMiddle = x + width / 2;
            Board board = (Board) o;
            if (ballMiddle <= o.getX() + board.rotationElement) {
                velocityX = -3;
                velocityY = -1;
            } else if (ballMiddle <= o.getX() + 2 * board.rotationElement) {
                velocityX = -2;
                velocityY = -2;
            } else if (ballMiddle <= o.getX() + 3 * board.rotationElement) {
                velocityX = -1;
                velocityY = -3;
            } else if (ballMiddle <= o.getX() + 4 * board.rotationElement) {
                velocityX = 0;
                velocityY = -3;
            } else if (ballMiddle <= o.getX() + 5 * board.rotationElement) {
                velocityX = 1;
                velocityY = -3;
            } else if (ballMiddle <= o.getX() + 6 * board.rotationElement) {
                velocityX = 2;
                velocityY = -2;
            } else if (ballMiddle <= o.getX() + 7 * board.rotationElement) {
                velocityX = 3;
                velocityY = -1;
            }
        } else {
            velocityY = -3;
        }

    }

    public void changeDirection(BaseObject o) {
        int topBorderObject = o.getY();
        int bottomBorderObject = o.getY() + o.getHeight();
        int rightBorderObject = o.getX() + o.getWidth();
        int leftBorderObject = o.getX();

        int rightBall = x + width;
        int leftBall = x;
        int topBall = y;
        int bottomBall = y + height;

        int overlapLeft = rightBall - leftBorderObject;
        int overlapRight = rightBorderObject - leftBall;
        int overlapTop = bottomBall - topBorderObject;
        int overlapBottom = bottomBorderObject - topBall;

        boolean fromLeft = overlapLeft < overlapRight;
        boolean fromTop = overlapTop < overlapBottom;

        int minOverlapX = fromLeft ? overlapLeft : overlapRight;
        int minOverlapY = fromTop ? overlapTop : overlapBottom;

        if (minOverlapX < minOverlapY) {
            velocityX *= -1;
        } else {
            velocityY *= -1;
        }
    }

    public void wallTouch(int x) {
        switch (x) {
            case 0:
            case 1:
                velocityX *= -1;
                break;
            case 2:
                velocityY *= -1;
                break;
            case 3:
                velocityY += -1;
                break;
        }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        velocityY = -3;
        velocityX = 0;
    }
}
