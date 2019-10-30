package com.mongo;
import java.net.UnknownHostException;
import java.util.Date;

import com.jsonreader.DataRow;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;



//db.createCollection("MASTER_DATA_SEARCH")
//db.MASTER_DATA_SEARCH.createIndex( {'year':1,'make': 1,'model':1,'interchange':1},{unique: true} )
public class MongoClt {
	public MongoClient mongo = null;
	public static void main(String[] str) {
		MongoClt clt	=	new MongoClt();
		clt.getConnection("");
		DataRow row	=	new DataRow();
		try {
			clt.insertDoc(row);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public void insertDoc(DataRow dataRow) throws Exception {
		try {
			DB db = mongo.getDB("cloudsearch");
			DBCollection table = db.getCollection("MASTER_DATA_SEARCH");

			BasicDBObject document = new BasicDBObject();
			document.put("category",dataRow.getSheetCategory());
			document.put("interchange", dataRow.getInterchange());
			document.put("createdDate", dataRow.getNumber());
			document.put("input", dataRow.getSheetInput());
			document.put("make", dataRow.getSheetMakeStr());
			document.put("model", dataRow.getSheetModelStr());
			document.put("yearmakemodle", dataRow.getSheetYearmakemodel());
			document.put("yearmakemodle_desc", dataRow.getSheetYearmakemodel_desc());
			document.put("year", dataRow.getSheetYearStr());
			
			document.put("type", dataRow.getType());
			document.put("corePrice", dataRow.getCorePrice());
			document.put("price", dataRow.getPrice());
			

			
			table.insert(document);

		} catch (DuplicateKeyException e) {
			throw e;
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection(String args) {

	    try {
	    	MongoClientOptions.Builder options = MongoClientOptions.builder()
	                .connectionsPerHost(4)
	                .maxConnectionIdleTime((60 * 1_000))
	                .maxConnectionLifeTime((120 * 1_000));
	                ;
	    	MongoClientURI uri1 = new MongoClientURI("mongodb://webuser:Donkey%40123@52.90.61.141:27017/eventstore", options);
			MongoClientURI uri = new MongoClientURI(
				    "mongodb+srv://admin_rahul:ILovePune2003@cluster0-mokax.mongodb.net/admin?retryWrites=true&w=majority");
			mongo = new MongoClient(uri1);
		
	    } catch (MongoException e) {
		e.printStackTrace();
	    } 	catch (Exception e) {
			e.printStackTrace();
	    }

	  }
}
