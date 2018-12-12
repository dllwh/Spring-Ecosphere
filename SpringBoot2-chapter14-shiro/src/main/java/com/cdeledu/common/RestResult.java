package com.cdeledu.common;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 统一返回数据结构
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午10:39:06
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@ApiModel(value = "RestResult",description="统一返回数据结构")
public class RestResult {
	/** 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位） */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime	currentTime	= LocalDateTime.now();
	@ApiModelProperty(value = "成功或者失败的code错误码", name = "statusCode")
	private Integer			statusCode;
	@ApiModelProperty(value = "访问Url", name = "url")
	private String			url;
	@ApiModelProperty(value = "错误类型", name = "error")
	private String			error;
	@ApiModelProperty(value = "错误的堆栈轨迹", name = "stackTrace")
	private String			stackTrace;
	@ApiModelProperty(value = "成功时返回的数据息", name = "data")
	private Object			data;
	@ApiModelProperty(value = "请求失败返回的提示信息，给前端进行页面展示的信息", name = "message")
	private String			message;
							
	/**
	 * @方法描述:返回错误消息
	 * @return
	 */
	public static RestResult error() {
		return error(500, "操作失败");
	}
	
	/**
	 * @方法描述:返回错误消息
	 * @param message
	 * @return
	 */
	public static RestResult error(String message) {
		return error(500, message);
	}
	
	/**
	 * @方法描述:返回错误消息
	 * @param statusCode
	 * @param message
	 * @return
	 */
	public static RestResult error(int statusCode, String message) {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(statusCode);
		restResult.setMessage(message);
		return restResult;
	}
	
	public static RestResult success() {
		return success("操作成功");
	}
	
	public static RestResult success(String message) {
		RestResult restResult = new RestResult();
		restResult.setStatusCode(200);
		restResult.setMessage(message);
		return restResult;
	}
}