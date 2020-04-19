package mysquare.core;

import org.bson.Document;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Stock {

    public static JTable getStockView(Document query) throws Exception{
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(IMStart.f3);
        table.setRowHeight(20);
        model.addColumn(ApplicationConstants.PRODUCT);
        model.addColumn(ApplicationConstants.COLOUR);
        model.addColumn(ApplicationConstants.WEIGHT);
        model.addColumn(ApplicationConstants.QUANTITY);

        try {
            Db db = new Db();
            ArrayList<JSONObject> jsonObjects = db.fetchData(ApplicationConstants.PRODUCTS,query);
            for(JSONObject jsonObject : jsonObjects){
                model.addRow(new Object[]{
                        jsonObject.getString(ApplicationConstants.COL_NAME),
                        jsonObject.getString(ApplicationConstants.COL_COLOUR),
                        jsonObject.getString(ApplicationConstants.COL_WEIGHT),
                        jsonObject.getString(ApplicationConstants.COL_QUANTITY)});
            }
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }
        table.setGridColor(IMStart.c1);
        return table;
    }
}



