package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    static final int MIN_SPEED = 1;
    static final int MAX_SPEED = 10;
    static final int STEP_SPEED = 1;
    private JSlider slider;
    private JComboBox<String> comboBox;
    private int direction;
    String[] items = {
            "Стоп",
            "По часовой",
            "Против часовой"
    };

    MainWindow() throws IOException {
        slider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, STEP_SPEED);
        slider.setPreferredSize(new Dimension(400, 50));
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        comboBox = new JComboBox<>(items);
        JButton openImage = new JButton("open...");
        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(700, 100));
        pane.add(openImage);
        pane.add(slider);
        pane.add(comboBox);




        PicturePannel panel = new PicturePannel(this);
        panel.setImage(ImageIO.read(new File("C:/Users/YuraL/Downloads/1.jpg")));


        comboBox.addActionListener(e -> {
            String item = (String) comboBox.getSelectedItem();
            assert item != null;
            if ("По часовой".equals(item)) {
                direction = 1;
            } else if ("Против часовой".equals(item)){
                direction = -1;
            } else direction = 0;
        });
        openImage.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    panel.setImage(ImageIO.read(file));

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pane, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.CENTER);
       //this.setMinimumSize(new Dimension(600, 600));
        this.setVisible(true);
        this.setSize(new Dimension(1500, 1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return slider.getValue() * 500;
    }

    public static void main(String[] args) throws IOException {
        MainWindow mainWindow = new MainWindow();
    }
}
