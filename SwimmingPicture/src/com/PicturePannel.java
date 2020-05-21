package com;

import sun.awt.image.BufferedImageDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PicturePannel extends JPanel {
    private Timer timer;
    private BufferedImage image;
    private int xCoordinates;
    private int yCoordinates;
    private int delay;
    private double rotationAngle;
    private int radius;
    private Dimension imageDimension;
    private int speed;

    PicturePannel(MainWindow parent) {
        setSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));
        Dimension dimension = this.getSize();
        radius = Math.min(dimension.width, dimension.height) / 2;
        delay = 15;
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = parent.getSpeed();
                repaint();
                if (radius == 0) {
                    rotationAngle = 0;
                } else {
                    rotationAngle += ((double)speed / radius) * parent.getDirection();
                }

                if (rotationAngle >= 360) {
                    rotationAngle = 0;
                }
            }
        };
        timer = new Timer(delay, listener);
        timer.start();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.imageDimension = new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Dimension dimension = this.getSize();
        xCoordinates = dimension.width / 2;
        yCoordinates = dimension.height / 2;
        double angle = Math.toRadians(rotationAngle);
        graphics2D.clearRect(0, 0, dimension.width, dimension.height);

        if (image != null) {
            radius = Math.min(dimension.width, dimension.height) / 2;
            radius -= Math.max(imageDimension.width, imageDimension.height) / 4;

            int x = xCoordinates - (int) (radius * Math.sin(-angle));
            int y = yCoordinates - (int) (radius * Math.cos(-angle));
            graphics2D.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
        }
    }
}
