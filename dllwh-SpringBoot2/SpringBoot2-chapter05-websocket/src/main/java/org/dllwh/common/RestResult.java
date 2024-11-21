package org.dllwh.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:统一返回数据结构
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
@Getter
@Setter
public final class RestResult implements Serializable {

	private static final long serialVersionUID = 2201399824548851402L;
	/** 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位） */
	private String currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	/** 返回结果编码 */
	private Integer resultCode = ResultCode.SUCCESS.getCode();
	/** 返回的数据息 */
	private Object data;
	/** 请求返回的提示信息，给前端进行页面展示的信息 */
	private String message = "操作成功";
	/** 额外参数,不使用Map<String, Object>防止JSON解析时过于复杂 */
	private Map<String, Object> extraInfo = new HashMap<String, Object>();

	/**
	 * 构造器私有
	 */
	private RestResult() {
		super();
	}

	/**
	 * 构造器私有
	 */
	private RestResult(ResultCode resultCode) {
		super();
		this.resultCode = resultCode.getCode();
		this.message = resultCode.getMessage();
	}

	/**
	 * 构造器私有
	 */
	public RestResult(String message, Object data, Map<String, Object> extraInfo) {
		super();
		this.message = message;
		this.data = data;
		this.extraInfo = extraInfo;
	}

	/**
	 * @方法描述: 添加返回数据
	 * 
	 * @param key
	 * @param value
	 */
	public void setData(String key, Object value) {
		extraInfo.put(key, value);
	}

	/**
	 * @方法描述: 可以连接追加调用此方法，添加返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public RestResult add(String key, Object value) {
		extraInfo.put(key, value);
		return this;
	}

	/**
	 * @方法描述: 返回执行成功,不返回数据直接返回成功信息
	 * @return
	 */
	public static RestResult success() {
		return new RestResult();
	}

	/**
	 * @方法描述: 返回执行成功，返回数据
	 * @param message 自定义成功提示信息
	 * @param data    自定义返回数据
	 * @return
	 */
	public static RestResult success(String message, Object data) {
		return new RestResult(message, data, null);
	}

	/**
	 * @方法描述: 返回执行成功，返回数据
	 * @param message 自定义成功提示信息
	 * @return
	 */
	public static RestResult success(Object data) {
		return new RestResult(null, data, null);
	}

	/**
	 * @方法描述: 成功返回执行成功，返回数据并返回额外参数
	 * @param data      自定义返回数据
	 * @param extraInfo 额外参数
	 * @return
	 */
	public static RestResult success(Object data, Map<String, Object> extraInfo) {
		return new RestResult(null, data, extraInfo);
	}

	/**
	 * @方法描述:返回执行成功，返回数据并返回额外参数
	 * @param message   自定义成功提示信息
	 * @param data      自定义返回数据
	 * @param extraInfo 额外参数
	 * @return
	 */
	public static RestResult success(String message, Object data, Map<String, Object> extraInfo) {
		return new RestResult(message, data, extraInfo);
	}

	/**
	 * @方法描述:返回执行失败
	 * @return
	 */
	public static RestResult fail() {
		return new RestResult(ResultCode.FAILURE);
	}

	/**
	 * @方法描述:返回失败的消息
	 * @param message 自定义失败原因
	 * @return
	 */
	public static RestResult fail(String message) {
		RestResult restResult = new RestResult(ResultCode.FAILURE);
		restResult.setMessage(message);
		return restResult;
	}

	/**
	 * @方法描述:返回错误消息
	 * @param resultCode
	 * @return
	 */
	public static RestResult fail(ResultCode resultCode) {
		return new RestResult(resultCode);
	}

	/**
	 * @方法描述:返回失败的状态码以及数据
	 * @param resultCode
	 * @param message    自定义失败原因
	 * @return
	 */
	public static RestResult fail(int resultCode, String message) {
		RestResult restResult = new RestResult();
		restResult.setResultCode(resultCode);
		restResult.setMessage(message);
		return restResult;
	}

	/**
	 * @方法描述:返回失败的状态码以及数据
	 * @param resultCode
	 * @param message    自定义失败原因
	 * @param data       自定义返回数据
	 * @return
	 */
	public static RestResult fail(int resultCode, String message, Object data) {
		RestResult restResult = new RestResult();
		restResult.setResultCode(resultCode);
		restResult.setMessage(message);
		restResult.setData(data);
		return restResult;
	}

	/**
	 * @方法描述:返回失败的状态码以及数据
	 * @param resultCode
	 * @param data       自定义返回数据
	 * @return
	 */
	public static RestResult fail(ResultCode resultCode, Object data) {
		RestResult restResult = new RestResult(resultCode);
		restResult.setData(data);
		return restResult;
	}

	/**
	 * @方法描述:失败，自定义失败返回状态并返回数据、额外参数
	 * @param resultCode
	 * @param data       自定义返回数据
	 * @param extraInfo
	 * @return
	 */
	public static RestResult fail(ResultCode resultCode, Object data, Map<String, Object> extraInfo) {
		RestResult restResult = new RestResult(resultCode);
		restResult.setData(data);
		restResult.setExtraInfo(extraInfo);
		return restResult;
	}
}
