package com.cxc.service;

import com.cxc.BaseTest;
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

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void set(){
//		template.opsForValue().set("loop-forever", "cxc");
//		redisConnectionFactory.getConnection().set("loog-forever".getBytes(),"cxc".getBytes());
		redisTemplate.opsForValue().set("loog-forever","xxx22");
		System.out.println("ok");
	}

}
