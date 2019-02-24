package org.dllwh.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
	@Autowired
	private RestTemplate restTemplate;

	public String sayHelloFromRibbon() {
		return restTemplate.getForObject("http://EurekaProvideApplication/hello", String.class);
	}
}