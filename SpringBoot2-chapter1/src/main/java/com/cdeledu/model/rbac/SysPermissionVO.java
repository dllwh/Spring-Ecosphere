package com.cdeledu.model.rbac;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SysPermissionVO {
	private String					menuName;			// 菜单名称
	private String					menuUrl;			// 菜单链接地址
	private Integer					type;				// 类型。0:目录，默认值；1：菜单；2：按钮；3：窗体；4：链接
	private Integer					parentCode;			// 父菜单编号
	private String					parentName;			// 父级菜单名称
	private String					iconClass;			// 菜单图标样式
	private Boolean					open	= false;	// tree属性
	private List<SysPermissionVO>	childrenList;		// 子节点的集合
	private Integer					creator;			// 最初创建者
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date					createTime;			// 数据创建时间
	private Integer					modifier;			// 最后修改人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date					updateTime;			// 数据最后更新时间
	private Integer					id;					// 实体编号（唯一标识）
	private Integer					ifVisible;			// 是否可见;1:可见,默认值;0:不可见
	private Integer					ifEnabled;			// 是否有效;-1:删除;0:不可用,默认值;1:可用
	private Integer					allowEdit;			// 是否允许编辑;1:允许,默认值;0:不允许
	private Integer					allowDelete;		// 是否允许删除;1:允许删除,默认值,0:不允许删除
	private Integer					sequence;			// 排序,默认从1开始
	private String					remark;				// 备注、说明
}