package com;

public class WrongDataFormatException extends Exception {
    public WrongDataFormatException() {
        super("Wrong data format! Use format like \"dd.mm.yyyy\"");
    }

    WrongDataFormatException(String s) {
        super(s);
    }
}
