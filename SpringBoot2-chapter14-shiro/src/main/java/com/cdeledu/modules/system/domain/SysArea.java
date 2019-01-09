package com.cdeledu.modules.system.domain;

import com.cdeledu.common.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 行政区域字典表
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月8日 下午3:22:01
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("行政区域字典表")
public class SysArea extends BaseEntity {
	/** 行政区划代码 */
	private String	areaCode;
	/** 所辖行政区 */
	private String	areaName;
	private Integer	parentId;
	/** 行政区级别(类型 0：国家 1：省、直辖市 2：市 3：区 4：街道 、办事处 5：村委会、村) */
	private Integer	areaLevel;
	/** 简称 */
	private String	shortName;
	/** 点击访问地址 */
	private String	areaUrl;
}