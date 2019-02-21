package org.dllwh.module;

import lombok.Data;

@Data
public class ResultVo {
	private int		statusCode;
	private String	message;
	private Object	data;

	public static ResultVo success(String message, Object data) {
		ResultVo restResult = new ResultVo();
		restResult.setStatusCode(200);
		restResult.setMessage(message);
		restResult.setData(data);
		return restResult;
	}

	public static ResultVo success() {
		return success("操作成功", null);
	}

	public static ResultVo success(Object data) {
		return success("操作成功", data);
	}

	public static ResultVo fail(int statusCode, String message) {
		ResultVo restResult = new ResultVo();
		restResult.setStatusCode(statusCode);
		restResult.setMessage(message);
		return restResult;
	}

	public static ResultVo fail() {
		return fail(500, "操作失败");
	}
}