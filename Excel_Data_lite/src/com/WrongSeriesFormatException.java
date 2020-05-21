package com;

public class WrongSeriesFormatException extends Exception {
    public WrongSeriesFormatException() {
        super("Wrong format of serials formula!");
    }

    public WrongSeriesFormatException(String s) {
        super(s);
    }
}
