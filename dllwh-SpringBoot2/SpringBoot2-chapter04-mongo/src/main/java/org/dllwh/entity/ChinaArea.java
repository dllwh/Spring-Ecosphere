package org.dllwh.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "crawler_china_area")
public class ChinaArea implements Serializable {
	private static final long serialVersionUID = 5475429552537209701L;
	@Field("id")
	private Integer           id;
	/** 父级ID */
	private Integer           parentId;
	/** 层级 */
	private Integer           level;
	/** 行政代码 */
	private String            areaCode;
	/** 邮政编码 */
	@Field("zipCode")
	private Integer           zipCode;
	/** 区号 */
	private String            cityCode;
	/** 名称 */
	private String            name;
	/** 简称 */
	private String            shortName;
	/** 组合名 */
	private String            mergerName;
	/** 拼音 */
	private String            pinyin;
	/** 经度 */
	@Field("lng")
	private Double            lng;
	/** 纬度 */
	@Field("lat")
	private Double            lat;
}