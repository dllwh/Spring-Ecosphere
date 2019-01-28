package org.dllwh.entity;

import lombok.Data;

@Data
public class ChinaArea {
	private Integer	id;
	/** 父级ID */
	private Integer	parentId;
	/** 层级 */
	private Integer	level;
	/** 行政代码 */
	private String	areaCode;
	/** 邮政编码 */
	private Integer	zipCode;
	/** 区号 */
	private String	cityCode;
	/** 名称 */
	private String	name;
	/** 简称 */
	private String	shortName;
	/** 组合名 */
	private String	mergerName;
	/** 拼音 */
	private String	pinyin;
	/** 经度 */
	private Double	lng;
	/** 纬度 */
	private Double	lat;
}