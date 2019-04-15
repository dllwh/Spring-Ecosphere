package org.dllwh.devTools.api;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.dllwh.devTools.constant.ProjectConstant;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 * 
 * @param <T>
 *
 * @类描述: 统一API响应结果封装
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月8日 下午10:58:11
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Getter
@Setter
@ToString
@ApiModel(description = "返回信息")
@NoArgsConstructor
public class ReturnRest<T> {
	@ApiModelProperty(value = "状态码", required = true)
	private int		code;
	@ApiModelProperty(value = "是否成功", required = true)
	private boolean	success;
	@ApiModelProperty(value = "承载数据")
	private T		data;
	@ApiModelProperty(value = "返回消息", required = true)
	private String	msg;

	private ReturnRest(IResultCode resultCode) {
		this(resultCode, null, resultCode.getMessage());
	}

	private ReturnRest(IResultCode resultCode, String msg) {
		this(resultCode, null, msg);
	}

	private ReturnRest(IResultCode resultCode, T data, String msg) {
		this(resultCode.getCode(), data, msg);
	}

	private ReturnRest(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.success = ResultCode.SUCCESS.code == code;
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result
	 *            Result
	 * @return 是否成功
	 */
	public static boolean isSuccess(@Nullable ReturnRest<?> result) {
		return Optional.ofNullable(result)
				.map(x -> ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code)).orElse(Boolean.FALSE);
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result
	 *            Result
	 * @return 是否成功
	 */
	public static boolean isNotSuccess(@Nullable ReturnRest<?> result) {
		return !isSuccess(result);
	}

	/**
	 * 返回R
	 *
	 * @param data
	 *            数据
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> data(T data) {
		return data(data, ProjectConstant.DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 返回R
	 *
	 * @param data
	 *            数据
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> data(T data, String msg) {
		return data(HttpServletResponse.SC_OK, data, msg);
	}

	/**
	 * 返回R
	 *
	 * @param code
	 *            状态码
	 * @param data
	 *            数据
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> data(int code, T data, String msg) {
		return new ReturnRest<>(code, data, data == null ? ProjectConstant.DEFAULT_NULL_MESSAGE : msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> success(String msg) {
		return new ReturnRest<>(ResultCode.SUCCESS, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode
	 *            业务代码
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> success(IResultCode resultCode) {
		return new ReturnRest<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode
	 *            业务代码
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> success(IResultCode resultCode, String msg) {
		return new ReturnRest<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> fail(String msg) {
		return new ReturnRest<>(ResultCode.FAILURE, msg);
	}

	/**
	 * 返回R
	 *
	 * @param code
	 *            状态码
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> fail(int code, String msg) {
		return new ReturnRest<>(code, null, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode
	 *            业务代码
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> fail(IResultCode resultCode) {
		return new ReturnRest<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode
	 *            业务代码
	 * @param msg
	 *            消息
	 * @param <T>
	 *            T 泛型标记
	 * @return R
	 */
	public static <T> ReturnRest<T> fail(IResultCode resultCode, String msg) {
		return new ReturnRest<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param flag
	 *            成功状态
	 * @return R
	 */
	public static <T> ReturnRest<T> status(boolean flag) {
		return flag ? success(ProjectConstant.DEFAULT_SUCCESS_MESSAGE)
				: fail(ProjectConstant.DEFAULT_FAILURE_MESSAGE);
	}
}