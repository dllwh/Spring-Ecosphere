package org.dllwh.devTools.api;

import java.io.Serializable;

public interface IResultCode extends Serializable {
	/** 消息 */
	String getMessage();

	/** 状态码 */
	int getCode();
}