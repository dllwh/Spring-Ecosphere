package com.cdeledu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 全局页面跳转
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月13日 下午11:33:09
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
public class IndexController {
	/**
	 * @方法描述 : 页面跳转
	 * @param url
	 * @return
	 */
	@RequestMapping("{url}.shtml")
	public String firstPage(@PathVariable("url") String url) {
		return url;
	}

	/**
	 * @方法描述 : 页面跳转(二级目录)
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{url}.shtml")
	public String secondPage(@PathVariable("module") String module,
			@PathVariable("url") String url) {
		return module + "/" + url;
	}

	/**
	 * @方法描述 : 页面跳转(三级目录)
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{project}/{module}/{url}.shtml")
	public String thirdPeage(@PathVariable("project") String project,
			@PathVariable("module") String module, @PathVariable("url") String url) {
		return project + "/" + module + "/" + url;
	}
}
