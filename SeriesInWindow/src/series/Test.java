package series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    private JPanel Pannel;
    private JTextField textField1;
    private JRadioButton linearProgressionRadioButton;
    private JRadioButton exponentialProgressionRadioButton;
    private JTextField FirstElement;
    private JTextField Difference;
    private JTextField textField4;
    private JButton showButton;
    private JButton showButton1;
    private JTextField Order;
    private JPanel inputField;
    private JPanel radioButtonField;
    private JPanel outputField_1;
    private JPanel outputField_2;
    private JButton insertDataButton;
    private JTextField setNNumber;
    private JTextField filePath;
    private JButton writeInFileButton;
    private JCheckBox progressionCreatedCheckBox;
    private JCheckBox fileSavedCheckBox;
    private JTextField infoField;
    private JButton recreateProgressionButton;
    private JButton clearAllButton;
    private JButton exitButton;
    private JButton buttonAbout;
    private JLabel textFirst;
    private JLabel textEnterN;
    private JLabel textTheSum;
    private JPanel info1;
    private JLabel textFilePath;
    private Series series;
    private boolean isCreated = false;


    private void setEn(boolean flag){
        progressionCreatedCheckBox.setSelected(flag);
        showButton1.setEnabled(flag);
        showButton.setEnabled(flag);
        recreateProgressionButton.setEnabled(flag);
        writeInFileButton.setEnabled(flag);
        filePath.setEnabled(flag);
        setNNumber.setEnabled(flag);
        outputField_1.setEnabled(flag);
        outputField_2.setEnabled(flag);
        textEnterN.setEnabled(flag);
        textFirst.setEnabled(flag);
        textFilePath.setEnabled(flag);
        textTheSum.setEnabled(flag);
        info1.setEnabled(flag);
    }
    public Test() {
        series = new Linear();
        ButtonGroup buttgr = new ButtonGroup();
        buttgr.add(linearProgressionRadioButton);
        buttgr.add(exponentialProgressionRadioButton);


        insertDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (linearProgressionRadioButton.isSelected() && exponentialProgressionRadioButton.isSelected())
                        throw new MyException("Choose only one type of progression!");
                    if (!linearProgressionRadioButton.isSelected() && !exponentialProgressionRadioButton.isSelected()) {
                        throw new MyException("Choose type of progression!");
                    }

                    if (linearProgressionRadioButton.isSelected()) {
                        series = new Linear();
                    }

                    if (exponentialProgressionRadioButton.isSelected()) {
                        series = new Exponential();
                    }

                    String str = FirstElement.getText();
                    if (str.length() > 0) {
                        series.setFirstElement(Double.parseDouble(str));
                    } else throw new MyException("Illegal input format of first element");

                    str = Difference.getText();
                    if (str.length() > 0) {
                        series.setDifference(Double.parseDouble(str));
                    } else throw new MyException("Illegal input format of coefficient");

                    str = Order.getText();
                    if (str.length() > 0) {
                        series.setOrder(Integer.parseInt(str));
                    } else throw new MyException("Illegal input format of order");
                    if (Integer.parseInt(str) <= 0)
                        throw new MyException("Ohh, it's harder then I can imagine. Choose other number of order, please(");
                    isCreated = true;
                    infoField.setText("" + series.getClass());
                    setEn(true);
                } catch (MyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Write a number!");
                }
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!isCreated)
                        throw new MyException("Progression not created!");
                    textField4.setText(String.valueOf(series.getSum()));
                } catch (MyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Illegal input format!");
                }

            }
        });
        showButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = setNNumber.getText();
                try {
                    if (!isCreated)
                        throw new MyException("Progression not created!");
                    if (Integer.parseInt(str) < 0)
                        throw new NumberFormatException();
                    if (Integer.parseInt(str) == 0)
                        throw new MyException("Really?");
                    if (str.length() > 0) {
                        textField1.setText(series.toString(Integer.parseInt(setNNumber.getText())));
                    } else throw new MyException("Enter the n number!");
                } catch (MyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Illegal input format!");
                }


            }
        });
        writeInFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!isCreated)
                        throw new MyException("Progression not created!");
                    FileWriter writer = new FileWriter(filePath.getText(), false);
                    writer.write(series.toString());
                    writer.close();

                    infoField.setText(filePath.getText() + " changed");
                    fileSavedCheckBox.setSelected(true);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "File not found!");
                } catch (MyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Illegal input format!");
                }
            }
        });
        recreateProgressionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (linearProgressionRadioButton.isSelected() && exponentialProgressionRadioButton.isSelected())
                        throw new MyException("Choose only one type of progression!");
                    if (!linearProgressionRadioButton.isSelected() && !exponentialProgressionRadioButton.isSelected()) {
                        throw new MyException("Choose type of progression!");
                    }

                    if (linearProgressionRadioButton.isSelected()) {
                        series = new Linear();
                    }

                    if (exponentialProgressionRadioButton.isSelected()) {
                        series = new Exponential();
                    }

                    String str = FirstElement.getText();
                    if (str.length() > 0) {
                        series.setFirstElement(Double.parseDouble(str));
                    } else throw new MyException("Illegal input format of first element");

                    str = Difference.getText();
                    if (str.length() > 0) {
                        series.setDifference(Double.parseDouble(str));
                    } else throw new MyException("Illegal input format of coefficient");

                    str = Order.getText();
                    if (str.length() > 0) {
                        series.setOrder(Integer.parseInt(str));
                    } else throw new MyException("Illegal input format of order");
                    isCreated = true;
                    infoField.setText("recreated " + series.getClass());
                    progressionCreatedCheckBox.setSelected(true);
                } catch (MyException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Write a number!");
                }
            }
        });
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstElement.setText("");
                Order.setText("");
                Difference.setText("");
                textField1.setText("");
                textField4.setText("");
                infoField.setText("");
                filePath.setText("");
                setNNumber.setText("");
                progressionCreatedCheckBox.setSelected(false);
                fileSavedCheckBox.setSelected(false);
                setEn(false);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder str = new StringBuilder();
                str.append("Series in abstract v1.0\n");
                str.append("Written by Lucashevich Yuriy, 04.11.2019\n");
                str.append("It was warm autumn evening when I decided to write this message...\n");
                str.append("Now this program is on the GITHub!");
                JOptionPane.showMessageDialog(null, str.toString());
            }
        });
    }

    public static void main(String[] args) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        JFrame frame = new JFrame("Progression calculator");
        frame.setContentPane(new Test().Pannel);
        frame.setBounds(dimension.width / 2 - 350, dimension.height / 2 - 300, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println(Math.pow(25, 5));
    }
}
