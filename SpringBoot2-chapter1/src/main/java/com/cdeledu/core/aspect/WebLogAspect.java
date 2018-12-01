package com.cdeledu.core.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: AOP处理HTTP请求
 * 		
 *       <pre>
 * 利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年11月16日 上午11:27:08
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	/**
	 * @方法描述: Controoler切入点
	 */
	@Pointcut("execution(public * com.cdeledu.controller.*.*(..))")
	public void webLog() {
	}
	
	/**
	 * @方法描述: 前置通知，用于拦截记录用户的操作
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		log.debug("=========执行前置通知===============");
		startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		log.warn("URL={}", request.getRequestURL());
		log.warn("Method={}", request.getMethod());
		log.warn("IP={}", request.getRemoteAddr());
		log.warn("CLass.Method={}", joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + "()");
		log.warn("Args={}", Arrays.toString(joinPoint.getArgs()));
	}
	
	/**
	 * @方法描述:配置环绕通知,使用在方法aspect()上注册的切入点
	 */
	@Around("webLog()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("==========开始执行环绕通知===============");
		// 执行方法
		Object result = joinPoint.proceed();
		log.debug("==========结束执行环绕通知===============");
		return result;
	}
	
	/**
	 * @方法描述: 标注该方法体为后置通知,当目标方法执行成功后执行该方法体,使用在方法aspect()上注册的切入点
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		log.debug("=========执行后置返回通知===============");
		// 处理完请求，返回内容
		log.warn("response = {} ", ret);
		log.warn("spend time = {}", (System.currentTimeMillis() - startTime.get()));
	}
	
	/**
	 * @方法描述:后置通知 用于拦截层记录用户的操作
	 */
	@After("webLog()")
	public void doAfter(JoinPoint joinPoint) {
		log.debug("=========执行后置通知===============");
	}
	
	/**
	 * @方法描述: 异常通知 用于拦截记录异常日志
	 */
	@AfterThrowing(pointcut = "webLog()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.debug("=========执行异常通知===============");
	}
}
