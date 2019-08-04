package mysquare.core;

import java.util.ArrayList;

public class Utility {
	
	private static String dbSource = "jdbc:sqlite:H:/Work Station/Java/IMS/database/rbp.db";
	
	public static void setSource(String str) {
		dbSource = str;
	}
	
	public static String getSource() {
		return dbSource;
	}
	
	public static String[] getProductList() {
		ArrayList<String> productList = Db.fetchPList();
	    String[] pArr = productList.toArray(new String[productList.size()]);
	    return pArr;
	}
	
	public static String[] getColourList() {
		ArrayList<String> colourList = Db.fetchCList();
	    String[] cArr = colourList.toArray(new String[colourList.size()]);
	    return cArr;
   	}
	
	public static String[] getWeightList() {
		ArrayList<String> weightList = Db.fetchWList();
		String[] wArr = weightList.toArray(new String[weightList.size()]);
		return wArr;
	}
}