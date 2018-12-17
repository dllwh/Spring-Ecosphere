package com.cdeledu.modules.system.domain;

import java.security.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 角色表实体类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午11:54:43
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@ApiModel(value = "角色表实体类")
public class SysRole {
	private Integer		id;
	/** 角色名称 */
	private String		roleName;
	/** 角色编码 */
	private String		roleCode;
	/** 排序 */
	private Integer		sequence;
	/** 是否可见(启用/禁用),不为空 */
	private Integer		ifVisible;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private Integer		ifEnabled;
	/** 创建人 */
	private Integer		creator;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp	createTime;
	/** 最后修改人 */
	private Integer		modifier;
	/** 修改日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp	updateTime;
	/** 备注 */
	private String		remark;
}