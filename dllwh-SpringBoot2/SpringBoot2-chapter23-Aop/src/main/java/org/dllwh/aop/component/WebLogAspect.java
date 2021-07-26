package org.dllwh.aop.component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dllwh.aop.annotation.OperLog;
import org.dllwh.aop.model.WebLog;
import org.dllwh.aop.service.LogService;
import org.dllwh.aop.util.IpUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

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
 * @版本: V 2.1
 * @since: JDK 1.8
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
	/** 操作版本号 */
	// @Value("${version}")
	// private String operVer;
	/** 时间线程 */
	private static final ThreadLocal<Long> beginTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal beginTime");
	/** 日志线程 */
	private static final ThreadLocal<WebLog> logThreadLocal = new NamedThreadLocal<WebLog>("ThreadLocal log");
	/** 异步操作记录日志的线程池 */
	private ScheduledThreadPoolExecutor threadPoolTaskExecutor = new ScheduledThreadPoolExecutor(10);
	@Autowired
	LogService logService;

	/**
	 * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
	 * 
	 * @within: 匹配使用指定注解的类，其所有方法都将被匹配
	 * @annotation:指定方法所应用的注解。也就是说，所有被指定注解标注的方法都将匹配。
	 */
	@Pointcut("@annotation(org.dllwh.aop.annotation.OperLog)")
	// @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	// @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
	public void webLog() {
	}

	/**
	 * 定义切入点，设置操作异常切入点记录异常日志 扫描所有controller包下操作
	 */
	@Pointcut("execution(public * org.dllwh.aop.controller..*.*(..))")
	public void operExceptionLog() {
	}

	/**
	 * 前置通知，用于拦截记录用户的操作。
	 * 
	 * 在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
	 * 
	 * @param joinPoint 切点
	 */
	@Before(value = "webLog() || operExceptionLog()")
	public void doBefore(JoinPoint joinPoint) {
		log.info("=========执行前置通知==================");
		// 线程绑定变量（该数据只有当前请求的线程可见）
		beginTimeThreadLocal.set(System.currentTimeMillis());
	}

	/**
	 * 环绕通知,决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值
	 * 
	 * 必须有这个返回值。可以这样理解，Around方法之后，不再是被织入的函数返回值，而是Around函数返回值
	 *
	 * @param joinPoint 切点
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "webLog() || operExceptionLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logThreadLocal.set(new WebLog());
		log.info("=========执行环绕通知==================");
		// 执行方法
		Object result = joinPoint.proceed();
		return result;
	}

	/**
	 * 最终(后置)通知。
	 * 
	 * 当目标方法执行成功后执行该方法体，不论是正常返回还是异常退出
	 *
	 * @param joinPoint 切点
	 */
	@After(value = "webLog() || operExceptionLog()")
	public void doAfter(JoinPoint joinPoint) {
		log.info("=========执行后置通知==================");
	}

	/**
	 * 后置返回通知,当目标方法执行成功后执行该方法体。
	 * 
	 * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
	 *
	 * @param joinPoint    切入点
	 * @param keys         返回结果
	 * @param returnObject 返回值的信息
	 */
	@AfterReturning(value = "webLog() || operExceptionLog()", returning = "keys")
	public void doAfterReturning(JoinPoint joinPoint, Object keys) {
		log.info("=========执行后置返回通知===============");
		WebLog webLog = getWebLog(joinPoint);
		webLog.setLogType("info");
		if (keys != null) {
			webLog.setResult(JSON.toJSONString(keys));
		}
		// 方法结束时间
		Long endTime = System.currentTimeMillis();
		// 得到线程绑定的局部变量（开始时间）
		long startTime = beginTimeThreadLocal.get();
		// 执行时长(毫秒)
		webLog.setSpendTime(endTime - startTime);
		webLog.setStartTime(startTime);
		logThreadLocal.set(webLog);
		// 通过线程池来执行日志保存
		threadPoolTaskExecutor.execute(new SaveLogThread(webLog, logService));
		log.info("{}", JSONUtil.parse(webLog));

	}

	/**
	 * 异常通知
	 * 
	 * 当目标方法抛出异常返回后，执行该方法体,用于拦截记录异常日志
	 *
	 * @param joinPoint
	 * @param exception 为Throwable类型将匹配任何异常
	 */
	@AfterThrowing(value = "webLog() || operExceptionLog()", throwing = "exception")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {

		log.info("=========执行异常通知===============");
		WebLog webLog = getWebLog(joinPoint);
		webLog.setExcepName(exception.getClass().getName());
		// 异常信息
		webLog.setExcepDetail(stackTraceToString(exception));
		webLog.setLogType("error");
		logThreadLocal.set(webLog);
		// 通过线程池来执行日志保存
		threadPoolTaskExecutor.execute(new SaveLogThread(webLog, logService));

		log.info("{}", JSONUtil.parse(webLog));
	}

	/**
	 * 获取日志信息
	 *
	 * @param joinPoint
	 * @param exception
	 * @return
	 */
	private WebLog getWebLog(JoinPoint joinPoint) {
		WebLog webLog = logThreadLocal.get();

		// 获取当前请求对象
		RequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		// 获取目标方法的参数
		Object[] getArgs = joinPoint.getArgs();
		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 获取切入点所在的方法
		Method method = methodSignature.getMethod();

		// 当前类有 OperLog 注解的方法
		OperLog opLog = method.getAnnotation(OperLog.class);
		if (opLog != null) {
			// 通过注解OperLog管理请求日志
			webLog.setOperateModel(opLog.operModel());
			webLog.setOperateType(opLog.operType());
			webLog.setOperateDesc(opLog.operDesc());
		} else if (method.isAnnotationPresent(ApiOperation.class)) { // 当前类有 swagger 注解的方法
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			webLog.setOperateDesc(apiOperation.value());
		}

		String urlStr = request.getRequestURL().toString();
		webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
		webLog.setIpAddress(IpUtilHelper.getClientIP(request));
		// 请求方式
		webLog.setRequestType(request.getMethod());
		// 请求参数
		webLog.setParameter(getParameter(method, getArgs));
		webLog.setUri(request.getRequestURI());
		// 请求Url
		webLog.setUrl(request.getRequestURL().toString());
		// 获取执行方法的类的名称（包名加类名）
		webLog.setOperateClass(joinPoint.getTarget().getClass().getName());
		// 获取方法名
		webLog.setOperateMethod(method.getName());
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

	/**
	 * 转换异常信息为字符串
	 *
	 * @param exception 异常
	 */
	private String stackTraceToString(Throwable exception) {
		// 异常名称
		String exceptionName = exception.getClass().getName();
		// 异常信息
		String exceptionMessage = exception.getMessage();
		// 堆栈信息
		StackTraceElement[] elements = exception.getStackTrace();

		StringBuffer strbuff = new StringBuffer();
		for (StackTraceElement stet : elements) {
			strbuff.append(stet + "\n");
		}
		String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
		return message;
	}

	/**
	 * 保存日志线程
	 */
	private class SaveLogThread implements Runnable {
		private WebLog webLog;
		private LogService logService;

		public SaveLogThread(WebLog webLog, LogService logService) {
			this.webLog = webLog;
			this.logService = logService;
		}

		@Override
		public void run() {
			log.info("通过线程池来执行日志保存");
			logService.insert(webLog);
		}
	}
}
