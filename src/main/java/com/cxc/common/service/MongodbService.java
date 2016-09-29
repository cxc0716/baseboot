package com.cxc.common.service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

/**
 * author:chenxinchao
 * date:2016-09-29 11:49
 * desc:com.cxc.common.service
 */
@Component
public class MongodbService {

	@Autowired
	private MongoDbFactory mongo;

	public DB getDB(){
		return mongo.getDb();
	}

	public DBCollection  getCollection(String collectionName){
		 return mongo.getDb().getCollection(collectionName);
	}

}
