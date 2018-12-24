package com.cdeledu.framework.shiro.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 密码服务
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:41:11
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Component
public class PasswordService {
	/** 密钥 */
	private static final String PRIVATE_KEY = "237c038cd985c0f75e38756af2d54de9";

	public String encryptPassword(String username, String password) {
		return new Md5Hash(username + password + PRIVATE_KEY).toHex();
	}

	public String encryptPassword(String username, String password, String salt) {
		return new Md5Hash(username + password + salt).toHex();
	}
}
