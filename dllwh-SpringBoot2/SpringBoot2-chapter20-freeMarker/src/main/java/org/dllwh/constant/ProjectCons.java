package org.dllwh.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统常用变量文件夹
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年3月26日 下午10:22:38
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class ProjectCons {
	/** 项目基础包名称 */
	public static String		BASE_PACKAGE				= "org.dllwh";
	/** Model所在包 */
	public static final String	MODEL_PACKAGE				= BASE_PACKAGE + ".model";
	/** Mapper所在包 */
	public static final String	MAPPER_PACKAGE				= BASE_PACKAGE + ".dao";
	/** Mapper所在包 */
	public static final String	MAPPER_XML_PACKAGE			= MAPPER_PACKAGE+".mapper";
	/** Service所在包 */
	public static final String	SERVICE_PACKAGE				= BASE_PACKAGE + ".service";
	/** ServiceImpl所在包 */
	public static final String	SERVICE_IMPL_PACKAGE		= SERVICE_PACKAGE + ".impl";
	/** Controller所在包 */
	public static final String	CONTROLLER_PACKAGE			= BASE_PACKAGE + ".controller";
	public static String		encoding					= "UTF-8";
	/** java文件路径 */
	public static final String	JAVA_PATH					= "src/main/java";
	/** 资源文件路径 */
	public static final String	RESOURCES_PATH				= "src/main/resources";
	/** 创建者 */
	public static final String	AUTHOR						= "独泪了无痕--duleilewuhen@sina.com";
	public static String		DATE						= new SimpleDateFormat("yyyy-MM-dd HH:mm")
			.format(new Date());
	/** 生成的Controller存放路径 */
	public static final String	PACKAGE_PATH_CONTROLLER		= packageConvertPath(CONTROLLER_PACKAGE);
	/** 生成的Model存放路径 */
	public static final String	PACKAGE_PATH_MODEL			= packageConvertPath(MODEL_PACKAGE);
	/** 生成的Mapper存放路径 */
	public static final String	PACKAGE_PATH_MAPPER			= packageConvertPath(MAPPER_PACKAGE);
	/** 生成的Service存放路径 */
	public static final String	PACKAGE_PATH_SERVICE		= packageConvertPath(SERVICE_PACKAGE);
	/** 生成的ServiceImpl存放路径 */
	public static final String	PACKAGE_PATH_SERVICE_IMPL	= packageConvertPath(SERVICE_IMPL_PACKAGE);

	private static String packageConvertPath(String packageName) {
		return String.format("/%s/",
				packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}
}