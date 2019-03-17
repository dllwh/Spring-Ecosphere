package org.dllwh.model.rbac;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SysUserVO {
	// @NotBlank(message = "登陆账号不能为空")
	private String	userName;		// 用户名(数字与字母组成)
	// @NotBlank(message = "登陆密码不能为空")
	private String	password;		// 密码(真正的密码与用户名加密之后的结果)
	private Integer	userType;		// 用户类型(超级管理员、系统管理员、管理员)
	private String	nickName;		// 昵称
	private String	realName;		// 真实姓名
	private String	email;			// 用户邮箱
	private Integer	emailstatus;	// email是否经过验证
	private Integer	userSex;		// 性别
	private String	mobile;			// 用户手机号码
	private String	telephone;		// 用户电话号码
	private String	signature;		// 个性签名
	private String	imageCaptcha;	// 验证码
	private Integer	ifLocked;		// 是否锁定(1:不锁定;0：锁定)
	private Integer	loginFlag;		// 是否允许登陆;1:允许,默认值;0:不允许
	private Integer	creator;		// 最初创建者
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	createTime;		// 数据创建时间
	private Integer	modifier;		// 最后修改人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	updateTime;		// 数据最后更新时间
	private Integer	id;				// 实体编号（唯一标识）
	private Integer	ifVisible;		// 是否可见;1:可见,默认值;0:不可见
	private Integer	ifEnabled;		// 是否有效;-1:删除;0:不可用,默认值;1:可用
	private Integer	allowEdit;		// 是否允许编辑;1:允许,默认值;0:不允许
	private Integer	allowDelete;	// 是否允许删除;1:允许删除,默认值,0:不允许删除
	private Integer	sequence;		// 排序,默认从1开始
	private String	remark;			// 备注、说明
}