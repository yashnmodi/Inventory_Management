package mysquare.core;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Production {

    public static DefaultTableModel model;

    public static JTable getProductionView() throws Exception{
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("MANUFACTURED ON");
        model.addColumn("PRODUCT");
        model.addColumn("COLOUR");
        model.addColumn("WEIGHT");
        model.addColumn("QUANTITY");

        try {
            Db db = new Db();
            ArrayList<JSONObject> jsonObjects = db.fetchData("Manufactured");
            for(JSONObject jsonObject : jsonObjects){
                model.addRow(new Object[]{
                        jsonObject.getString("when"),
                        jsonObject.getString("name"),
                        jsonObject.getString("clr"),
                        jsonObject.getString("wt"),
                        jsonObject.getInt("qty")});
            }
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }
        table.setGridColor(new Color(239,214,186));
        return table;
    }

    public static JPanel getProductionPanel() throws Exception{
        JPanel panel = new JPanel();
        Db db = new Db();
        JSONObject jsonObject = db.fetchCatalogue();
        JSONArray arr1 = jsonObject.getJSONArray("bottles");
        JSONArray arr2 = jsonObject.getJSONArray("colours");
        JSONArray arr3 = jsonObject.getJSONArray("weights");
        String[] products = arr1.toList().toArray(new String[arr1.length()]);
        String[] colours = arr2.toList().toArray(new String[arr2.length()]);
        String[] weights = arr3.toList().toArray(new String[arr3.length()]);
        JComboBox<String> cb1 = new JComboBox<String>(products);
        JComboBox<String> cb2 = new JComboBox<String>(colours);
        JComboBox<String> cb3 = new JComboBox<String>(weights);

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

        JButton addBtn = new JButton("Add");
        panel.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String product = cb1.getSelectedItem().toString();
                String colour = cb2.getSelectedItem().toString();
                String weight = cb3.getSelectedItem().toString();
                int qty = Integer.parseInt(tf1.getText());
                try {
                    model.setRowCount(0);
                    Db db = new Db();
                    ArrayList<JSONObject> jsonObjects = db.addProduct(product, colour, weight, qty);
                    for(JSONObject jsonObject : jsonObjects){
                        model.addRow(new Object[]{
                                jsonObject.getString("when"),
                                jsonObject.getString("name"),
                                jsonObject.getString("clr"),
                                jsonObject.getString("wt"),
                                jsonObject.getInt("qty")});
                    }
                } catch (Exception e) {
                    try {
                        throw new Exception("Unable to add product.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.setBackground(new Color(239,176,137));
        return panel;
    }
}
