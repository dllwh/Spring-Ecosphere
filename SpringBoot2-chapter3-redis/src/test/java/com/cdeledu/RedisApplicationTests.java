package com.cdeledu;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisSpringBootApplication.class)
public class RedisApplicationTests {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void ValueOperationsTest() {
		redisTemplate.opsForValue().set("dllwh", "代码成就万世基积沙镇海，梦想永在凌云意意气奋发。");
		System.out.println(redisTemplate.opsForValue().get("dllwh"));
	}

	@Test
	public void HashOperationsTest() {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("firstname", "独泪了无痕");
		userMap.put("lastname", "独泪了无痕");
		redisTemplate.opsForHash().putAll("normalMapping", userMap);

		redisTemplate.opsForHash().put("redisHash", "name", "tom");
		redisTemplate.opsForHash().put("redisHash", "age", 26);
		redisTemplate.opsForHash().put("redisHash", "class", "6");
		System.out.println(redisTemplate.opsForHash().entries("redisHash"));
	}

	public void ListOperationsTest() {
		redisTemplate.opsForList().leftPush("list", "java");
		redisTemplate.opsForList().leftPush("list", "python");
		redisTemplate.opsForList().leftPush("list", "c++");
	}

	public void SetOperationsTest() {

	}

	public void ZSetOperationsTest() {

	}
}
