package org.dllwh.aop.component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dllwh.aop.model.WebLog;
import org.dllwh.aop.util.IpUtilHelper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 统一日志处理切面
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-08-20 01:00:35
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
@Aspect // 使之成为切面类
@Component // 把切面类加入到IOC容器中
@Order(1)
@Slf4j
public class WebLogAspect {
	/** 进入方法时间戳 */
	private Long startTime;
	/** 方法结束时间戳(计时) */
	private Long endTime;

	/**
	 * @方法描述: 定义切入点，切入点为 org.dllwh.aop.controller 中的所有函数
	 */
	// @Pointcut("execution(public * org.dllwh.aop.controller..*.*(..))")
	@Pointcut("execution(public * org.dllwh.aop.controller.AopController.*(..))")
	public void webLog() {
	}

	/**
	 * @方法描述: 前置通知，用于拦截记录用户的操作
	 */
	@Before(value = "webLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("=========执行前置通知==================");
		}

	}

	/**
	 * @方法描述: 环绕通知,决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 执行方法
		Object result = joinPoint.proceed();
		WebLog webLog = getWebLog(joinPoint, null);
		if (result != null) {
			webLog.setResult(result);
		}
		if (log.isDebugEnabled()) {
			log.debug("=========执行环绕通知==================");
		}
		log.info("{}", JSONUtil.parse(webLog));
		return result;
	}

	/**
	 * @方法描述: 标注该方法体为后置通知,当目标方法执行成功后执行该方法体，不论是正常返回还是异常退出
	 *
	 * @param joinPoint
	 */
	@After("webLog()")
	public void doAfter(JoinPoint joinPoint) {
		if (log.isDebugEnabled()) {
			log.debug("=========执行后置通知==================");
		}
	}

	/**
	 * @方法描述: 标注该方法体为后置通知,当目标方法执行成功后执行该方法体,使用在方法aspect()上注册的切入点
	 *
	 * @param joinPoint
	 * @param returnObject 返回值的信息
	 */
	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
		// endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("=========执行后置返回通知===============");
		}
	}

	/**
	 * @方法描述: 标注该方法体为异常通知，当目标方法抛出异常返回后，执行该方法体,用于拦截记录异常日志
	 *
	 * @param joinPoint
	 * @param exception 为Throwable类型将匹配任何异常
	 */
	@AfterThrowing(pointcut = "webLog()", throwing = "exception")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		if (log.isDebugEnabled()) {
			log.debug("=========执行异常通知===============");
		}
		WebLog webLog = getWebLog(joinPoint, exception);
		log.info("{}", JSONUtil.parse(webLog));
		
	}

	/**
	 * @方法描述: 获取日志信息
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private WebLog getWebLog(JoinPoint joinPoint, Throwable exception) {
		endTime = System.currentTimeMillis();
		// 记录请求信息
		WebLog webLog = new WebLog();
		// 获取当前请求对象
		RequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		// 获取目标方法的签名
		Signature signature = joinPoint.getSignature();
		// 获取目标方法的参数
		Object[] getArgs = joinPoint.getArgs();

		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(ApiOperation.class)) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			webLog.setDescription(apiOperation.value());
		}

		String urlStr = request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setIpAddress(IpUtilHelper.getClientIP(request));
		// 请求方式
		webLog.setMethod(request.getMethod());
		// 请求参数
		webLog.setParameter(getParameter(method, getArgs));

		// 执行时长(毫秒)
		webLog.setSpendTime(endTime - startTime);
		webLog.setStartTime(startTime);
		webLog.setUri(request.getRequestURI());
		// 请求Url
		webLog.setUrl(request.getRequestURL().toString());
		// 获取执行方法的类的名称（包名加类名）
		webLog.setClassName(joinPoint.getTarget().getClass().getName());
		// 获取方法名
		webLog.setMethodName(signature.getName());

		if (exception != null) {
			webLog.setLogType(-1);
			webLog.setExceptionDetail(exception.getMessage());
		}
		return webLog;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 * 
	 * @param method
	 * @param args
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			// 将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			// 将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (!StringUtils.isEmpty(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.size() == 0) {
			return null;
		} else if (argList.size() == 1) {
			return argList.get(0);
		} else {
			return argList;
		}
	}
}
