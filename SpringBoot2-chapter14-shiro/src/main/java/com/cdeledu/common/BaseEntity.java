package com.cdeledu.common;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:16:41
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Data
public class BaseEntity {
	/** 创建人 */
	protected Integer	creator;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp	createTime;
	/** 最后修改人 */
	protected Integer	modifier;
	/** 修改日期 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp	updateTime;
	/** 备注 */
	private String		remark;
}
