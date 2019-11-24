package org.dllwh.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "crawler_china_area") // 指定表名
public class ChinaAreaEntity {
	// value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
	@TableId(value = "id", type = IdType.AUTO) // 指定自增策略
	private Integer id;
	/** 父级ID */
	// 若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
	@TableField(value = "parentId", exist = true)
	private Integer parentId;
	/** 层级 */
	private Integer level;
	/** 行政代码 */
	@TableField(value = "areaCode", exist = true)
	private String areaCode;
	/** 邮政编码 */
	@TableField(value = "zipCode", exist = true)
	private Integer zipCode;
	/** 区号 */
	@TableField(value = "cityCode", exist = true)
	private String cityCode;
	/** 简称 */
	@TableField(value = "shortName", exist = true)
	private String shortName;
	/** 组合名 */
	@TableField(value = "mergerName", exist = true)
	private String mergerName;
	/** 拼音 */
	private String pinyin;
	/** 经度 */
	private Double lng;
	/** 纬度 */
	private Double lat;
}
