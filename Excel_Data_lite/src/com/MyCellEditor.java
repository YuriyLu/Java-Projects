package com;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JTable jTable;
    private Formula formula;
    private int row, col;
    private JTextField text = new JTextField();
    boolean trigger = true;

    final static Pattern DATE = Pattern.compile(
            "(" +
                    "((0[1-9]|[12]\\d|3[01])\\.(0[13578]|1[02])\\.((19|[2-9]\\d)\\d{2}))|" +
                    "((0[1-9]|[12]\\d|30)\\.(0[13456789]|1[012])\\.((19|[2-9]\\d)\\d{2}))|" +
                    "((0[1-9]|1\\d|2[0-8])\\.02\\.((19|[2-9]\\d)\\d{2}))|" +
                    "(29\\.02\\.((1[6-9]|[2-9]\\d)" +
                    "(0[48]|[2468][048]|[13579][26]))" +
                    ")" +
                    ")");
    final static Pattern CELL = Pattern.compile("[A-Z][1-9]+[0-9]*");

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        this.jTable = jTable;
        formula = (Formula) o;
        row = i;
        col = i1;
        if (formula != null) {
            text.setText(formula.getCurrentFormula());
        } else {
            text.setText("");
        }
        return text;
    }

    @Override
    public Object getCellEditorValue() {
        try {
            if (text.getText().equals("")) {
                return new Formula();
            }
            formula = parseFormula(text.getText());
            formula.setCurrentFormula(text.getText());
            jTable.getModel().setValueAt(formula, row, col);
        } catch (MyException | WrongSeriesFormatException | WrongDataFormatException | WrongMaxMinFormulaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        if (trigger && formula!=null){
            trigger = false;
            for (int i = 0; i < jTable.getRowCount(); i++) {
                for (int j = 1; j < jTable.getColumnCount(); j++) {
                    Formula formula = (Formula) jTable.getValueAt(i, j);
                    if (formula != null) {
                        jTable.editCellAt(i, j);
                    }
                }
            }
            for (int i = jTable.getRowCount() - 1; i >= 0; i--) {
                for (int j = jTable.getColumnCount() - 1; j > 0; j--) {
                    Formula formula = (Formula) jTable.getValueAt(i, j);
                    if (formula != null) {
                        jTable.editCellAt(i, j);
                    }
                }
            }
            trigger = true;
        }

        return formula;
    }


    Formula parseFormula(String string) throws MyException, WrongSeriesFormatException, WrongDataFormatException, WrongMaxMinFormulaException {
        Matcher matcher = DATE.matcher(string);
        if (matcher.matches()) {
            return parseDateFromStr(string);
        } else {
            if (string.charAt(0) == '=' && string.charAt(string.length() - 1) == ')') {
                if (string.charAt(1) == '(') {
                    return parseSeries(string.substring(2, string.length() - 1));
                }
                if ((string.substring(1, 5)).equals("min(")) {
                    return parseMaxMinFormula(string.substring(5, string.length() - 1), true);
                } else {
                    if ((string.substring(1, 5)).equals("max(")) {
                        return parseMaxMinFormula(string.substring(5, string.length() - 1), false);
                    }
                }
            } else {
                throw new WrongDataFormatException();
            }
        }

        return null;
    }

    Formula parseDateFromStr(String string) {
        int day = Integer.parseInt(string.substring(0, 2));
        int month = Integer.parseInt(string.substring(3, 5));
        int year = Integer.parseInt(string.substring(6));
        return new Formula(day, month, year);
    }

    Formula parseSeries(String string) throws MyException, WrongSeriesFormatException {
        try {
            if (string.charAt(0) >= 'A' && string.charAt(0) <= 'Z') {
                Formula date = fromCell(string.substring(0, 2));
                if (string.length() == 2) {
                    return date;
                }
                if (string.charAt(2) == '-') {
                    date.add(GregorianCalendar.DAY_OF_MONTH, -1 * Integer.parseInt(string.substring(3)));
                    return date;
                } else if (string.charAt(2) == '+') {
                    date.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(string.substring(3)));
                    return date;
                } else {
                    throw new WrongSeriesFormatException();
                }
            } else {
                if (string.length() < 10) {
                    throw new WrongSeriesFormatException();
                }
                Matcher matcher = DATE.matcher(string.substring(0, 10));
                if (matcher.matches()) {
                    Formula newDate = parseDateFromStr(string.substring(0, 10));
                    if (string.charAt(10) == '-') {
                        newDate.add(GregorianCalendar.DAY_OF_MONTH, -1 * Integer.parseInt(string.substring(11)));
                        return newDate;
                    } else if (string.charAt(10) == '+') {
                        newDate.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(string.substring(11)));
                        return newDate;
                    } else {
                        throw new WrongSeriesFormatException();
                    }
                } else {
                    throw new WrongSeriesFormatException();
                }
            }
        } catch (NumberFormatException ex) {
            throw new WrongSeriesFormatException();
        }

    }

    Formula fromCell(String string) throws MyException {
        int column = (int) (string.charAt(0)) - 64;
        int row = Integer.parseInt(string.substring(1)) - 1;
        if (column >= jTable.getColumnCount() || row >= jTable.getRowCount()) {
            throw new MyException("Out of bounds!");
        }

        Formula newFormula = (Formula) jTable.getValueAt(row, column);
        if (newFormula == null) {
            throw new MyException("Wrong format of data in cell: " + row + " " + column + "!");
        }
        return parseDateFromStr(newFormula.toString());
    }

    Formula parseMaxMinFormula(String string, boolean flag) throws MyException, WrongMaxMinFormulaException {
        Matcher matcher = DATE.matcher(string);
        ArrayList<Formula> list = new ArrayList<>();
        String s;
        while (matcher.find()) {
            s = matcher.group();
            list.add(parseDateFromStr(matcher.group()));
        }
        matcher = CELL.matcher(string);
        while (matcher.find()) {
            s = matcher.group();
            list.add(fromCell(s));
        }
        if (list.isEmpty()) {
            throw new WrongMaxMinFormulaException();
        }
        if (flag) {
            return Collections.min(list);
        } else return Collections.max(list);
    }
}
