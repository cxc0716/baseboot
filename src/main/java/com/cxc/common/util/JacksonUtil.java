package com.cxc.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JacksonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
	private static ObjectMapper objectMapper = null;

	static  {
			objectMapper = new ObjectMapper();
//			objectMapper.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);
			objectMapper.setDateFormat(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"));
			//jackson 默认时间会少8小时，要设置timezone
			objectMapper.setTimeZone(TimeZone.getDefault());
			//json to object时  忽略object中找不到的字段
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

	}

	public static String write(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);

		} catch (IOException e) {
			logger.warn("jackson write exception:{},{}",e,obj);
			return "";
		}

	}

	public static <T> List<T> readArray(String str, Class<T> entityClss) {
		CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, entityClss);
		try {
			return objectMapper.readValue(str, collectionType);
		} catch (IOException e) {
			logger.warn("jackson readArray exception:{}",e);
			return Lists.newArrayList();
		}
	}
	public static <T> List<Map<String,T>> readArrayMap(String str,Class<T> valClss){
		MapType constructMapType = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, valClss);
		JavaType javaType  = objectMapper.getTypeFactory().constructCollectionType(List.class, constructMapType);
		try {
			return objectMapper.readValue(str, javaType);
		} catch (IOException e) {
			logger.warn("jackson readArrayMap exception:{}",e);
			return Lists.newArrayList();
		}
		
	}
	public static <K,T> Map<K,T> readMap(String str,Class<K> keyClss,Class<T> valClss){
		MapType constructMapType = objectMapper.getTypeFactory().constructMapType(Map.class, keyClss, valClss);
		try {
			return objectMapper.readValue(str, constructMapType);
		} catch (IOException e) {
			logger.warn("jackson readMap exception:{}",e);
			return null;
		}
	}


	public static <K,T> Map<K,List<T>> readMapArray(String str,Class<K> keyClss,Class<T> valClss){

		JavaType keyType  = objectMapper.getTypeFactory().constructType(keyClss);
		JavaType valType  = objectMapper.getTypeFactory().constructCollectionType(List.class, valClss);
		MapType constructMapType = objectMapper.getTypeFactory().constructMapType(Map.class, keyType,valType);
		try {
			return objectMapper.readValue(str, constructMapType);
		} catch (IOException e) {
			logger.warn("jackson readMapArray exception:{}",e);
			return null;
		}
	}

	public static <T> T read(String str, Class<T> clss) {
		try {
			return objectMapper.readValue(str, clss);
		} catch (IOException e) {
			logger.warn("jackson read exception:{}",e);
			return null;

		}
	}

}