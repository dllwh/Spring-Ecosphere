package com.cdeledu.framework.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 日志实体
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 上午8:25:48
 * @版本: V1.2
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoggerEntity {
	
	private Long		id;					// 编号
						
	private String		loginName;			// 操作人员登录账号
	private String		clientIp;			// 客户端请求ip
	private String		requestUrl;			// 客户端请求路径
	private Integer		requestType;		// 终端请求方式,0:普通请求,1：ajax请求
	private String		requestMethod;		// 请求方式method,post,get等
	private String		requestParameter;	// 请求参数
	private String		operateClass;		// 调用类
	private String		sessionId;			// 请求接口唯一session标识
	private String		operateMethod;		// 调用方法
	private String		requestSource;		// 请求来源，pc：pc端，wap：wap端 默认来源为pc
	private Integer		operateStatus;		// 请求时httpStatusCode代码，如：200,400,404等
						
	private Timestamp	startTime;			// 请求开始时间
	private Timestamp	returnTime;			// 接口返回时间 or 异常产生时间
	private String		returnData;			// 接口返回数据 or 异常对象的json 形式
	private Long		timeConsuming;		// 请求耗时，单位：毫秒
	private String		logType;			// 日志类型,‘operate’:操作日志，‘exception’:异常日志
	private String		exceptionDetail;	// 异常信息
}