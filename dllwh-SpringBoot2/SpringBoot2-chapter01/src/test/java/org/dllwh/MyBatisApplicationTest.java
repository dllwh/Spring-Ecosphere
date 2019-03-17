package org.dllwh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.dllwh.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyBatisApplicationTest {
	/** ----------------------------------------------------- Fields start */
	@Autowired
	private RoleService roleService;

	/** ----------------------------------------------------- Fields end */

	@Test
	@Transactional
	public void test() throws Exception {
		System.out.println("Spring Boot整合MyBatis ：" + roleService.getRoleList());
	}
}