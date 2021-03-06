package org.dllwh.constant;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RestResult", description = "统一返回数据结构")
public class RestResult {
	/** 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位） */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime	currentTime	= LocalDateTime.now();
	@ApiModelProperty(value = "结果响应码", name = "statusCode")
	private Integer			statusCode;
	@ApiModelProperty(value = "访问Url", name = "url")
	private String			url;
	@ApiModelProperty(value = "返回的数据", name = "data")
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
