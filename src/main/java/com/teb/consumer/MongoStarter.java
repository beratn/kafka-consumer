package com.teb.consumer;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.teb.consumer.Interfaces.IMongoDbConstants;

public class MongoStarter {

    private DB getConnection() {
        try {

            MongoClient mongoClient = new MongoClient(IMongoDbConstants.MONGODB_HOST, IMongoDbConstants.MONGODB_PORT);
            DB db = mongoClient.getDB(IMongoDbConstants.DB_NAME);
//            boolean auth = db.authenticate(IMongoDbConstants.USERNAME,IMongoDbConstants.PASSWORD.toCharArray());

            return db;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void save(String json) {
        DB db = getConnection();
        DBObject dbObject = (DBObject) JSON.parse(json);
        try {
            DBCollection collection = db.getCollection(IMongoDbConstants.COLLECTION_NAME);
            if (collection == null) {
                collection = db.createCollection(IMongoDbConstants.COLLECTION_NAME, null);
            }
            collection.insert(dbObject);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
