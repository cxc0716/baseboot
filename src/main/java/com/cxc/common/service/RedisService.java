package com.cxc.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * author:chenxinchao
 * date:2016-09-29 11:31
 * desc:com.cxc.common.service
 */
@Component
public class RedisService {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redis;

	public RedisTemplate getRedis(){
		return redis;
	}

}
