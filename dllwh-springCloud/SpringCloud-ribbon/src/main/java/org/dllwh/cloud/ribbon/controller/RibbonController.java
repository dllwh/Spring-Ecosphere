package org.dllwh.cloud.ribbon.controller;

import org.dllwh.cloud.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ribbon-consumer")
public class RibbonController {
	@Autowired
	RibbonService ribbonService;

	@GetMapping({"","/"})
	public String hi() {
		return ribbonService.sayHelloFromRibbon();
	}
}