import javax.media.j3d.Light;
import javax.media.j3d.PointLight;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Point3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPannel extends JPanel {
    TransformGroup transformGroup;
    Light light;
    private int rotationAngle;
    private int radius;

    MyPannel(TransformGroup transformGroup, Light light) {
        radius = 3;
        this.light = light;
        this.transformGroup = transformGroup;

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
        super.paintComponent(g);
        double angle = Math.toRadians(rotationAngle);
        float x = (float) (Math.sin(-angle) * (radius - 10) / 2);
        float y = (float) (Math.cos(-angle) * (radius - 10) / 2);
        ((PointLight) light).setPosition(new Point3f(x, y, 1));
    }
}
