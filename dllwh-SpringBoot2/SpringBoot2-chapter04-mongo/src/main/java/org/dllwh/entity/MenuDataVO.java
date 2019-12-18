package org.dllwh.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 菜单数据结构
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月29日 下午7:52:34
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDataVO {
	/** ID,唯一标识 */
	private Integer          id;
	/** 显示的标题 */
	private String           title;
	/** 地址，(a标签的href属性值) */
	private String           path;
	/** 图标 */
	private String           icon;
	/** 父级ID(重要)，一级菜单pid为0 */
	private String           pid;
	/** 是否展开(该值只对拥有子菜单的菜单有效) */
	private boolean          open;
	/** 跳转到外链 */
	private boolean          blank;
	/** 子菜单的数据列表 */
	private List<MenuDataVO> children;
}