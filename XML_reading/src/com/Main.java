package com;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Main extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;

    Main() {
        tableCreating();
        frameCreating();
    }

    void frameCreating() {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuEdit = new JMenu("Edit");
        JMenuItem save = new JMenuItem("Save XML");
        JMenuItem open = new JMenuItem("Open XML");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem add = new JMenuItem("Add");
        open.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser(new File("D:\\Java\\Projects\\MyPrograms\\XMLReader"));
            chooser.showDialog(null, "Open file");
            tableReset();
            try {
                parseXML(chooser.getSelectedFile());
            } catch (SAXException | ParserConfigurationException | IllegalArgumentException | IOException e) {
                JOptionPane.showMessageDialog(null, "Something was wrong");
            }

        });
        save.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser(new File("D:\\Java\\Projects\\MyPrograms\\XMLReader"));
            fileChooser.showDialog(null, "Choose file to save");
            try {

                writeToXML(fileChooser.getSelectedFile());
            } catch (ParserConfigurationException | IllegalArgumentException | TransformerException e) {
                JOptionPane.showMessageDialog(null, "Something was wrong");

            }
        });
        delete.addActionListener(actionEvent -> {
            int row = table.getSelectedRow();
            if (row > -1) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.removeRow(row);
            }
        });
        add.addActionListener(actionEvent -> {
            String id = JOptionPane.showInputDialog(null, "Enter id");
            String type = JOptionPane.showInputDialog(null, "Enter type");
            String material = JOptionPane.showInputDialog(null, "Enter material");
            String power = JOptionPane.showInputDialog(null, "Enter power");
            if (tableModel.getRowCount() > 0) {
                if (tableModel.getValueAt(tableModel.getRowCount() - 1, 0) != null) {
                    Vector<String> vector = new Vector<>(6);
                    int maxRow = tableModel.getRowCount();
                    tableModel.addRow(vector);
                    addElement(id, type, material, power, maxRow);
                } else {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (tableModel.getValueAt(i, 0) == null) {
                            addElement(id, type, material, power, i);
                            break;
                        }
                    }
                }

            } else {
                tableReset();
                addElement(id, type, material, power, 1);
            }
        });

        JMenu menuBinary = new JMenu("Binary");
        JMenuItem openBinary = new JMenuItem("Open binary file");
        openBinary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFileChooser chooser = new JFileChooser(new File("D:\\Java\\Projects\\MyPrograms\\XMLReader"));
                chooser.showDialog(null, "Open file");
                tableReset();
                try {
                    openBinary(chooser.getSelectedFile());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        JMenuItem saveBinary = new JMenuItem("Save binary file");
        saveBinary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser(new File("D:\\Java\\Projects\\MyPrograms\\XMLReader"));
                chooser.showDialog(null, "Open file");
                try {
                    saveBinary(chooser.getSelectedFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuBinary.add(openBinary);
        menuBinary.add(saveBinary);

        menuEdit.add(delete);
        menuEdit.add(add);
        menuFile.add(open);
        menuFile.add(save);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuBinary);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setLocation(dimension.width / 2 - 400, dimension.height / 2 - 200);
        this.setJMenuBar(menuBar);
        this.setContentPane(scrollPane);
        this.setPreferredSize(new Dimension(800, 400));
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void openBinary(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        int k = 0;
        ArrayList<Expander> expanders = (ArrayList<Expander>)objectInputStream.readObject();
        for (Expander expander : expanders){
            tableModel.setValueAt(expander.getId(), k + 1, 0);
            tableModel.setValueAt(expander.getType(), k + 1, 1);
            tableModel.setValueAt(expander.getMaterial(), k + 1, 2);
            tableModel.setValueAt(expander.getPower(), k + 1, 3);
            k++;
        }
    }

    void saveBinary(File file) throws IOException {
        ArrayList<Expander> expanders = new ArrayList<>();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        for (int i = 1; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0) != null) {
                Expander expander = new Expander(Integer.parseInt (tableModel.getValueAt(i, 0).toString()),
                        tableModel.getValueAt(i, 1).toString(),
                        tableModel.getValueAt(i, 2).toString(),
                        (Integer.parseInt(tableModel.getValueAt(i, 3).toString())));
                expanders.add(expander);
            } else {
                break;
            }
        }
        objectOutputStream.writeObject(expanders);
    }

    void addElement(String a, String b, String c, String d, int i) {
        tableModel.setValueAt(a, i, 0);
        tableModel.setValueAt(b, i, 1);
        tableModel.setValueAt(c, i, 2);
        tableModel.setValueAt(d, i, 3);
    }

    void tableReset() {
        table.setRowHeight(40);
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        for (int i = 0; i < 3; i++) {
            tableModel.addColumn("");
        }
        Vector<String> vector = new Vector<>(35);
        for (int i = 0; i < vector.capacity(); i++) {
            vector.add("");
        }
        tableModel.addColumn("", vector);
        tableModel.setValueAt("Expander id", 0, 0);
        tableModel.setValueAt("Type", 0, 1);
        tableModel.setValueAt("Material", 0, 2);
        tableModel.setValueAt("Power", 0, 3);
    }

    void tableCreating() {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
    }

    void parseXML(File file) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file);
        Node root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        int k = 0;
        for (int i = 0; i < nodes.getLength(); i++) {
            int id = 0;
            String material = null;
            String type = null;
            int power = 0;
            Expander expander;

            Node node = nodes.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {


                id = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
                tableModel.setValueAt(id, k + 1, 0);
                NodeList nodeProps = node.getChildNodes();
                for (int j = 0; j < nodeProps.getLength(); j++) {
                    Node nodeProp = nodeProps.item(j);
                    if (nodeProp.getNodeType() != Node.TEXT_NODE) {
                        if (nodeProp.getNodeName().equals("type")) {
                            type = nodeProp.getChildNodes().item(0).getTextContent();
                            tableModel.setValueAt(type, k + 1, 1);
                        }
                        if (nodeProp.getNodeName().equals("material")) {
                            material = nodeProp.getChildNodes().item(0).getTextContent();
                            tableModel.setValueAt(material, k + 1, 2);
                        }
                        if (nodeProp.getNodeName().equals("power")) {
                            power = Integer.parseInt(nodeProp.getChildNodes().item(0).getTextContent());
                            tableModel.setValueAt(power, k + 1, 3);
                        }
                    }
                }

                expander = new Expander(id, type, material, power);
                k++;
            }

        }
    }

    void writeToXML(File f) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element rootElement =
                document.createElement("expanders");
        document.appendChild(rootElement);
        for (int i = 1; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0) != null) {
                rootElement.appendChild(getExpander(document,
                        tableModel.getValueAt(i, 0).toString(),
                        tableModel.getValueAt(i, 1).toString(),
                        tableModel.getValueAt(i, 2).toString(),
                        tableModel.getValueAt(i, 3).toString()));
            } else break;
        }
        StreamResult file = new StreamResult(f);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        transformer.transform(source, file);


    }

    private Node getExpander(Document doc, String id, String type, String material, String power) {
        Element expander = doc.createElement("expander");
        expander.setAttribute("id", id);
        expander.appendChild(getExpanderElement(doc, "type", type));
        expander.appendChild(getExpanderElement(doc, "material", material));
        expander.appendChild(getExpanderElement(doc, "power", power));
        return expander;
    }

    private Node getExpanderElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public static void main(String[] args) {
        new Main();
    }
}
