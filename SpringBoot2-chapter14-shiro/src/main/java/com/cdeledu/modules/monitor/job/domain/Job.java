package com.cdeledu.modules.monitor.job.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务调度信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:15:42
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
@ApiModel
public class Job {
	@ApiModelProperty(value = "任务ID", name = "id", example = "1")
	private String	jobId;
	@ApiModelProperty(value = "任务名称", name = "jobName")
	private String	jobName;
	@ApiModelProperty(value = "任务分组", name = "jobGroup")
	private String	jobGroup;
	@ApiModelProperty(value = "任务描述", name = "description")
	private String	description;
	@ApiModelProperty(value = "执行类", name = "methodName")
	private String	methodName;
	@ApiModelProperty(value = "执行表达式", name = "cronExpression")
	private String	cronExpression;
	@ApiModelProperty(value = "执行时间", name = "triggerName")
	private String	triggerName;
	@ApiModelProperty(value = "任务状态", name = "triggerState", example = "1")
	private Integer	triggerState;
}