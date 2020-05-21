package com;

import javax.swing.*;
import java.awt.*;

class Clock extends JFrame {
    Clock() {
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClockPanel panel = new ClockPanel();
        this.add(panel);
    }
}