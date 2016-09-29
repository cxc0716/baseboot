package com.cxc.service;

import com.cxc.BaseTest;
import com.cxc.common.service.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * author:chenxinchao
 * date:2016-09-28 15:10
 * desc:com.cxc.service
 */
public class RedisServiceTest extends BaseTest {

//	@Autowired
//	private RedisTemplate redisTemplate;

	@Autowired
	private RedisService redisService;

	@Test
	public void set(){
//		template.opsForValue().set("loop-forever", "cxc");
//		redisConnectionFactory.getConnection().set("loog-forever".getBytes(),"cxc".getBytes());
		redisService.getRedis().opsForValue().set("loog-forever","ffff");
		System.out.println("set finish");
		Object o = redisService.getRedis().opsForValue().get("loog-forever");
		System.out.println("result-->"+o);

		System.out.println("ok");
	}

}
