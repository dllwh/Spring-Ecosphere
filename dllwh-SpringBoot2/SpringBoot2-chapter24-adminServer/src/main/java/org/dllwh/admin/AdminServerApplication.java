package org.dllwh.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 启动类
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-10-11 21:19:18
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableAdminServer
@Slf4j
public class AdminServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminServerApplication.class, args);
		log.info("AdminServerApplication启动!");
	}
}
