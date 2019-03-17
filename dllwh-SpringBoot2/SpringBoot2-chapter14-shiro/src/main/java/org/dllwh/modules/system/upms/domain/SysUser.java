package org.dllwh.modules.system.upms.domain;

import java.sql.Timestamp;
import java.util.List;

import org.dllwh.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统管理员用户表
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午11:56:24
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户表")
public class SysUser extends BaseEntity {
	private Integer			id;
	/** 用户名(数字与字母组成) */
	private String			userName;
	/** 密码(真正的密码与用户名加密之后的结果) */
	private String			password;
	/** 用户头像 */
	private Integer			avatar;
	/** 昵称 */
	private String			nickName;
	/** 用户邮箱 */
	private String			email;
	/** email是否经过验证 */
	private Integer			emailstatus;
	/** 联系方式 */
	private String			mobile;
	/** 是否锁定(1:不锁定;0：锁定) */
	private Integer			ifLocked;
	/** 审核状态(-1：审核未通过；0：待审核或新用户；1：审核成功) */
	private Integer			IfVisible;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private Integer			ifEnabled;
	/** 拒绝登录描述 */
	private String			refuseDes;
	/** 角色组 */
	private Integer[]		roleIds;
	/** 最后登陆IP */
	private String			loginIp;
	/** 最后登陆时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp		loginDate;
	// 一个用户具有多个角色
	private List<SysRole>	roleList;
}
