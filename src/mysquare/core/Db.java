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
	private final String mongoURI = "mongodb+srv://yash:admin@dataone-iedyp.mongodb.net/rbp?retryWrites=true&w=majority";
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public ArrayList<JSONObject> fetchData(String tableName, Document query) throws Exception {
		ArrayList<JSONObject> jsonList = new ArrayList<>();
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(tableName);
		FindIterable<Document> findIterable = collection.find(query).sort(new BasicDBObject("name", 1));
		MongoCursor<Document> cursor = findIterable.iterator();
		while (cursor.hasNext()){
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		mongoClient.close();
		return jsonList;
	}

	public void addItem(String type, String value) throws Exception {
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(ApplicationConstants.CATALOGUE);
		Document updates = new Document("$push", new Document(type, value));
		collection.updateOne(new Document(), updates);
		mongoClient.close();
	}

	public void removeItem(String type, String value) throws Exception {
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(ApplicationConstants.CATALOGUE);
		Document updates = new Document("$pull",new Document(type,value));
		collection.updateOne(new Document(),updates);
		mongoClient.close();
	}

	public ArrayList<JSONObject> addProduct(String product, String colour, String weight, int qty, String type) throws Exception{
		Document query = new Document(
				ApplicationConstants.COL_NAME,product)
				.append(ApplicationConstants.COL_COLOUR,colour)
				.append(ApplicationConstants.COL_WEIGHT,weight)
				.append(ApplicationConstants.COL_TYPE,type);
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(ApplicationConstants.PRODUCTS);
		FindIterable<Document> findIterable = collection.find(query);
		MongoCursor<Document> cursor = findIterable.iterator();
		int availableQty = 0;
		while (cursor.hasNext()){
			availableQty = cursor.next().getInteger("qty");
		}
		if(0 == availableQty){
			query.append(ApplicationConstants.COL_QUANTITY,qty);
			collection.insertOne(query);
			collection = database.getCollection(ApplicationConstants.MANUFACTURED);
			query.append(ApplicationConstants.COL_DATE,dateFormat.format(new Date()));
			collection.insertOne(query);
		} else {
			int newQty = qty + availableQty;
			Document updated = new Document(ApplicationConstants.COL_QUANTITY,newQty).append(ApplicationConstants.COL_TYPE,type);
			collection.updateOne(query, new Document("$set",updated));
			collection = database.getCollection(ApplicationConstants.MANUFACTURED);
			query.append(ApplicationConstants.COL_DATE,dateFormat.format(new Date()));
			collection.insertOne(query);
		}

		ArrayList<JSONObject> jsonList = new ArrayList<>();
		findIterable = collection.find(new Document(ApplicationConstants.COL_TYPE,type));
		cursor = findIterable.iterator();
		while (cursor.hasNext()){
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		mongoClient.close();
		return jsonList;
	}

	public ArrayList<JSONObject> sellProduct(String product, String colour, String weight, int qty, String type) throws Exception{
		Document query = new Document(ApplicationConstants.COL_NAME,product)
				.append(ApplicationConstants.COL_COLOUR,colour)
				.append(ApplicationConstants.COL_WEIGHT,weight)
				.append(ApplicationConstants.COL_TYPE,type);
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(ApplicationConstants.PRODUCTS);
		FindIterable<Document> findIterable = collection.find(query);
		MongoCursor<Document> cursor = findIterable.iterator();
//		int availableQty = 0;
		if (cursor.hasNext()){
			int availableQty = cursor.next().getInteger(ApplicationConstants.COL_QUANTITY);
			availableQty -= qty;
//			if(availableQty <= 0){
//				collection.deleteOne(filter);
//				collection = database.getCollection(ApplicationConstants.DISPATCHED);
//				filter.append("qty",qty).append("when",dateFormat.format(new Date())).append("type",type);
//				collection.insertOne(filter);
//			} else{}
			Document updated = new Document(ApplicationConstants.COL_QUANTITY,availableQty).append(ApplicationConstants.COL_TYPE,type);
			collection.updateOne(query, new Document("$set",updated));
			collection = database.getCollection(ApplicationConstants.DISPATCHED);
			query.append(ApplicationConstants.COL_DATE,dateFormat.format(new Date()));
			collection.insertOne(query);
		} else{
			System.out.println("Product Not Found!");
		}

		ArrayList<JSONObject> jsonList = new ArrayList<>();
		findIterable = collection.find(new Document(ApplicationConstants.COL_TYPE,type));
		cursor = findIterable.iterator();
		while (cursor.hasNext()){
			jsonList.add(new JSONObject(cursor.next().toJson()));
		}
		mongoClient.close();
		return jsonList;
	}

	public JSONObject fetchCatalogue() {
		JSONObject jsonObject = null;
		MongoClient mongoClient = MongoClients.create(mongoURI);
		MongoDatabase database = mongoClient.getDatabase(ApplicationConstants.DATABASE);
		MongoCollection collection = database.getCollection(ApplicationConstants.CATALOGUE);
		FindIterable<Document> findIterable = collection.find(new Document()).sort(new BasicDBObject("name",1));
		MongoCursor<Document> cursor = findIterable.iterator();
		while (cursor.hasNext()){
			jsonObject = new JSONObject(cursor.next().toJson());
		}
		mongoClient.close();
		return jsonObject;
	}
}