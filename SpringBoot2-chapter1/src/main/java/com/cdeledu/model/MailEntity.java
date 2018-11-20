package com.cdeledu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮件
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 上午8:09:46
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Data
public class MailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	// 此处填写SMTP服务器
	private String smtpService;
	// 设置端口号
	private String smtpPort;
	// 设置发送邮箱
	private String fromMailAddress;
	// 设置发送邮箱的STMP口令
	private String fromMailStmpPwd;
	// 设置邮件标题
	private String title;
	// 设置邮件内容
	private String content;
	// 内容格式（默认采用html）
	private String contentType;
	// 接受邮件地址集合
	private List<String> list = new ArrayList<>();
}
