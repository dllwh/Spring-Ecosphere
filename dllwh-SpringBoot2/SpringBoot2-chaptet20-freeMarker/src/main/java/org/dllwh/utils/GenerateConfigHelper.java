package org.dllwh.utils;

import org.dllwh.utils.ymlHelper;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

public final class GenerateConfigHelper {

	public static Context getContext() {
		Context context = new Context(ModelType.FLAT);
		context.setId("Mysql");
		context.setTargetRuntime("MyBatis3Simple");
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
		 * 1、ANNOTATEDMAPPER：会生成使用Mapper接口+Annotation的方式创建，不会生成对应的XML
		 * 2、MIXEDMAPPER：使用混合配置，会生成Mapper接口，并适当添加合适的Annotation，但是XML会生成在XML中
		 * 3、XMLMAPPER：会生成Mapper接口，接口完全依赖XML
		 */
		config.setConfigurationType("XMLMAPPER");
		// 在targetPackage的基础上，根据数据库的schema再生成一层package，最终生成的类放在这个package下，默认为false
		config.addProperty(PropertyRegistry.ANY_ENABLE_SUB_PACKAGES, "true");
		return config;
	}

	/**
	 * 1，schema：数据库的schema；
	 * 
	 * 2，catalog：数据库的catalog；
	 * 
	 * 3，alias：为数据表设置的别名，如果设置了alias，那么生成的所有的SELECT
	 * SQL语句中，列名会变成：alias_actualColumnName
	 * 
	 * 4，domainObjectName：生成的domain类的名字，如果不设置，直接使用表名作为domain类的名字；
	 * 可以设置为somepck.domainName，那么会自动把domainName类再放到somepck包里面；
	 * 
	 * 5，enableInsert（默认true）：指定是否生成insert语句；
	 * 
	 * 6，enableSelectByPrimaryKey（默认true）：指定是否生成按照主键查询对象的语句（ 就是getById或get）；
	 * 
	 * 7，enableSelectByExample（默认true）：MyBatis3Simple为false， 指定是否生成动态查询语句；
	 * 
	 * 8，enableUpdateByPrimaryKey（默认true）：指定是否生成按照主键修改对象的语句（即update)；
	 * 
	 * 9，enableDeleteByPrimaryKey（默认true）：指定是否生成按照主键删除对象的语句（即delete）；
	 * 
	 * 10，enableDeleteByExample（默认true）：MyBatis3Simple为false， 指定是否生成动态删除语句；
	 * 
	 * 11，enableCountByExample（默认true）：MyBatis3Simple为false， 指定是否生成动态查询总条数语句（
	 * 用于分页的总条数查询）；
	 * 
	 * 12，enableUpdateByExample（默认true）：MyBatis3Simple为false， 指定是否生成动态修改语句（
	 * 只修改对象中不为空的属性）；
	 * 
	 * 13，modelType：参考context元素的defaultModelType，相当于覆盖；
	 * 
	 * 14，delimitIdentifiers：参考tableName的解释，注意， 默认的delimitIdentifiers是双引号，
	 * 如果类似MYSQL这样的数据库，使用的是`（反引号，
	 * 那么还需要设置context的beginningDelimiter和endingDelimiter属性）
	 * 
	 * 15，delimitAllColumns：设置是否所有生成的SQL中的列名都使用标识符引起来。默认为false，
	 * delimitIdentifiers参考context的属性
	 */
	public static TableConfiguration getTableConfig(Context context, String tableName, String modelName) {
		TableConfiguration tableConfig = new TableConfiguration(context);
		tableConfig.setTableName(tableName);
		tableConfig.setDomainObjectName(modelName);
		// 指定是否只生成domain类
		tableConfig.addProperty("modelOnly", "false");
		return tableConfig;

	}
}