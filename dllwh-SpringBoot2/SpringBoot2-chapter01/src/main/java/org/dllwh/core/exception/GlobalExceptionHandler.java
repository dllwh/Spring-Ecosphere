package org.dllwh.core.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.dllwh.utils.WebHelperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 对异常（包括系统异常与业务逻辑异常）进行统一处理
 * 
 *       <pre>
 * 1、当普通调用时，跳转到自定义的错误页面
 * 2、当ajax 调用时，可返回约定的json数据对象，方便页面统一处理
 *       </pre>
 * 
 * @使用说明:
 * 
 *        <pre>
 * {@link ControllerAdvice 默认扫描路径：例如com.cdeledu.controller}
 * {@link ExceptionHandler 指定捕捉异常} 
 * {@link ModelAndView 返回异常信息页(View)}
 * {@link ResponseBody 返回异常信息(JSON)}
 * 		</pre>
 * 
 *        <pre>
 * ControllerAdvice应用范围
 * ①basePackages：应用在xx包，只捕捉xx包中的异常
 * ②basePackageClasses：应用在xx类，只捕捉xx类中的异常
 * ③assignableTypes：应用在加了@Controller的类，只捕捉标识了@controller注解的类中的异常
 * ④annotations：应用在带有xx注解的类或者方法
 *        </pre>
 * 
 * @使用注意点:
 * 
 *         <pre>
 * 1.获取异常：直接在方法参数注入
 * 2.常见缺点：无法捕捉404类异常
 * 3.替代方案：实现ErrorController
 *         </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月12日 下午7:20:34
 * @版本: V1.2
 * @since: JDK 1.8
 */
@CrossOrigin
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	private final static String DEFAULT_ERROR_VIEW = "exception"; // 错误信息页
	@Autowired
	private ErrorInfoBuilder errorInfoBuilder;

	/**
	 * @方法描述 : 根据业务规则,统一处理异常。
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public Object defaultExceptionHandler(HttpServletRequest request, Exception ex) {
		log.debug(getClass().getName() + ".errorHandler】统一异常处理：request=" + request);
		// 1、若为AJAX请求，则返回异常信息(JSON)
		if (WebHelperUtils.isAjaxRequest(request)) {
			return errorInfoBuilder.buildErrorInfo(request);
		}
		// 2.其余请求,则返回指定的异常信息页(View).
		return new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo",
				errorInfoBuilder.buildErrorInfo(request, ex));
	}
}