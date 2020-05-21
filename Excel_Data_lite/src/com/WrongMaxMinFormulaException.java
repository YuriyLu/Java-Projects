package com;

public class WrongMaxMinFormulaException extends Exception{
    public WrongMaxMinFormulaException() {
        super("Wrong format of min/max formula!");
    }

    public WrongMaxMinFormulaException(String s) {
        super(s);
    }
}
