package mysquare.core;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Db {
	private final String mongoURI = "mongodb+srv://YOUR-CONNECTION_STRING";
	DateFormat dateFormat = new SimpleDateFormat("EEE dd-MMM-yyyy HH:mm");

	public ArrayList<JSONObject> fetchData(String tableName) throws Exception {
		ArrayList<JSONObject> jsonList = new ArrayList<>();
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection(tableName);
		FindIterable<Document> findIterable = collection.find(new Document()).sort(new BasicDBObject("name", 1));
		MongoCursor<Document> cursor = findIterable.iterator();
		while (cursor.hasNext()) {
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		System.out.println("JSON - " + jsonList.toString());
		mongoClient.close();
		return jsonList;
	}

	public void addItem(String type, String value) throws Exception {
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection("Catalogue");
		Document updates = new Document("$push", new Document(type, value));
		collection.updateOne(new Document(), updates);
		mongoClient.close();
	}

	public void removeItem(String type, String value) throws Exception {
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection("Catalogue");
		Document updates = new Document("$pull", new Document(type, value));
		collection.updateOne(new Document(), updates);
		mongoClient.close();
	}

	public ArrayList<JSONObject> addProduct(String product, String colour, String weight, int qty) throws Exception {
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection("Products");
		Document filter = new Document("name", product);
		filter.append("clr", colour).append("wt", weight);
		FindIterable<Document> findIterable = collection.find(filter);
		MongoCursor<Document> cursor = findIterable.iterator();
		int availableQty = 0;
		while (cursor.hasNext()) {
			availableQty = cursor.next().getInteger("qty");
		}
		if (0 == availableQty) {
			collection.insertOne(filter.append("qty", qty));
			collection = database.getCollection("Manufactured");
			filter.append("when", dateFormat.format(new Date()));
			collection.insertOne(filter.append("qty", qty));
		} else {
			int newQty = qty + availableQty;
			collection.updateOne(filter, new Document("$set", new Document("qty", newQty)));
			collection = database.getCollection("Manufactured");
			filter.append("when", dateFormat.format(new Date()));
			collection.insertOne(filter.append("qty", qty));
		}

		ArrayList<JSONObject> jsonList = new ArrayList<>();
		findIterable = collection.find(new Document());
		cursor = findIterable.iterator();
		while (cursor.hasNext()) {
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		mongoClient.close();
		return jsonList;
	}

	public ArrayList<JSONObject> sellProduct(String product, String colour, String weight, int qty) throws Exception {

		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection("Products");
		Document filter = new Document("name", product);
		filter.append("clr", colour).append("wt", weight);
		FindIterable<Document> findIterable = collection.find(filter);
		MongoCursor<Document> cursor = findIterable.iterator();
		int availableQty = 0;
		if (cursor.hasNext()) {
			availableQty = cursor.next().getInteger("qty");
			availableQty -= qty;
			if (availableQty <= 0) {
				collection.deleteOne(filter);
				collection = database.getCollection("Dispatched");
				filter.append("qty", qty).append("when", dateFormat.format(new Date()));
				collection.insertOne(filter);
			} else {
				collection.updateOne(filter, new Document("$set", new Document("qty", availableQty)));
				collection = database.getCollection("Dispatched");
				filter.append("qty", qty).append("when", dateFormat.format(new Date()));
				collection.insertOne(filter);
			}

		} else {
			System.out.println("Product Not Found!");
		}

		ArrayList<JSONObject> jsonList = new ArrayList<>();
		findIterable = collection.find(new Document());
		cursor = findIterable.iterator();
		while (cursor.hasNext()) {
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		mongoClient.close();
		return jsonList;
	}

	public JSONObject fetchCatalogue() {
		JSONObject jsonObject = null;
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase("YOUR_DB_NAME");
		MongoCollection collection = database.getCollection("Catalogue");
		FindIterable<Document> findIterable = collection.find(new Document());
		MongoCursor<Document> cursor = findIterable.iterator();
		while (cursor.hasNext()) {
			jsonObject = new JSONObject(cursor.next().toJson());
		}
		mongoClient.close();
		return jsonObject;
	}
}