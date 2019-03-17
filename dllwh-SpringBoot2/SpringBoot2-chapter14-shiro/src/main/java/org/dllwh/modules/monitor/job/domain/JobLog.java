package org.dllwh.modules.monitor.job.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class JobLog {
	@ApiModelProperty(value = "日志id", name = "id", example = "1")
	private Integer	id;
	@ApiModelProperty(value = "任务id", name = "jobId")
	private Integer	jobId;
	@ApiModelProperty(value = "任务状态    1：成功   0：失败", name = "status", example = "1")
	private Integer	status;
	@ApiModelProperty(value = "失败信息", name = "error")
	private String	error;
	@ApiModelProperty(value = "耗时(单位：毫秒)", name = "times", example = "1")
	private Integer	times;
	@ApiModelProperty(value = "创建时间", name = "createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date	createTime;
}
