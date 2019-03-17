package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.social.config.annotation.EnableSocial;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月25日 上午9:47:29
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableSocial // 用来启用 Spring Social 的相关功能
public class SocialApplication {
	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}
}
