package com.cdeledu.core.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdeledu.core.baseBase.RestResult;
import com.cdeledu.core.constant.PathConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 统一处理错误/异常(针对控制层)
 * 
 *       <pre>
 * 1、ErrorController 接口的默认实现类是abstract：AbstractErrorController
 * 2、AbstractErrorController 的子类 BasicErrorController 才是真正干活儿的实现类（分html、json 两类处理）
 * 3、BasicErrorController 有 private final ErrorProperties errorProperties;属性
 *    BasicErrorController的封装只能将状态码的提示信息返回前台，不能拿到手动抛异常的信息，因此需要实现HandlerExceptionResolver
 *       </pre>
 * 
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月23日 下午3:39:11
 * @版本: V1.3
 * @since: JDK 1.8
 */
@Controller
@Slf4j
public class GlobalExceptionController implements ErrorController {
	@Autowired
	private ErrorInfoBuilder errorInfoBuilder; // 错误信息构建器

	/**
	 * 发现错误路径
	 */
	public String getErrorPath() {
		return errorInfoBuilder.getErrorProperties().getPath();
	}

	/**
	 * @方法描述 : 情况1：若请求头返回类型为text/html,则返回错误信息页(View).
	 * @param request
	 *            请求对象
	 * @return
	 */
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request) {
		log.debug("统一异常处理【" + getClass().getName() + ".errorHtml】：request=" + request);
		return new ModelAndView(PathConstant.DEFAULT_ERROR_VIEW, "errorInfo",
				errorInfoBuilder.buildErrorInfo(request));
	}

	/**
	 * @方法描述 : 情况2：其它请求返回类型，则返回详细的错误信息(JSON)
	 * @param request
	 *            请求对象
	 * @return 错误信息字符串
	 */
	@ResponseBody
	@RequestMapping
	public RestResult error(HttpServletRequest request) {
		log.info("统一异常处理【" + getClass().getName() + ".error】");
		return errorInfoBuilder.buildErrorInfo(request);
	}
}