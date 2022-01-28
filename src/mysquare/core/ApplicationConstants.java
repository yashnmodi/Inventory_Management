package mysquare.core;

public class ApplicationConstants {

    /* UI - Menubar, Panels, Buttons, Frames */
    static final String APP_NAME = "Inventory Management";
    static final String APP_VERSION = " v2.0";
    static final String ORGANIZATION = "Raj Blow Plast";
    static final String[] MENUBAR = new String[]{"View","Operations","Help"};
    static final String BOTTLES = "Bottles";
    static final String CAPS = "Caps";
    static final String PRODUCTION = "Production";
    static final String DISPATCH = "Dispatch";
    static final String STOCK = "Stock";
    static final String M3I1 = "Modify products";
    static final String ABOUT = "About Software";
    static final String DEVELOPER = "Inventory Management System (IMS) v2.0\nAn open source project developed by Yash Modi\n\nVisit https://github.com/yashnmodi/Inventory_Management";
    static final String[] CATALOGUE_ITMES = {ApplicationConstants.BOTTLES,ApplicationConstants.CAPS,ApplicationConstants.COL_COLOUR_BOTTLES,ApplicationConstants.COL_COLOUR_CAPS,ApplicationConstants.COL_WEIGHT_BOTTLES,ApplicationConstants.COL_WEIGHT_CAPS};

    /* Database, Tables & Columns */
    static final String DATABASE = "YOUR DB NAME";
    static final String PRODUCTS = "Products";
    static final String CATALOGUE = "Catalogue";
    static final String MANUFACTURED = "Manufactured";
    static final String DISPATCHED = "Dispatched";
    static final String COL_NAME = "name";
    static final String COL_COLOUR = "clr";
    static final String COL_QUANTITY = "qty";
    static final String COL_WEIGHT = "wt";
    static final String COL_DATE = "when";
    static final String COL_TYPE = "type";
    static final String COL_COLOURS = "Colours";
    static final String COL_WEIGHTS = "Weights";
    static final String COL_COLOUR_CAPS = "ColoursCaps";
    static final String COL_WEIGHT_CAPS = "WeightsCaps";
    static final String COL_COLOUR_BOTTLES = "ColoursBottles";
    static final String COL_WEIGHT_BOTTLES = "WeightsBottles";

    /* JTable Columns */
    static final String MANUFACTURED_DATE = "MANUFACTURED ON";
    static final String DISPATCHED_DATE = "DISPATCHED ON";
    static final String PRODUCT = "PRODUCT";
    static final String COLOUR = "COLOUR";
    static final String WEIGHT = "WEIGHT";
    static final String QUANTITY = "QUANTITY";
    static final String[] QTY_UNITS = new String[]{"Nos","Kg"};
    static final String[] SHIFT = new String[]{"Day","Night"};
}
//remained
//printing, reports, searching