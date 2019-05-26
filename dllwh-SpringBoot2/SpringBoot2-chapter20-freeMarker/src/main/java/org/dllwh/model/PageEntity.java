package org.dllwh.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 分页
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午9:40:07
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Getter
@Setter
public class PageEntity {
	/** 当前页数:第几页 --pageNo */
	protected Integer	pageNumber	= 1;
	/** 每页记录数 :pageSize */
	protected Integer	pageSize	= 10;
	/** 起始页 */
	protected int		startRow;
	/** 排序字段名 */
	protected String	sortName;
	/** 按什么排序(asc,desc) */
	protected String	sortOrder;

	public PageEntity(Integer pageNumber, Integer pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public void setSortOrder(String sortOrder) {
		if (sortOrder.equalsIgnoreCase("asc") || sortOrder.equalsIgnoreCase("desc")) {
			this.sortOrder = sortOrder;
		} else {
			this.sortOrder = "";
		}
	}

	public int getStartRow() {
		startRow = (pageNumber - 1) * getPageSize();
		return startRow;
	}
}