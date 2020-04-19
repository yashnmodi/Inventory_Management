package mysquare.core;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;

public class CachedCatalogue {
    public static boolean latestCatalogue = false;

    public static HashMap<String, JSONArray> cachedCatalogue = new HashMap<>();

    CachedCatalogue(){
        Db db = new Db();
        JSONObject jsonObject = db.fetchCatalogue();
        cachedCatalogue.put(ApplicationConstants.BOTTLES, jsonObject.getJSONArray(ApplicationConstants.BOTTLES));
        cachedCatalogue.put(ApplicationConstants.CAPS, jsonObject.getJSONArray(ApplicationConstants.CAPS));
        cachedCatalogue.put(ApplicationConstants.COL_COLOUR_BOTTLES, jsonObject.getJSONArray(ApplicationConstants.COL_COLOUR_BOTTLES));
        cachedCatalogue.put(ApplicationConstants.COL_COLOUR_CAPS, jsonObject.getJSONArray(ApplicationConstants.COL_COLOUR_CAPS));
        cachedCatalogue.put(ApplicationConstants.COL_WEIGHT_BOTTLES, jsonObject.getJSONArray(ApplicationConstants.COL_WEIGHT_BOTTLES));
        cachedCatalogue.put(ApplicationConstants.COL_WEIGHT_CAPS, jsonObject.getJSONArray(ApplicationConstants.COL_WEIGHT_CAPS));
        latestCatalogue = true;
    }

}
