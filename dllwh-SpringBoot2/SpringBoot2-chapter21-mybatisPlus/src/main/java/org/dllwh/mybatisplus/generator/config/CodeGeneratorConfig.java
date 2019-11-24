package org.dllwh.mybatisplus.generator.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
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
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Mybatis-plus 代码生成器  相关配置
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-24 11:16:05 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class CodeGeneratorConfig {
	/**
	 * @方法描述: 全局策略配置
	 * @param projectOutPath 生成文件的输出目录
	 * @param author         作者
	 * @return
	 */
	public static GlobalConfig getGlobalConfig(String projectOutPath, String author) {
		if (StringUtils.isBlank(projectOutPath)) {
			projectOutPath = System.getProperty("user.dir") + "/src/main/java";
		}
		GlobalConfig globalConfig = new GlobalConfig();
		// 生成文件的输出目录
		globalConfig.setOutputDir(projectOutPath);
		// 是否覆盖已有文件，默认值：false
		globalConfig.setFileOverride(true);
		// 是否打开输出目录，默认值：true
		globalConfig.setOpen(true);
		globalConfig.setActiveRecord(false);// 开启 activeRecord 模式
		// 是否在xml中添加二级缓存配置，默认值：`false
		globalConfig.setEnableCache(false);
		globalConfig.setAuthor(author);
		// 开启 swagger2 模式，默认值：false
		globalConfig.setSwagger2(true);
		globalConfig.setBaseResultMap(false);// XML ResultMap
		globalConfig.setBaseColumnList(true);// XML columList
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		globalConfig.setEntityName("%sEntity");
		globalConfig.setXmlName("%sMapper");
		globalConfig.setMapperName("%sDao");
		globalConfig.setServiceName("%sService");
		globalConfig.setServiceImplName("%sServiceImpl");
		globalConfig.setControllerName("%sController");
		return globalConfig;
	}

	/**
	 * @方法描述: 数据源配置，通过该配置，指定需要生成代码的具体数据库
	 * @param dbType     数据库类型
	 * @param driverName 驱动名称
	 * @param userName   数据库连接用户名
	 * @param password   数据库连接密码
	 * @param url        驱动连接的URL
	 * @return
	 */
	public static DataSourceConfig getDataSourceConfig(String dbType, String driverName, String userName,
			String password, String url) {
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		// 数据库类型
		dataSourceConfig.setDbType(DbType.getDbType(dbType));
		dataSourceConfig.setDriverName(driverName);
		dataSourceConfig.setUsername(userName);
		dataSourceConfig.setPassword(password);
		dataSourceConfig.setUrl(url);
		return dataSourceConfig;
	}

	/**
	 * @方法描述: 包名策略配置，通过该配置，指定生成代码的包路径
	 * @param packageName 自定义包路径
	 * @param moduleName  父包模块名
	 * @return
	 */
	public static PackageConfig getPackageConfig(String packageName, String moduleName) {
		PackageConfig packageInfo = new PackageConfig();
		packageInfo.setParent(packageName);
		packageInfo.setModuleName(moduleName);
		// 这里是控制器包名，默认 web
		packageInfo.setController("controller");
		packageInfo.setService("service");
		packageInfo.setXml("mapper");
		packageInfo.setMapper("dao");
		packageInfo.setEntity("entity");
		return packageInfo;
	}

	/**
	 * @方法描述: 数据库表配置，通过该配置，可指定需要生成哪些表
	 * @param tableName
	 * @return
	 */
	public static StrategyConfig getStrategyConfig(String... tableName) {
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

		// 自定义继承的Entity类全称，带包名
		strategy.setSuperEntityClass("org.dllwh.mybatisplus.generator.base.BaseEntity");
		// 自定义基础的Entity类，公共字段
		strategy.setSuperEntityColumns(new String[] { "id" });
		// 自定义继承的Mapper类全称，带包名
		strategy.setSuperMapperClass("org.dllwh.mybatisplus.generator.base.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceClass("org.dllwh.mybatisplus.generator.base.BaseService");
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass("org.dllwh.mybatisplus.generator.base.BaseServiceImpl");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("org.dllwh.mybatisplus.generator.base.BaseController");

		strategy.setEntityColumnConstant(false);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBuilderModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBooleanColumnRemoveIsPrefix(true);
		strategy.setRestControllerStyle(true);
		strategy.setControllerMappingHyphenStyle(true);

		return strategy;
	}

	/**
	 * @方法描述: 配置模板，可自定义代码生成的模板，实现个性化操作。
	 * @param entity      Java 实体类模板
	 * @param service     Service 类模板
	 * @param serviceImpl ServiceImpl 实现类模板
	 * @param mapper      mapper 模板
	 * @param xml         mapper xml 模板
	 * @param controller  controller 控制器模板
	 * @return
	 */
	public static TemplateConfig getTemplateConfig(String entity, String service, String serviceImpl, String mapper,
			String xml, String controller) {
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
		// 关闭默认 xml 生成，调整生成 至 根目录
		templateConfig.setXml(null);
		return templateConfig;
	}

	/**
	 * @方法描述: 注入自定义配置，通过该配置，可注入自定义参数等操作以实现个性化操作
	 * @param projectOutPath 输出文件的路径
	 * @param moduleName
	 * @return
	 */
	public static InjectionConfig getInjectionConfig(String projectOutPath, String moduleName) {
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
				return projectOutPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName()
						+ "Mapper" + StringPool.DOT_XML;
			}
		});
		// cfg.setFileOutConfigList(focList);
		return cfg;

	}

	/**
	 * @方法描述: 配置模板引擎
	 * @return
	 */
	public static AbstractTemplateEngine getTemplateEngine() {
		return new FreemarkerTemplateEngine();
	}
}
