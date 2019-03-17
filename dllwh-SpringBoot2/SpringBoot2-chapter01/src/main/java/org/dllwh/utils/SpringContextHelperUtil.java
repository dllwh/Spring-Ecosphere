package org.dllwh.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取SpringBoot项目的applicationContext对象,可在任何代码任何地方任何时候取出ApplicaitonContext
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月17日 下午11:14:15
 * @版本: V1.0
 * @since: JDK 1.8
 */

@Component
public class SpringContextHelperUtil implements ApplicationContextAware {
	/** 上下文对象实例 */
	private static ApplicationContext applicationContext;
	
	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHelperUtil.applicationContext = applicationContext;
	}
	
	/**
	 * 
	 * @方法描述:取得存储在静态变量中的ApplicationContext.
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	/**
	 * 
	 * @方法描述:从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeans(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * @方法描述:从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.如果有多个Bean符合Class,
	 *                                       取出第一个.
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		Map<String, T> beanMap = applicationContext.getBeansOfType(clazz);
		Collection<T> valueSet = beanMap.values();
		List<T> valueList = new ArrayList<T>(valueSet);
		return valueList.get(0);
	}
	
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在spring.xml中定义applicationContext");
		}
	}
	
	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		checkApplicationContext();
		return applicationContext.getBean(name);
	}
	
	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(String name, Class requiredType) throws BeansException {
		checkApplicationContext();
		return applicationContext.getBean(name, requiredType);
	}
	
	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		checkApplicationContext();
		return applicationContext.containsBean(name);
	}
	
	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		checkApplicationContext();
		return applicationContext.isSingleton(name);
	}
	
	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	@SuppressWarnings("rawtypes")
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		checkApplicationContext();
		return applicationContext.getType(name);
	}
	
	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		checkApplicationContext();
		return applicationContext.getAliases(name);
	}
}