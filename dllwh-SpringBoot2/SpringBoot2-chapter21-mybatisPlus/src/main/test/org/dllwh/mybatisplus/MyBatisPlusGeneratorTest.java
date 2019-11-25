package org.dllwh.mybatisplus;

import org.dllwh.mybatisplus.generator.MyBatisPlusGenerator;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Mybatis-plus 代码生成器 测试
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-25 12:42:15 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class MyBatisPlusGeneratorTest {
	private static String moduleName = "mybatisplusgenerator";
	private static String dbType = "MYSQL";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://rm-duleilewuhen.mysql.rds.aliyuncs.com:3306/spring?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
	private static String userName = "spring";
	private static String password = "spring";

	public static void main(String[] args) {
		new MyBatisPlusGenerator(null, null, moduleName, null, dbType, driverName, userName, password,url)
				.codeAutoGenerator("crawler_china_area");
	}
}
