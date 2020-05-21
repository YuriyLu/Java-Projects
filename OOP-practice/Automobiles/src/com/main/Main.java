package com.main;
import com.carhierarchy.*;
import com.enums.hierarchyenum.SlidingRoof;
import com.park.TaxiPark;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        TaxiPark taxiPark = new TaxiPark();
        try {
            taxiPark.readFromXML(new File("src/Automobiles.xml"));
            taxiPark.sortingByFuelConsumption();
            taxiPark.printTotalCost();
            taxiPark.showInWindow();
            taxiPark.findInSpeedRange(120, 200);
        } catch (ParserConfigurationException | IOException | SAXException | NullPointerException e) {
            System.out.println("Wrong input format!");
        }

    }
}
