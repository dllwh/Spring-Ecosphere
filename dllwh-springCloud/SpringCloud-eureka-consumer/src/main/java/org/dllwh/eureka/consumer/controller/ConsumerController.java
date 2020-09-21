package org.dllwh.eureka.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 通过 RestTemplate 来实现对 Erauka 服务提供者的调用
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-09-01 22:06:38
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
@RestController
public class ConsumerController {

	private RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		return restTemplate.getForEntity("http://eurekaProviderClientApplication/hello", String.class).getBody();
	}
}
