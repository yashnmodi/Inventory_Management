package mysquare.core;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CachedDb {
    public static boolean latestCatalogue = false;
    public static boolean latestStock = false;

    public static HashMap<Object,Object> cachedData = new HashMap<>();

    CachedDb(){
//        Db db = new Db();
//        JSONObject jsonObject = db.fetchCatalogue();
        cachedData.put("catalogue", null);
        cachedData.put("isCatalogueLatest",latestCatalogue);
        cachedData.put("isStockLatest",latestStock);
    }

}
