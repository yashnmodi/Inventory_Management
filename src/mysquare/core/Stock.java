package mysquare.core;

import org.bson.Document;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Stock {

    public static JTable getStockView(String viewName) throws Exception{
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("PRODUCT");
        model.addColumn("COLOUR");
        model.addColumn("WEIGHT");
        model.addColumn("QUANTITY");

        try {
            Db db = new Db();
            Document query = new Document("type",viewName);
            ArrayList<JSONObject> jsonObjects = db.fetchData("Products",query);
            for(JSONObject jsonObject : jsonObjects){
                model.addRow(new Object[]{
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
}



