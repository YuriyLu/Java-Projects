package com.view;

import com.model.Ball;

import java.awt.*;

public class BallView {
    private Ball ball;
    private Color color;

    public BallView(Ball ball) {
        this.ball = ball;
        color = Color.BLUE;
    }

    public void drawBall(Graphics g) {
        g.setColor(color);
        g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }
}
