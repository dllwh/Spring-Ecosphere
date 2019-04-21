package org.dllwh.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dllwh.constant.ProjectCons;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.google.common.base.CaseFormat;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发时间
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年3月28日 下午9:50:40
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public final class GeneratorSqlHelper {
	/**
	 * @方法描述:通过数据表名称生成代码
	 * @param tableName
	 *            数据表名称
	 * @throws IOException
	 */
	public static void genCode(String tableName) {
		genController(tableName);
		genModelAndMapper(tableName);
		genServiceInterface(tableName);
		genServiceImpl(tableName);
	}

	/**
	 * @方法描述:生成模块控制类
	 * @param modelName
	 * @throws IOException
	 */
	public static void genController(String tableName) {
		try {
			String upperTableName = tableNameConvertUpperCamel(tableName);
			String lowerTableName = tableNameConvertLowerCamel(tableName);
			Map<String, Object> data = new HashMap<>();
			data.put("date", ProjectCons.DATE);
			data.put("author", ProjectCons.AUTHOR);
			data.put("baseRequestMapping", lowerTableName);
			data.put("basePackage", ProjectCons.BASE_PACKAGE);
			data.put("basePackageController", ProjectCons.CONTROLLER_PACKAGE);
			data.put("basePackageService", ProjectCons.SERVICE_PACKAGE);
			data.put("basePackageModel", ProjectCons.MODEL_PACKAGE);
			data.put("tableNameUpper", upperTableName);
			data.put("tableNameLower", lowerTableName);

			File file = new File(ProjectCons.JAVA_PATH + ProjectCons.PACKAGE_PATH_CONTROLLER + upperTableName
					+ "Controller.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Configuration cfg = getConfiguration();
			cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));
			log.info(tableName + "Controller.java 生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法描述:生成模块数据实体类
	 * @param tableName
	 */
	public static void genModelAndMapper(String tableName) {
		try {
			String modelName = tableNameConvertUpperCamel(tableName);

			Context context = GenerateConfigHelper.getContext();

			JDBCConnectionConfiguration jdbcConnectionConfig = new GenerateConfigHelper().getJdbcConnection();
			context.setJdbcConnectionConfiguration(jdbcConnectionConfig);

			JavaModelGeneratorConfiguration javaModelGeneratorConfig = GenerateConfigHelper
					.getJavaModelGenerator(ProjectCons.MODEL_PACKAGE, ProjectCons.JAVA_PATH);
			context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfig);

			SqlMapGeneratorConfiguration sqlMapGeneratorConfig = GenerateConfigHelper
					.getSqlMapGenerator(ProjectCons.MAPPER_XML_PACKAGE, ProjectCons.JAVA_PATH);
			context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfig);

			JavaClientGeneratorConfiguration javaClientGeneratorConfig = GenerateConfigHelper
					.getJavaClientGenerator(ProjectCons.MAPPER_PACKAGE, ProjectCons.JAVA_PATH);
			context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);

			CommentGeneratorConfiguration commentGeneratorConfig = GenerateConfigHelper
					.getCommentGeneratorConfig();
			context.setCommentGeneratorConfiguration(commentGeneratorConfig);

			TableConfiguration tableConfig = GenerateConfigHelper.getTableConfig(context, tableName,
					modelName);
			context.addTableConfiguration(tableConfig);

			org.mybatis.generator.config.Configuration config = new org.mybatis.generator.config.Configuration();
			config.addContext(context);
			config.validate();

			boolean overwrite = true;
			DefaultShellCallback shellCallback = new DefaultShellCallback(overwrite);
			List<String> warnings = new ArrayList<>();
			MyBatisGenerator generator = new MyBatisGenerator(config, shellCallback, warnings);
			generator.generate(null);

			if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
				throw new RuntimeException("生成Model和Mapper失败：" + warnings);
			}
			// 生成Model类存放位置
			log.info(modelName + ".java 生成成功");
			// 生成映射文件存放位置
			log.info(modelName + "Mapper.java 生成成功");
			// 生成Dao类存放位置
			log.info(modelName + "Mapper.xml 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Model和Mapper失败", e);
		}
	}

	/**
	 * @方法描述:生成模块业务服务接口
	 * @param tableName
	 * @throws IOException
	 */
	public static void genServiceInterface(String tableName) {
		try {
			String upperTableName = tableNameConvertUpperCamel(tableName);
			String lowerTableName = tableNameConvertLowerCamel(tableName);
			Configuration cfg = getConfiguration();
			// 模板所需要的参数
			Map<String, Object> data = new HashMap<>();
			data.put("date", ProjectCons.DATE);
			data.put("author", ProjectCons.AUTHOR);
			data.put("basePackage", ProjectCons.BASE_PACKAGE);
			data.put("basePackageService", ProjectCons.SERVICE_PACKAGE);
			data.put("tableNameUpper", upperTableName);
			data.put("tableNameLower", lowerTableName);
			data.put("basePackageModel", ProjectCons.MODEL_PACKAGE);

			File file = new File(ProjectCons.JAVA_PATH + ProjectCons.PACKAGE_PATH_SERVICE + upperTableName
					+ "Service.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
			log.info(tableName + "Service.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Service失败", e);
		}
	}

	/**
	 * @方法描述:生成模块服务业务接口实现类
	 * @param tableName
	 */
	public static void genServiceImpl(String tableName) {
		try {
			String upperTableName = tableNameConvertUpperCamel(tableName);
			String lowerTableName = tableNameConvertLowerCamel(tableName);

			Configuration cfg = getConfiguration();
			// 模板所需要的参数
			Map<String, Object> data = new HashMap<>();
			data.put("date", ProjectCons.DATE);
			data.put("author", ProjectCons.AUTHOR);
			data.put("basePackage", ProjectCons.BASE_PACKAGE);
			data.put("basePackageService", ProjectCons.SERVICE_PACKAGE);
			data.put("basePackageServiceImpl", ProjectCons.SERVICE_IMPL_PACKAGE);
			data.put("tableNameUpper", upperTableName);
			data.put("tableNameLower", lowerTableName);
			data.put("basePackageModel", ProjectCons.MODEL_PACKAGE);
			data.put("basePackageDao", ProjectCons.MAPPER_PACKAGE);
			File file = new File(ProjectCons.JAVA_PATH + ProjectCons.PACKAGE_PATH_SERVICE_IMPL
					+ upperTableName + "ServiceImpl.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("serviceImpl.ftl").process(data, new FileWriter(file));
			log.info(tableName + "ServiceImpl.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成ServiceImpl失败", e);
		}
	}

	/**
	 * @方法描述:mysql中表明使用“_”或者“-”分隔，自动生成的代码文件名中会去掉，并且其后面的字母会升级为大写。
	 */
	private static String tableNameConvertUpperCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
	}

	private static String tableNameConvertLowerCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
	}

	private static Configuration getConfiguration() throws IOException {
		Configuration cfg = new Configuration(Configuration.getVersion());
		cfg.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
		cfg.setDefaultEncoding(ProjectCons.encoding);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}
}