package org.dllwh.druiddatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: SpringBoot + Druid DataSource 实现监控 MySQL 性能
 *
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-02-27 23:02:35
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
public class DruidDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidDataSourceApplication.class, args);
    }
}
