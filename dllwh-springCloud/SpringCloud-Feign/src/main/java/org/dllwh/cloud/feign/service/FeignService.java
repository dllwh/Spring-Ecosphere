package org.dllwh.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EurekaProvideApplication") // 指定调用哪个服务
public interface FeignService {

	@GetMapping("hello")
	String sayHiFromClient();
}
