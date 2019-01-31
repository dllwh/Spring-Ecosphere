package org.dllwh;

import java.util.List;
import java.util.Set;

import org.dllwh.entity.ChinaArea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MongoDBApplicationTest {

	@Autowired
	private MongoTemplate	mongoTemplate;
	@Autowired
	private MongoOperations	mongoOperations;

	private String			tableName	= "sys_user";

	/**
	 * @方法描述 : 查看数据库名
	 */
	public void getDatabaseNames() {
		String dbName = mongoTemplate.getDb().getName();
		log.info(dbName);
	}

	/**
	 * @方法描述 : 获取所有的Collection列表
	 * @throws Exception
	 */
	public void getCollectionNames() throws Exception {
		Set<String> result = mongoTemplate.getCollectionNames();
		System.out.println("Spring Boot中使用MongoDB数据库 :" + result);
	}

	/**
	 * @方法描述 : 创建集合
	 */
	public void createCollection() {
		if (!mongoTemplate.collectionExists(tableName)) {
			mongoTemplate.createCollection(tableName);
		}
	}

	@Test
	public void findList() {
		Query query = new Query();
		query.skip(100);
		query.limit(10);
		List<ChinaArea> userList = mongoTemplate.find(query, ChinaArea.class);
		for (ChinaArea chinaArea : userList) {
			log.info(chinaArea.toString());
		}
	}
}