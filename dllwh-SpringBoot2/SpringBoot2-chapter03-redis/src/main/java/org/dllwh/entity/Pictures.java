package org.dllwh.entity;

import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 图片爬取
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月15日 下午10:49:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class Pictures {
	private String	_id;
	/** 创建时间 */
	private Date	createdAt;
	/** 对内容的描述 */
	private String	desc;
	/** 发布时间 */
	private Date	publishedAt;
	/** 来源 */
	private String	source;
	/** 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all */
	private String	type;
	/** 地址 */
	private String	url;
}