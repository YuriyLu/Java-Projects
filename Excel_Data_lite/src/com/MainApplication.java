package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class MainApplication extends JFrame {
    public static void main(String[] args) {
        new MainApplication();
    }

    MainApplication() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            public Class getColumnClass(int column) {
                return Formula.class;
            }

            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                else
                    return super.isCellEditable(row, column);
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        Vector<Integer> vector = new Vector<>(35);
        for (int i = 0; i < vector.capacity(); i++) {
            vector.add(i + 1);
        }


            tableModel.addColumn("", vector);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int i = 65; i < 91; i++) {
            tableModel.addColumn((char) i);
            table.getColumnModel().getColumn(i - 65).setPreferredWidth(100);
        }
        tableModel.setValueAt(new Formula(16, 12, 2001), 0, 2);
        table.getModel().setValueAt(new Formula(16, 12, 2001), 0, 2);

        table.setDefaultEditor(Formula.class, new MyCellEditor());
        table.setRowHeight(30);

        tableModel.setValueAt(new Formula(12, 12, 2000), 0, 1);

        table.getModel().setValueAt(new Formula(12, 07, 2002), 1, 1);
        table.getModel().setValueAt(new Formula(04, 12, 2003), 0, 3);
        table.getModel().setValueAt(new Formula(12, 11, 2004), 2, 1);
        table.getModel().setValueAt(new Formula(23, 02, 2005), 0, 4);
        table.getModel().setValueAt(new Formula(23, 12, 2006), 3, 1);


        setContentPane(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1600, 800));
        setPreferredSize(new Dimension(1600, 800));
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
