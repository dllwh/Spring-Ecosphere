package org.dllwh.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Blog implements Serializable {

	private static final long serialVersionUID = -2211369888889599947L;

	@ApiModelProperty(value = "博客id")
	private Integer id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "文章链接")
	private String link;

	@ApiModelProperty(value = "博客发表时间")
	private Date date;

	@ApiModelProperty(value = "作者")
	private String author;

	@ApiModelProperty(value = "是否展示：1展示  0隐藏")
	private Integer tag;
}
