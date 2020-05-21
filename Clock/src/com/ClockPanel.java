package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockPanel extends JPanel {
    private int rotationAngle;
    private int radius;

    ClockPanel() {
        radius = 400;
        int delayTime = 1000;
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                rotationAngle += 6;
                if (rotationAngle >= 360) {
                    rotationAngle = 0;
                }
            }
        };
        Timer timer = new Timer(delayTime, listener);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension dimension = this.getSize();
        int xCoord = dimension.width / 2;
        int yCoord = dimension.height / 2;
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.LIGHT_GRAY);

        double angle = Math.toRadians(rotationAngle);
        int x = xCoord - ((int) (Math.sin(-angle) * (radius - 10) / 2));
        int y = yCoord - ((int) (Math.cos(-angle) * (radius - 10) / 2));
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.setColor(Color.LIGHT_GRAY);
        graphics2D.fillOval(xCoord - radius / 2, yCoord - radius / 2, radius, radius);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(xCoord, yCoord, x, y);
    }
}