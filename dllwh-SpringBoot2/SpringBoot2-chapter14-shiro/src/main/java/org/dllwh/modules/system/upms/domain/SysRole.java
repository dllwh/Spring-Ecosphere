package org.dllwh.modules.system.upms.domain;

import java.util.List;

import org.dllwh.common.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色表实体类")
public class SysRole extends BaseEntity {
	private Integer			id;
	/** 角色名称 */
	private String			roleName;
	/** 角色编码 */
	private String			roleCode;
	/** 排序 */
	private Integer			sequence;
	/** 是否可见(启用/禁用),不为空 */
	private Integer			ifVisible;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private Integer			ifEnabled;
	/** 一个角色对应多个用户 */
	private List<SysUser>	userInfos;
}