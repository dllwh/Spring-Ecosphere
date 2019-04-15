package org.dllwh.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户认证服务器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月8日 下午10:48:24
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableFeignClients
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}