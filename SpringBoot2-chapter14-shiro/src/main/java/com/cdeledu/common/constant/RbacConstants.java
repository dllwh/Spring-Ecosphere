package com.cdeledu.common.constant;

public interface RbacConstants {
	/**
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 菜单类型
	 * @创建者: 皇族灬战狼
	 * @联系方式: duleilewuhen@sina.com
	 * @创建时间: 2018年12月24日 下午2:40:27
	 * @版本: V 1.0.1
	 * @since: JDK 1.8
	 */
	public enum SysMenuType {
		/** 目录 */
		CATALOG(0),
		/** 菜单 */
		MENU(1),
		/** 按钮 */
		BUTTON(2);
		int value;

		SysMenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
