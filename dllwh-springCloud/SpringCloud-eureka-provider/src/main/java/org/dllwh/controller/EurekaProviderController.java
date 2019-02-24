package org.dllwh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaProviderController {

	@GetMapping("hello")
	public String sayHiFromClient() {
		return "hello word";
	}
}