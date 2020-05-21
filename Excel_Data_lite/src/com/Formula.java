package com;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Formula extends GregorianCalendar{
    private String currentFormula;
    private boolean unchanged;

    public Formula() {
        unchanged = true;
    }

    public Formula(int day, int month, int year) {
        super(year, month - 1, day);
        unchanged = false;
        this.currentFormula = this.toString();
    }

    public String getCurrentFormula() {
        return currentFormula;
    }

    public void setCurrentFormula(String currentFormula) {
        this.currentFormula = currentFormula;
    }

    @Override
    public String toString() {
        if (unchanged){
            return "";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        fmt.setCalendar(this);
        return fmt.format(this.getTime());
    }

}
