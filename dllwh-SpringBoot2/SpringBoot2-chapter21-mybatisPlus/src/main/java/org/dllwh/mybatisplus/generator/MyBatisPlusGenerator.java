package org.dllwh.mybatisplus.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

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
	private String projectPath = System.getProperty("user.dir") + "/src/main/java";
	private String packageName = "org.dllwh.mybatisplus";
	private String moduleName;
	private String author = "独泪了无痕";
	private String dbType;
	private String driverName;
	private String userName;
	private String password;
	private String url;

	/**
	 * @param moduleName 模块名称
	 * @param dbType     数据库类型
	 * @param driverName 驱动名称
	 * @param userName   数据库连接用户名
	 * @param password   数据库连接密码
	 * @param url        驱动连接的URL
	 */
	public MyBatisPlusGenerator(String moduleName, String dbType, String driverName, String userName, String password,
			String url) {
		super();
		this.moduleName = moduleName;
		this.dbType = dbType;
		this.driverName = driverName;
		this.userName = userName;
		this.password = password;
		this.url = url;
	}

	/**
	 * @param projectPath 输出文件的路径
	 * @param packageName 包名
	 * @param moduleName  模块名称
	 * @param author      代码生成者
	 * @param dbType      数据库类型
	 * @param driverName  驱动名称
	 * @param userName    数据库连接用户名
	 * @param password    数据库连接密码
	 * @param url         驱动连接的URL
	 */
	public MyBatisPlusGenerator(String projectPath, String packageName, String moduleName, String author, String dbType,
			String driverName, String userName, String password, String url) {
		super();
		if (StringUtils.isNotBlank(projectPath)) {
			this.projectPath = projectPath;
		}
		if (StringUtils.isNotBlank(packageName)) {
			this.packageName = packageName;
		}
		this.moduleName = moduleName;
		if (StringUtils.isNotBlank(author)) {
			this.author = author;
		}
		this.dbType = dbType;
		this.driverName = driverName;
		this.userName = userName;
		this.password = password;
		this.url = url;
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
		getGlobalConfig(autoGenerator);
		// 数据源配置
		getDataSourceConfig(autoGenerator);
		// 策略配置
		getStrategyConfig(autoGenerator, new String[] { tableName });
		// 包名策略配置
		getPackageConfig(autoGenerator);
		// 注入自定义配置
		getInjectionConfig(autoGenerator);
		// 配置模板
		getTemplateConfig(autoGenerator, "", "", "", "", "", "");
		// 选择 freemarker 模板引擎，默认 Veloctiy，如果用的是Veloctiy模板引擎就不需要这句代码
		autoGenerator.setTemplateEngine(getTemplateEngine());
		// 执行生成
		autoGenerator.execute();
	}

	/**
	 * @方法描述: 全局策略配置
	 * @param autoGenerator
	 * @return
	 */
	private void getGlobalConfig(AutoGenerator autoGenerator) {
		GlobalConfig globalConfig = new GlobalConfig();
		// 生成文件的输出目录
		globalConfig.setOutputDir(projectPath);
		// 是否覆盖已有文件，默认值：false
		globalConfig.setFileOverride(true);
		// 是否打开输出目录，默认值：true
		globalConfig.setOpen(false);
		globalConfig.setActiveRecord(false);// 开启 activeRecord 模式
		// 是否在xml中添加二级缓存配置，默认值：`false
		globalConfig.setEnableCache(false);
		globalConfig.setAuthor(author);
		// 开启 swagger2 模式，默认值：false
		globalConfig.setSwagger2(true);
		// XML ResultMap
		globalConfig.setBaseResultMap(false);
		// XML columList
		globalConfig.setBaseColumnList(true);
		// 主键ID类型
		globalConfig.setIdType(IdType.AUTO);
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		globalConfig.setEntityName("%sEntity");
		globalConfig.setXmlName("%sMapper");
		globalConfig.setMapperName("%sDao");
		globalConfig.setServiceName("%sService");
		globalConfig.setServiceImplName("%sServiceImpl");
		globalConfig.setControllerName("%sController");
		autoGenerator.setGlobalConfig(globalConfig);
	}

	/**
	 * @方法描述: 数据源配置，通过该配置，指定需要生成代码的具体数据库
	 * @param autoGenerator
	 * @return
	 */
	private void getDataSourceConfig(AutoGenerator autoGenerator) {
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		// 数据库类型
		dataSourceConfig.setDbType(DbType.getDbType(dbType));
		dataSourceConfig.setDriverName(driverName);
		dataSourceConfig.setUsername(userName);
		dataSourceConfig.setPassword(password);
		dataSourceConfig.setUrl(url);
		autoGenerator.setDataSource(dataSourceConfig);
	}

	/**
	 * @方法描述: 包名策略配置，通过该配置，指定生成代码的包路径
	 * @param autoGenerator
	 * @param packageName   自定义包路径
	 * @param moduleName    父包模块名
	 * @return
	 */
	private void getPackageConfig(AutoGenerator autoGenerator) {
		PackageConfig packageInfo = new PackageConfig();
		packageInfo.setParent(packageName);
		packageInfo.setModuleName(moduleName);
		// 这里是控制器包名，默认 web
		packageInfo.setController("controller");
		packageInfo.setService("service");
		packageInfo.setXml("mapper");
		packageInfo.setMapper("dao");
		packageInfo.setEntity("entity");
		autoGenerator.setPackageInfo(packageInfo);
	}

	/**
	 * @方法描述: 数据库表配置，通过该配置，可指定需要生成哪些表
	 * @param autoGenerator
	 * @param tableName
	 * @return
	 */
	private void getStrategyConfig(AutoGenerator autoGenerator, String... tableName) {
		/**
		 * 自定义需要填充的字段
		 */
		List<TableFill> tableFillList = new ArrayList<TableFill>();
		// 每张表都有一个创建时间、修改时间,而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改,修改时，修改时间会修改，使用公共字段填充功能，就可以实现，自动按场景更新了。
		TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
		TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
		tableFillList.add(createField);
		tableFillList.add(modifiedField);

		/**
		 * 策略配置
		 */
		StrategyConfig strategy = new StrategyConfig();
		// 表填充字段
		strategy.setTableFillList(tableFillList);
		// 全局大写命名
		strategy.setCapitalMode(true);
		// 表前缀
		// strategy.setTablePrefix("");
		// 表名生成策略，下划线到驼峰的命名方式
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 需要生成的表
		strategy.setInclude(tableName);
		// 排除生成的表
		// strategy.setExclude(new String[]{});

	

		strategy.setEntityColumnConstant(false);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBuilderModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBooleanColumnRemoveIsPrefix(true);
		strategy.setRestControllerStyle(true);
		strategy.setControllerMappingHyphenStyle(true);

		autoGenerator.setStrategy(strategy);
	}

	/**
	 * @方法描述: 配置模板，可自定义代码生成的模板，实现个性化操作。
	 * @param autoGenerator
	 * @param entity        Java 实体类模板
	 * @param service       Service 类模板
	 * @param serviceImpl   ServiceImpl 实现类模板
	 * @param mapper        mapper 模板
	 * @param xml           mapper xml 模板
	 * @param controller    controller 控制器模板
	 * @return
	 */
	public static void getTemplateConfig(AutoGenerator autoGenerator, String entity, String service, String serviceImpl,
			String mapper, String xml, String controller) {
		TemplateConfig templateConfig = new TemplateConfig();
		if (StringUtils.isNotEmpty(entity)) {
			templateConfig.setEntity(entity);
		}
		if (StringUtils.isNotEmpty(service)) {
			templateConfig.setService(service);
		}
		if (StringUtils.isNotEmpty(serviceImpl)) {
			templateConfig.setServiceImpl(serviceImpl);
		}
		if (StringUtils.isNotEmpty(mapper)) {
			templateConfig.setMapper(mapper);
		}
		if (StringUtils.isNotEmpty(xml)) {
			templateConfig.setXml(xml);
		}
		if (StringUtils.isNotEmpty(controller)) {
			templateConfig.setController(controller);
		}
		autoGenerator.setTemplate(templateConfig);
	}

	/**
	 * @方法描述: 注入自定义配置，通过该配置，可注入自定义参数等操作以实现个性化操作
	 * @param autoGenerator
	 * @param projectOutPath 输出文件的路径
	 * @param moduleName
	 * @return
	 */
	private void getInjectionConfig(AutoGenerator autoGenerator) {
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		/**
		 * 自定义输出配置
		 */
		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName()
						+ "Mapper" + StringPool.DOT_XML;
			}
		});
		// cfg.setFileOutConfigList(focList);
		autoGenerator.setCfg(cfg);
	}

	/**
	 * @方法描述: 配置模板引擎
	 * @return
	 */
	public static AbstractTemplateEngine getTemplateEngine() {
		return new FreemarkerTemplateEngine();
	}

}
