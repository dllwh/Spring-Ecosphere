package org.dllwh.core.baseBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.dllwh.enumresource.ResponseCode;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 统一返回数据结构
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月12日 下午7:15:16
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class RestResult {
	/** 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位） */
	private String	currentTime	= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
			.format(LocalDateTime.now());
	private Integer	statusCode	= ResponseCode.SUCCESS.code;						// 成功或者失败的code错误码
	private String	url;															// 访问Url
	private String	error;															// 错误类型
	private String	stackTrace;														// 错误的堆栈轨迹
	private Object	data;															// 成功时返回的数据息
	private String	message		= "操作成功";											// 请求失败返回的提示信息，给前端进行页面展示的信息
}