package com.cdeledu.modules.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Data
@ApiModel(value = "角色表实体类")
public class SysRole {
	@ApiModelProperty(value = "主键：角色ID", name = "id", example = "1")
	private Integer	id;
	@ApiModelProperty(value = "角色名称", name = "roleName")
	private String	roleName;
	@ApiModelProperty(value = "角色编码", name = "roleCode")
	private String	roleCode;
}
