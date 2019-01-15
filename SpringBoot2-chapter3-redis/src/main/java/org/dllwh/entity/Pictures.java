package org.dllwh.entity;

import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 美女图片爬取
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月15日 下午10:49:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class Pictures {
	private String	id;
	private String	title;
	private String	url;
	private Integer	pictureurlsNum;
	private Integer	zan;
	private String	biaoqian;
	private String	keywords;
	private Date	lastUpdateDate;
}