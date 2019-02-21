package org.dllwh.module;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	private Integer	id;
	/** 文章标题 */
	private String	title;
	/** 作者或者发布人 */
	private String	author;
	/** 审核状态.（0：待审核；1：审核通过；-1，审核未通过） */
	private Integer	status;
	/** 浏览权限（0：开放浏览；1：会员浏览） */
	private Integer	lookType;
	/** 是否展示（0：否；1：是） */
	private Integer	ifShow;
	/** 发布时间 */
	private Date	createTime;
	/** 更新时间 */
	private Date	aupdateTime;
}
