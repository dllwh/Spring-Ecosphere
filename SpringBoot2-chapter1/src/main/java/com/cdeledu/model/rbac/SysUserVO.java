package com.cdeledu.model.rbac;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SysUserVO {
	/** 用户名(数字与字母组成) */
	// @NotBlank(message = "登陆账号不能为空")
	private String userName;
	/** 密码(真正的密码与用户名加密之后的结果) */
	// @NotBlank(message = "登陆密码不能为空")
	private String password;
	/** 用户类型(超级管理员、系统管理员、管理员) */
	private Integer userType;
	/** 昵称 */
	private String nickName;
	/** 真实姓名 */
	private String realName;
	/** 用户邮箱 */
	private String email;
	/** email是否经过验证 */
	private Integer emailstatus;
	/** 性别 */
	private Integer userSex;
	/** 用户手机号码 */
	private String mobile;
	/** 用户电话号码 */
	private String telephone;
	/** 个性签名 */
	private String signature;
	// @NotBlank(message = "验证码")
	private String imageCaptcha;
	/** 是否锁定(1:不锁定;0：锁定) */
	private Integer ifLocked;
	/** 是否允许登陆;1:允许,默认值;0:不允许 */
	private Integer loginFlag;
	// 最初创建者
	private Integer creator;
	// 数据创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	// 最后修改人
	private Integer modifier;
	// 数据最后更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**  实体编号（唯一标识）  */
	private Integer id;
	/** 是否可见;1:可见,默认值;0:不可见  */
	private Integer ifVisible;
	/**  是否有效;-1:删除;0:不可用,默认值;1:可用  */
	private Integer ifEnabled;
	/** 是否允许编辑;1:允许,默认值;0:不允许  */
	private Integer allowEdit;
	/**  是否允许删除;1:允许删除,默认值,0:不允许删除  */
	private Integer allowDelete;
	/**  排序,默认从1开始  */
	private Integer sequence;
	/**备注、说明*/
	private String remark;
}
