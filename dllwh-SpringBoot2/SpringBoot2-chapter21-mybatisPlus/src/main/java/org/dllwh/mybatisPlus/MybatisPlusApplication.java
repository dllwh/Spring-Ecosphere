package org.dllwh.mybatisPlus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 项目启动类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 2:18:26 AM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableSwagger2 // 开启 swagger2 功能
@MapperScan("org.dllwh.mybatisPlus.mapper")
public class MybatisPlusApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusApplication.class, args);
	}
}
