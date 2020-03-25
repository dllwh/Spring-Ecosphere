package org.dllwh.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 登录请求消息结构
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-02 11:44:26 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends BaseRequest {
	private static final long serialVersionUID = -7384540992496656807L;
	private String appid;
	private String roomid;
	private String userId;
	/** 用户名 */
	private String userName;
	private String nickName;
	private String role;
	private long time;
	/** 校验码(此字段可与logingname、password共存,也可只选一种方式) */
	private String token;
	/** 终端类型 */
	private String platform;
	/** 终端版本号 */
	private String platformVersion;
	/** 登录时间 */
	private Long bindTime;
	/** 客户端ID (设备号码+应用包名),ios为devicetoken */
	private String deviceId;
}
