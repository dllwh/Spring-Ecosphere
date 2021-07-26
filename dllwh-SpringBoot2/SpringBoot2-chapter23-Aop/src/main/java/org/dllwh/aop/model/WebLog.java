package org.dllwh.aop.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Controller层的日志封装类
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-08-20 00:49:41
 * @版本: V 1.0.2
 * @since: JDK 1.8
 *
 */
@Data
@NoArgsConstructor
public class WebLog implements Serializable {

	private static final long serialVersionUID = 5132663133670672913L;

	/** 主键 */
	private Integer id;
	/** 操作版本号 */
	private String operateVer;

	/** 操作模块 */
	private String operateModel;
	/** 操作类型 */
	private String operateType;
	/** 操作说明 */
	private String operateDesc;

	/** 操作者ID */
	private String operateUserId;
	/** 操作者名称 */
	private String operateUserName;

	/** 目标类名 */
	private String operateClass;
	/** 目标方法 */
	private String operateMethod;

	/** 操作时间 */
	private Long startTime;
	/** 消耗时间(单位：毫秒) */
	private Long spendTime;

	/** IP地址 */
	private String ipAddress;
	/** 请求方式(请求类型) */
	private String requestType;
	/** 根路径 */
	private String basePath;
	/** URI */
	private String uri;
	/** URL */
	private String url;
	/** 请求参数 */
	private Object parameter;
	/** 请求返回的结果 */
	private String result;
	/** 日志类型(info:操作日志,error:错误) */
	private String logType = "info";
	
	/** 异常详情 */
	private String excepDetail = "";
	/** 异常名称 */
	private String excepName = "";

}
