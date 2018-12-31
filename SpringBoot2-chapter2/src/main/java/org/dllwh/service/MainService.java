package org.dllwh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

	@Autowired
	private RemoteService remoteService;

	public void mainService() {
		System.out.println("这是需要测试的业务方法");
		String result = remoteService.call();
		System.out.println("调用结果：" + result);
	}
}