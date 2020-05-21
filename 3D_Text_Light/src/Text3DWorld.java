import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Text3DWorld extends JFrame {
    private int flag = -1;
    private int flag1 = 5;
    private PointLight light;
    private Transform3D transform3D;
    private Text3D textGeom;
    private ColoringAttributes coloringAttributes;
    private Vector3d spherePos;
    private TransformGroup transformGroup;

    public Text3DWorld() {
        super("Text3DWorld");
        Canvas3D canvas3D = createCanvas3D();
        BranchGroup scene = createSceneGraph();
        connect(canvas3D, scene);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Canvas3D createCanvas3D() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        setSize(2000, 900);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(canvas3D, BorderLayout.CENTER);
        panel.setVisible(true);
        setContentPane(panel);


        canvas3D.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    String newText = textGeom.getString().substring(0, textGeom.getString().length() - 1);
                    textGeom.setString(newText);
                } else if (e.getExtendedKeyCode() == KeyEvent.VK_C) {
                    Color color = JColorChooser.showDialog(null, "Choose color for left light!", Color.black);
                    if (color != null) {
                        light.setColor(new Color3f(color));
                        coloringAttributes.setColor(new Color3f(color));
                    }
                } else if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN){
                    spherePos = new Vector3d(0, -1.6, -13);

                } else if(e.getExtendedKeyCode() == KeyEvent.VK_UP){
                    spherePos = new Vector3d(0, 1.6, -13);

                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                    spherePos = new Vector3d(-3, 0, -13);
                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
                    spherePos = new Vector3d(3, 0, -13);
                }
                else {
                    String newText = String.valueOf(e.getKeyChar());
                    textGeom.setString(textGeom.getString() + newText);
                }
                transform3D.setTranslation(spherePos);
                transformGroup.setTransform(transform3D);
            }
        });
        canvas3D.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag1 *= flag;
                Point3f point3f = new Point3f(flag1, -3, 1);
                light.setPosition(point3f);
            }
        });
        return canvas3D;
    }

    public BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup transformGroup = transformGroup();
        TransformGroup sphereTransformGroup = createSphere();
        transformGroup.addChild(createTextShape());
        setLighting();
        objRoot.addChild(transformGroup);
        objRoot.addChild(sphereTransformGroup);
        return objRoot;
    }

    private TransformGroup createSphere() {
        transform3D = new Transform3D();

        spherePos = new Vector3d(0, 1.6, -13);
        transform3D.setTranslation(spherePos);

       transformGroup = new TransformGroup(transform3D);
       transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Color3f color3f = new Color3f(0, 1, 1);

        coloringAttributes = new ColoringAttributes();
        coloringAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
        coloringAttributes.setColor(color3f);


        Appearance appearance = new Appearance();
        appearance.setColoringAttributes(coloringAttributes);
        transformGroup.addChild(new Sphere(0.1f, 1, 2, appearance));
        return transformGroup;
    }

    private TransformGroup transformGroup() {
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3f(0.0f, 0.0f, -15.0f));
        TransformGroup group = new TransformGroup(transform3D);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return group;
    }


    private Shape3D createTextShape() {
        Appearance textAppear = new Appearance();
        textAppear.setMaterial(new Material());
        Font3D font3D = new Font3D(
                new Font("Helvetica", Font.PLAIN, 1),
                new FontExtrusion());
        textGeom = new Text3D(font3D, "Text3DWorld");
        textGeom.setCapability(Text3D.ALLOW_STRING_WRITE);
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D textShape = new Shape3D();
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppear);
        return textShape;
    }

    private void setLighting() {
        light = new PointLight(
                new Color3f(0, 1, 1),
                new Point3f(0, -3, 1),
                new Point3f(1, 0, 0));

        light.setInfluencingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                1000.0));
        light.setCapability(PointLight.ALLOW_COLOR_WRITE);
        light.setCapability(PointLight.ALLOW_STATE_WRITE);
        light.setCapability(PointLight.ALLOW_POSITION_WRITE);
        transformGroup.addChild(light);
    }


    private void connect(Canvas3D canvas3D, BranchGroup scene) {
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.addBranchGraph(scene);
    }

    public static void main(String[] args) {
        new Text3DWorld().setVisible(true);
    }
}