package org.dllwh;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 使用SpringBoot 的MockMVC 进行模拟测试，向"/hello"发送请求并得到回应
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月31日 下午7:21:01
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
// webEnvironment属性的默认值 是WebEnvironment.MOCK，在这里只是为了展示该配置
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
// 进行mock 相关的自动配置
@AutoConfigureMockMvc
public class MockEnvTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testHello() throws Exception {
		ResultActions ra = mvc.perform(MockMvcRequestBuilders.get(new URI("/hello")));
		MvcResult result = ra.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}