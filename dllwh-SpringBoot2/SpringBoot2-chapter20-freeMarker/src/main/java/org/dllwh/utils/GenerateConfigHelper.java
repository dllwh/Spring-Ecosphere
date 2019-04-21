package org.dllwh.utils;

import org.dllwh.utils.ymlHelper;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 配置生成器
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年3月28日 下午9:46:38
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class GenerateConfigHelper {
	/**
	 * @方法描述:生成一组对象的环境
	 */
	public static Context getContext() {
		Context context = new Context(ModelType.FLAT);
		// 上下文id，用于在生成错误时提示
		context.setId("Mysql");
		context.setTargetRuntime("MyBatis3Simple");
		// 自动识别数据库关键字，默认false
		context.addProperty(PropertyRegistry.CONTEXT_AUTO_DELIMIT_KEYWORDS, "false");
		// 生成的Java文件的编码
		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");
		// 格式化java代码
		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FORMATTER,
				"org.mybatis.generator.api.dom.DefaultJavaFormatter");
		// 格式化XML代码
		context.addProperty(PropertyRegistry.CONTEXT_XML_FORMATTER,
				"org.mybatis.generator.api.dom.DefaultXmlFormatter");
		// beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，比如ORACLE就是双引号，MYSQL默认是`反引号；
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
		return context;
	}

	public static PluginConfiguration getPluginConfig() {
		PluginConfiguration config = new PluginConfiguration();
		return config;
	}

	/**
	 * @方法描述: 去掉注解
	 */
	public static CommentGeneratorConfiguration getCommentGeneratorConfig() {
		CommentGeneratorConfiguration config = new CommentGeneratorConfiguration();
		config.addProperty("suppressDate", "true");
		// 是否去除自动生成的注释 true：是 ： false:否
		config.addProperty("suppressAllComments", "true");
		return config;
	}

	/**
	 * @方法描述:数据库连接的信息：驱动类、连接地址、用户名、密码
	 */
	public JDBCConnectionConfiguration getJdbcConnection() {
		JDBCConnectionConfiguration config = new JDBCConnectionConfiguration();
		config.setConnectionURL(ymlHelper.getConfig("spring.datasource.url"));
		config.setDriverClass(ymlHelper.getConfig("spring.datasource.driver-class-name"));
		config.setPassword(ymlHelper.getConfig("spring.datasource.password"));
		config.setUserId(ymlHelper.getConfig("spring.datasource.username"));
		return config;
	}

	/**
	 * @方法描述:生成Model类配置
	 * @param targetPackage
	 *            生成的类要放的包，真实的包受enableSubPackages属性控制
	 * @param targetProject
	 *            自动生成代码的位置
	 */
	public static JavaModelGeneratorConfiguration getJavaModelGenerator(String targetPackage,
			String targetProject) {
		JavaModelGeneratorConfiguration config = new JavaModelGeneratorConfiguration();
		config.setTargetPackage(targetPackage);
		config.setTargetProject(targetProject);
		// 是否让schema作为包的后缀
		config.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
		// 从数据库返回的值被清理前后的空格
		config.addProperty(PropertyRegistry.MODEL_GENERATOR_TRIM_STRINGS, "true");

		return config;
	}

	/**
	 * @方法描述:生成SQL map的XML文件生成器
	 * @param targetPackage
	 *            生成的类要放的包，真实的包受enableSubPackages属性控制
	 * @param targetProject
	 *            自动生成代码的位置
	 * @return
	 */
	public static SqlMapGeneratorConfiguration getSqlMapGenerator(String targetPackage,
			String targetProject) {
		SqlMapGeneratorConfiguration config = new SqlMapGeneratorConfiguration();
		config.setTargetPackage(targetPackage);
		config.setTargetProject(targetProject);
		// 在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false
		config.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
		return config;
	}

	/**
	 * @方法描述:生成Dao类配置
	 * @param targetPackage
	 * @param targetProject
	 *            自动生成代码的位置
	 */
	public static JavaClientGeneratorConfiguration getJavaClientGenerator(String targetPackage,
			String targetProject) {
		JavaClientGeneratorConfiguration config = new JavaClientGeneratorConfiguration();
		config.setTargetPackage(targetPackage);
		config.setTargetProject(targetProject);
		/**
		 * 选择怎么生成mapper接口
		 * 1、ANNOTATEDMAPPER：生成Java Model 和基于注解的Mapper对象
		 * 2、MIXEDMAPPER：生成基于注解的Java Model 和相应的Mapper对象
		 * 3、XMLMAPPER：生成SQLMap XML文件和独立的Mapper接口
		 */
		config.setConfigurationType("XMLMAPPER");
		// 在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false
		config.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
		return config;
	}

	
	public static TableConfiguration getTableConfig(Context context, String tableName, String modelName) {
		TableConfiguration tableConfig = new TableConfiguration(context);
		tableConfig.setTableName(tableName);
		// 生成的domain类的名字，如果不设置，直接使用表名作为domain类的名字
		tableConfig.setDomainObjectName(modelName);
		GeneratedKey generatedKey = new GeneratedKey("id", "MySql", true, "");
		tableConfig.setGeneratedKey(generatedKey);
		// 指定是否生成insert语句,默认true
		tableConfig.setInsertStatementEnabled(true);
		// 指定是否生成动态删除语句,MyBatis3Simple默认false
		tableConfig.setDeleteByExampleStatementEnabled(false);
		// 指定是否生成按照主键删除对象的语句（即delete）,MyBatis3Simple默认false
		tableConfig.setDeleteByPrimaryKeyStatementEnabled(true);
		tableConfig.setUpdateByExampleStatementEnabled(true);
		// 指定是否生成按照主键修改对象的语句（即update)
		tableConfig.setUpdateByPrimaryKeyStatementEnabled(true);
		// 指定是否生成动态查询语句,MyBatis3Simple默认为false
		tableConfig.setSelectByExampleStatementEnabled(true);
		// 指定是否生成按照主键查询对象的语句（ 就是getById或get）,默认true
		tableConfig.setSelectByPrimaryKeyStatementEnabled(true);
		// 指定是否生成动态查询总条数语句（用于分页的总条数查询）,MyBatis3Simple默认为false
		tableConfig.setCountByExampleStatementEnabled(true);
		// 默认的delimitIdentifiers是双引号
		tableConfig.setDelimitIdentifiers(true);
		// 设置是否所有生成的SQL中的列名都使用标识符引起来。默认为false
		tableConfig.setAllColumnDelimitingEnabled(false);
		// 指定是否只生成domain类
		tableConfig.addProperty("modelOnly", "false");
		return tableConfig;

	}
}