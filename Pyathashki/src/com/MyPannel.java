package com;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyPannel extends JPanel {
    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        setBackground(new Color(249, 198, 72, 174));
    }

    MyPannel(BufferedImage image){
        this.image = image;
    }
}
