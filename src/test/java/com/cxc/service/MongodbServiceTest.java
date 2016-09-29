package com.cxc.service;

import com.cxc.BaseTest;
import com.cxc.common.service.MongodbService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author:chenxinchao
 * date:2016-09-29 11:54
 * desc:com.cxc.service
 */
public class MongodbServiceTest extends BaseTest {


	@Autowired
	private MongodbService mongodbService;


	@Test
	public void query(){
		DBCollection actconf = mongodbService.getCollection("actconf");
		DBCursor dbObjects = actconf.find(new BasicDBObject());
		while (dbObjects.hasNext()){
			DBObject next = dbObjects.next();
			System.out.println("item:"+next.toString());
		}

	}
}
