package com.cdeledu;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MongoSpringBootApplication.class)
public class MongoDBApplicationTest {
	/** ----------------------------------------------------- Fields start */

	@Autowired
	private MongoTemplate mongoTemplate;

	/** ----------------------------------------------------- Fields end */
	@Test
	public void test() throws Exception {
		Set<String> result = mongoTemplate.getCollectionNames();
		System.out.println("Spring Boot中使用MongoDB数据库 :" + result);
	}
}
