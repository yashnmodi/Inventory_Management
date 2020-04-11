package mysquare.core;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Dispatch {

    public static DefaultTableModel model;

    public static JTable getDispatchView(Document query) throws Exception{
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setEnabled(false);
        model.addColumn(ApplicationConstants.DISPATCHED_DATE);
        model.addColumn(ApplicationConstants.PRODUCT);
        model.addColumn(ApplicationConstants.COLOUR);
        model.addColumn(ApplicationConstants.WEIGHT);
        model.addColumn(ApplicationConstants.QUANTITY);

        try {
            Db db = new Db();
            ArrayList<JSONObject> jsonObjects = db.fetchData(ApplicationConstants.DISPATCHED,query);
            for(JSONObject jsonObject : jsonObjects){
                model.addRow(new Object[]{
                        jsonObject.getString(ApplicationConstants.COL_DATE),
                        jsonObject.getString(ApplicationConstants.COL_NAME),
                        jsonObject.getString(ApplicationConstants.COL_COLOUR),
                        jsonObject.getString(ApplicationConstants.COL_WEIGHT),
                        jsonObject.getInt(ApplicationConstants.COL_QUANTITY)});
            }
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }
        table.setGridColor(new Color(239,214,186));
        return table;
    }

    public static JPanel getDispatchPanel(String productType){
        JPanel panel = new JPanel();
        if(!CachedCatalogue.latestCatalogue)
            new CachedCatalogue();
        JSONArray arr1 = null;
        if (ApplicationConstants.BOTTLES.equals(productType))
            arr1 = CachedCatalogue.cachedCatalogue.get(ApplicationConstants.BOTTLES);
        if(ApplicationConstants.CAPS.equals(productType))
            arr1 = CachedCatalogue.cachedCatalogue.get(ApplicationConstants.CAPS);
        JSONArray arr2 = CachedCatalogue.cachedCatalogue.get(ApplicationConstants.COL_COLOURS);
        JSONArray arr3 = CachedCatalogue.cachedCatalogue.get(ApplicationConstants.COL_WEIGHTS);
        String[] products = arr1.toList().toArray(new String[arr1.length()]);
        String[] colours = arr2.toList().toArray(new String[arr1.length()]);
        String[] weights = arr3.toList().toArray(new String[arr1.length()]);
        JComboBox<String> cb1 = new JComboBox<>(products);
        JComboBox<String> cb2 = new JComboBox<>(colours);
        JComboBox<String> cb3 = new JComboBox<>(weights);
        JComboBox<String> cb6 = new JComboBox<>(new String[]{"Nos","Kg"});

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
        JTextField tf1 = new JTextField(5);
        panel.add(lab4);
        panel.add(tf1);

        if (ApplicationConstants.CAPS.equals(productType)){
            panel.add(cb6);
        }

        JButton rmvBtn = new JButton("Remove");
        panel.add(rmvBtn);

        rmvBtn.addActionListener(arg0 -> {
            String product = cb1.getSelectedItem().toString();
            String colour = cb2.getSelectedItem().toString();
            String weight = cb3.getSelectedItem().toString();
            int qty = Integer.parseInt(tf1.getText());
            String unitOfQty;
            if (ApplicationConstants.CAPS.equals(productType)) {
                unitOfQty = cb6.getSelectedItem().toString();
            }
            try {
                model.setRowCount(0);
                Db db1 = new Db();
                ArrayList<JSONObject> jsonObjects = db1.sellProduct(product, colour, weight, qty, productType);
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
                    e.printStackTrace();
                    throw new Exception("Unable to sell product.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.setBackground(new Color(239,176,137));
        return panel;
    }
}
