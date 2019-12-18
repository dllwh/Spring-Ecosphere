package org.dllwh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 * 
 * @Entity 注解在数据源配置的时候由spring自动进行扫描并创建相应的实体映射
 * @Table 注解用于标明与数据库对应的表名数据库
 * @Id 注解说明该属性作为该表的主键
 * @Column 注解说明该属性对应的列名
 */
@Entity
@Table(name = "sys_upms_user")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -7169286622518619612L;
	@Id // 说明该属性作为该表的主键
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer           id;
	@Column(name = "userName", nullable = false, unique = true)
	private String            userName;
	@Column(name = "password", nullable = false)
	private String            password;
	@Column(name = "creator", nullable = false)
	private Integer           creator;
	@Column(name = "createTime", nullable = false)
	private Date              createTime;
	@Column(name = "modifier", nullable = false)
	private Integer           modifier;
	@Column(name = "updateTime", nullable = false)
	private Date              updateTime;
	@Column(name = "remark")
	private String            remark;
}