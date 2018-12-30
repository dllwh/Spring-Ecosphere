package com.cdeledu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdeledu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JdbcTemplateApplictionTest {
	@Autowired
	private UserService userSerivce;

	@Test
	public void test() throws Exception {
		System.out.println("使用JdbcTemplate操作数据库:" + userSerivce.getUserCount());
	}
}