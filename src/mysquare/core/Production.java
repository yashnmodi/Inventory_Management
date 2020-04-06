package mysquare.core;

import org.bson.Document;
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

    public static JTable getProductionView(Document query) throws Exception{
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn(ApplicationConstants.MANUFACTURED_DATE);
        model.addColumn(ApplicationConstants.PRODUCT);
        model.addColumn(ApplicationConstants.COLOUR);
        model.addColumn(ApplicationConstants.WEIGHT);
        model.addColumn(ApplicationConstants.QUANTITY);

        try {
            Db db = new Db();
            ArrayList<JSONObject> jsonObjects = db.fetchData("Manufactured",query);
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
        JSONArray arr1 = jsonObject.getJSONArray(ApplicationConstants.BOTTLES);
        JSONArray arr2 = jsonObject.getJSONArray(ApplicationConstants.COL_COLOURS);
        JSONArray arr3 = jsonObject.getJSONArray(ApplicationConstants.COL_WEIGHTS);
        String[] products = arr1.toList().toArray(new String[arr1.length()]);
        String[] colours = arr2.toList().toArray(new String[arr2.length()]);
        String[] weights = arr3.toList().toArray(new String[arr3.length()]);
        JComboBox<String> cb1 = new JComboBox<>(products);
        JComboBox<String> cb2 = new JComboBox<>(colours);
        JComboBox<String> cb3 = new JComboBox<>(weights);
        JComboBox<String> cb5 = new JComboBox<>(new String[]{"Day","Night"});

        // Components Added using Flow Layout
        JLabel lab1 = new JLabel(ApplicationConstants.PRODUCT);
        panel.add(lab1);
        panel.add(cb1);

        JLabel lab2 = new JLabel(ApplicationConstants.COLOUR);
        panel.add(lab2);
        panel.add(cb2);

        JLabel lab3 = new JLabel(ApplicationConstants.WEIGHT);
        panel.add(lab3);
        panel.add(cb3);

        JLabel lab4 = new JLabel(ApplicationConstants.QUANTITY);
        JTextField tf1 = new JTextField(6);
        panel.add(lab4);
        panel.add(tf1);

        JLabel lab5 = new JLabel("Shift");
        panel.add(lab5);
        panel.add(cb5);

        JButton addBtn = new JButton("Add");
        panel.add(addBtn);

        addBtn.addActionListener(arg0 -> {
            String product = cb1.getSelectedItem().toString();
            String colour = cb2.getSelectedItem().toString();
            String weight = cb3.getSelectedItem().toString();
            int qty = Integer.parseInt(tf1.getText());
            String productType = cb5.getSelectedItem().toString();
            try {
                model.setRowCount(0);
                Db db1 = new Db();
                ArrayList<JSONObject> jsonObjects = db1.addProduct(product, colour, weight, qty, productType);
                for(JSONObject jsonObject1 : jsonObjects){
                    model.addRow(new Object[]{
                            jsonObject1.getString(ApplicationConstants.COL_DATE),
                            jsonObject1.getString(ApplicationConstants.COL_NAME),
                            jsonObject1.getString(ApplicationConstants.COL_COLOUR),
                            jsonObject1.getString(ApplicationConstants.COL_WEIGHT),
                            jsonObject1.getInt(ApplicationConstants.COL_QUANTITY)});
                }
            } catch (Exception e) {
                try {
                    throw new Exception("Unable to add product.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.setBackground(new Color(239,176,137));
        return panel;
    }
}
