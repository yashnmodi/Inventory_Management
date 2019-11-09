package mysquare.core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Dispatch {

    public static DefaultTableModel model;

    public static JTable getDispatchView() throws Exception{
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("DISPATCHED ON");
        model.addColumn("PRODUCT");
        model.addColumn("COLOUR");
        model.addColumn("WEIGHT");
        model.addColumn("QUANTITY");

        ResultSet data = null;
        try {
            data = Db.fetchData("sold_records");
            while(data.next()) {
                model.addRow(new Object[]{data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5)});
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return table;
    }

    public static JPanel getDispatchPanel(){
        JPanel panel = new JPanel();
        Utility obj = new Utility();
        JComboBox<String> cb1 = new JComboBox<String>(obj.getProductList());
        JComboBox<String> cb2 = new JComboBox<String>(obj.getColourList());
        JComboBox<String> cb3 = new JComboBox<String>(obj.getWeightList());

        // Components Added using Flow Layout
        JLabel lab1 = new JLabel("Product");
        panel.add(lab1);
        panel.add(cb1);

        JLabel lab2 = new JLabel("Colour");
        panel.add(lab2);
        panel.add(cb2);

        JLabel lab3 = new JLabel("Weight");
        panel.add(lab3);
        panel.add(cb3);

        JLabel lab4 = new JLabel("Quantity");
        JTextField tf1 = new JTextField(5);
        panel.add(lab4);
        panel.add(tf1);

        JButton rmvBtn = new JButton("Remove");
        panel.add(rmvBtn);

        rmvBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String product = cb1.getSelectedItem().toString();
                String colour = cb2.getSelectedItem().toString();
                String weight = cb3.getSelectedItem().toString();
                int qty = Integer.parseInt(tf1.getText());
                try {
                    ResultSet dataNew = Db.sellProduct(product, colour, weight, qty);
                    model.setRowCount(0);
                    while(dataNew.next()) {
                        model.addRow(new Object[]{dataNew.getString(1), dataNew.getString(2), dataNew.getString(3), dataNew.getString(4), dataNew.getString(5)});
                    }
                } catch (Exception e) {
                    try {
                        throw new Exception("Unable to sell product.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return panel;
    }
}
