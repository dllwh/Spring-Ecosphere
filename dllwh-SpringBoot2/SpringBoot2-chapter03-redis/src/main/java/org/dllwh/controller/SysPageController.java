package org.dllwh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "syspage")
public class SysPageController {
	@RequestMapping("{module}/{function}/{url}")
	public String page(@PathVariable("module") String module, @PathVariable("function") String function,
			@PathVariable("url") String url) {
		return module + "/" + function + "/" + url;
	}

	@RequestMapping("{module}/{url}")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
		return module + "/" + url;
	}

	@RequestMapping("{url}")
	public String page(@PathVariable("url") String url) {
		return url;
	}
}