package mysquare.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CachedCatalogue {
    public static boolean latestCatalogue = false;

    public static HashMap<String, JSONArray> cachedCatalogue = new HashMap<>();

    CachedCatalogue(){
        Db db = new Db();
        JSONObject jsonObject = db.fetchCatalogue();
        cachedCatalogue.put(ApplicationConstants.BOTTLES, jsonObject.getJSONArray(ApplicationConstants.BOTTLES));
        cachedCatalogue.put(ApplicationConstants.CAPS, jsonObject.getJSONArray(ApplicationConstants.CAPS));
        cachedCatalogue.put(ApplicationConstants.COL_COLOURS, jsonObject.getJSONArray(ApplicationConstants.COL_COLOURS));
        cachedCatalogue.put(ApplicationConstants.COL_WEIGHTS, jsonObject.getJSONArray(ApplicationConstants.COL_WEIGHTS));
        latestCatalogue = true;
    }

}
