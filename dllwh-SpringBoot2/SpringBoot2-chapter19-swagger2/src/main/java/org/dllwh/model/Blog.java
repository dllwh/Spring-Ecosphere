package org.dllwh.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Blog") // 加入@ApiModel
public class Blog implements Serializable {

	private static final long serialVersionUID = 3963830283625630615L;
	@ApiModelProperty(value = "博客id", dataType = "Integer", required = true, example = "1020332806740959233")
	private Integer           id;
	@ApiModelProperty(value = "标题", dataType = "String", example = "001")
	private String            title;
	@ApiModelProperty(value = "文章链接", dataType = "String", example = "001")
	private String            link;
	@ApiModelProperty(value = "博客发表时间", dataType = "java.util.Date", example = "")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date              date;
	@ApiModelProperty(value = "作者", dataType = "String", example = "001")
	private String            author;
	@ApiModelProperty(value = "是否展示：1展示  0隐藏", dataType = "Integer", example = "001")
	private Integer           tag;
}