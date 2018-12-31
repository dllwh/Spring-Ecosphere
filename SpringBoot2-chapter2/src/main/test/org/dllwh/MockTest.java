package org.dllwh;

import org.dllwh.service.MainService;
import org.dllwh.service.RemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MockTest {

	@MockBean
	private RemoteService	remoteService;

	@Autowired
	private MainService		mainService;

	@Test
	public void testMainService() {
		// 模拟 removeService 的 call 方法返回 angus
		BDDMockito.given(this.remoteService.call()).willReturn("angus");
		mainService.mainService();
	}
}