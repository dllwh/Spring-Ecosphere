package com.cdeledu.core.configuration;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import com.cdeledu.core.exception.ErrorInfoBuilder;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 全局异常处理配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:28:12
 * @版本: V1.0
 * @since: JDK 1.8
 */
public class ExceptionConfig {
	@Resource
	private ServerProperties serverProperties;
	
	public ErrorInfoBuilder errorInfoBuilder() {
		return new ErrorInfoBuilder(serverProperties);
	}
}