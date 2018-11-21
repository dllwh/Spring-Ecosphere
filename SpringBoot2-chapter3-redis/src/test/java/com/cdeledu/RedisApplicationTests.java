package com.cdeledu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisSpringBootApplication.class)
public class RedisApplicationTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("dllwh", "独泪了无痕");
		String result = stringRedisTemplate.opsForValue().get("dllwh");
		System.out.println("Spring Boot中使用Redis数据库：" + result);
	}
}
