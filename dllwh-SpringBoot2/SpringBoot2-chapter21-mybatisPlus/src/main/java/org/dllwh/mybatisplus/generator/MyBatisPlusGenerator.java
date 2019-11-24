package org.dllwh.mybatisplus.generator;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.mybatisplus.generator.config.CodeGeneratorConfig;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Mybatis-plus 代码生成器
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-24 8:14:50 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class MyBatisPlusGenerator {
	/** 输出文件的路径 */
	private String projectPath;
	/** 包名 */
	private String packageName = "org.dllwh.mybatisplus";
	/** 模块名称 */
	private String moduleName;
	/** 代码生成者 */
	private String author = "独泪了无痕";
	/** JDBC相关配置 */
	private String dbType;
	private String driverName;
	private String url;
	private String userName;

	private String password;

	public MyBatisPlusGenerator(String moduleName, String dbType, String driverName, String url, String userName,
			String password) {
		super();
		this.moduleName = moduleName;
		this.dbType = dbType;
		this.driverName = driverName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public MyBatisPlusGenerator(String projectPath, String packageName, String moduleName, String author, String dbType,
			String driverName, String url, String userName, String password) {
		super();
		this.projectPath = projectPath;
		if (StringUtils.isNotBlank(packageName)) {
			this.packageName = packageName;
		}
		this.moduleName = moduleName;
		if (StringUtils.isNotBlank(author)) {
			this.author = author;
		}
		this.dbType = dbType;
		this.driverName = driverName;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 
	 * @方法描述: 代码生成器
	 * @param tableName 需要生成的表
	 */
	public void codeAutoGenerator(String tableName) {
		// 代码生成器
		AutoGenerator autoGenerator = new AutoGenerator();
		// 全局配置
		autoGenerator.setGlobalConfig(CodeGeneratorConfig.getGlobalConfig(projectPath, author));
		// 数据源配置
		autoGenerator
				.setDataSource(CodeGeneratorConfig.getDataSourceConfig(dbType, driverName, userName, password, url));
		// 策略配置
		autoGenerator.setStrategy(CodeGeneratorConfig.getStrategyConfig(new String[] { tableName }));
		// 包名策略配置
		autoGenerator.setPackageInfo(CodeGeneratorConfig.getPackageConfig(packageName, moduleName));
		// 注入自定义配置
		autoGenerator.setCfg(CodeGeneratorConfig.getInjectionConfig(projectPath, moduleName));
		// 配置模板
		autoGenerator.setTemplate(CodeGeneratorConfig.getTemplateConfig("", "", "", "", "", ""));
		// 模板引擎
		autoGenerator.setTemplateEngine(CodeGeneratorConfig.getTemplateEngine());
		// 执行生成
		autoGenerator.execute();
	}
}
