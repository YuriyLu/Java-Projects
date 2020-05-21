package com.park;

import com.carelements.*;
import com.carhierarchy.*;
import com.enums.*;
import com.enums.hierarchyenum.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class TaxiPark extends ArrayList<Automobile> {
    private int totalCost;

    public int getTotalCost() {
        return totalCost;
    }

    public void printTotalCost() {
        JOptionPane.showMessageDialog(null, "Total park cost = " +
                totalCost + "$");
    }

    @Override
    public boolean add(Automobile automobile) {
        this.totalCost += automobile.getTotalCost();
        return super.add(automobile);
    }

    @Override
    public Automobile remove(int index) {
        this.totalCost -= this.get(index).getTotalCost();
        return super.remove(index);
    }

    public void sortingByFuelConsumption() {
        this.sort(Comparator.comparing(obj -> obj.getEngine().getFuelConsumption()));
        ListIterator<Automobile> iterator = this.listIterator();
    }

    public void showInWindow() {
        String[] strings = new String[this.size() * 2 + 3];
        strings[0] = strings[this.size() * 2 + 2] = "---------------------------------------------------------------------------------" +
                "----------------------------------------------------------------------------------------------" +
                "-------------------------------";
        for (int i = 0; i < this.size(); i++) {
            strings[i * 2 + 1] = this.get(i).toString();
            strings[i * 2] = "---------------------------------------------------------------------------------" +
                    "----------------------------------------------------------------------------------------------" +
                    "-------------------------------";
        }
        JOptionPane.showMessageDialog(null, strings, "Taxi park", JOptionPane.INFORMATION_MESSAGE);
    }

    public void findInSpeedRange(int minSpeed, int maxSpeed) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("---------------------------------------------------------------------------------" +
                "----------------------------------------------------------------------------------------------" +
                "-------------------------------");

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getMaxSpeed() >= minSpeed && this.get(i).getMaxSpeed() <= maxSpeed) {
                strings.add(this.get(i).toString());
                strings.add("-----------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------" +
                        "------------------------------------");
            }
        }
        JOptionPane.showMessageDialog(null, strings.toArray(),
                "Automobiles in range from " + minSpeed + " to " + maxSpeed + " km/h",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void readFromXML(File file) throws ParserConfigurationException, IOException, SAXException,
            NullPointerException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        Node root = document.getDocumentElement();

        this.clear();

        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Automobile automobile = null;
            Carcase carcase = null;
            Engine engine = null;
            Wheel wheel = null;
            CarBody carBody = null;
            SlidingRoof slidingRoof = null;
            NitrousOxideEngine nitro = null;
            RoofRack roofRack = null;
            int wheelAmount = 0,
                    maxSpeed = 0;
            Node item = list.item(i);
            if (item.getNodeType() != Node.TEXT_NODE) {
                NodeList props = item.getChildNodes();
                for (int j = 0; j < props.getLength(); j++) {
                    Node prop = props.item(j);

                    if (prop.getNodeType() != Node.TEXT_NODE) {
                        if (prop.getNodeName().equals("carcase")) {
                            carcase = new Carcase(Color.valueOf(prop.getAttributes().getNamedItem("color").getNodeValue()),
                                    Size.valueOf(prop.getChildNodes().item(1).getTextContent()),
                                    Integer.parseInt(prop.getChildNodes().item(3).getTextContent()));
                        }
                        if (prop.getNodeName().equals("engine")) {
                            engine = new Engine(Integer.parseInt(prop.getChildNodes().item(1).getTextContent()),
                                    Integer.parseInt(prop.getChildNodes().item(3).getTextContent()),
                                    Integer.parseInt(prop.getChildNodes().item(5).getTextContent()),
                                    Double.parseDouble(prop.getChildNodes().item(7).getTextContent()));
                        }
                        if (prop.getNodeName().equals("wheel")) {
                            wheel = new Wheel(Integer.parseInt(prop.getChildNodes().item(1).getTextContent()),
                                    Integer.parseInt(prop.getAttributes().getNamedItem("radius").getNodeValue()));
                        }
                        if (prop.getNodeName().equals("wheelAmount")) {
                            wheelAmount = Integer.parseInt(prop.getTextContent());
                        }
                        if (prop.getNodeName().equals("maxSpeed")) {
                            maxSpeed = Integer.parseInt(prop.getTextContent());
                        }
                        if (prop.getNodeName().equals("carBody")) {
                            carBody = CarBody.valueOf(prop.getTextContent());
                        }
                        if (prop.getNodeName().equals("slidingRoof")) {
                            slidingRoof = SlidingRoof.valueOf(prop.getTextContent());
                        }
                        if (prop.getNodeName().equals("nitro")) {
                            nitro = NitrousOxideEngine.valueOf(prop.getTextContent());
                        }
                        if (prop.getNodeName().equals("roofRack")) {
                            roofRack = RoofRack.valueOf(prop.getTextContent());
                        }
                    }
                }
                if (item.getNodeName().equals("bodyCar")) {
                    assert carBody != null;
                    automobile = new BodyCar(Brand.valueOf(item.getAttributes().getNamedItem("brand").getNodeValue()),
                            carcase, engine, wheel, wheelAmount, maxSpeed, carBody);
                }
                if (item.getNodeName().equals("cabriolet")) {

                    assert slidingRoof != null;
                    automobile = new Cabriolet(Brand.valueOf(item.getAttributes().getNamedItem("brand").getNodeValue()),
                            carcase, engine, wheel, wheelAmount, maxSpeed, slidingRoof);
                    //System.out.println(automobile);
                }
                if (item.getNodeName().equals("sportCar")) {
                    assert nitro != null;
                    automobile = new SportCar(Brand.valueOf(item.getAttributes().getNamedItem("brand").getNodeValue()),
                            carcase, engine, wheel, wheelAmount, maxSpeed, nitro);
                }
                if (item.getNodeName().equals("travelCar")) {
                    assert roofRack != null;
                    automobile = new TravelCar(Brand.valueOf(item.getAttributes().getNamedItem("brand").getNodeValue()),
                            carcase, engine, wheel, wheelAmount, maxSpeed, roofRack);
                }
                assert automobile != null;
                this.add(automobile);
            }
        }

    }
}
