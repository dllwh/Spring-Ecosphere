package org.dllwh.auth.core.secure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "认证信息")
public class AuthInfo {
	@ApiModelProperty(value = "令牌")
	private String	accessToken;
	@ApiModelProperty(value = "令牌类型")
	private String	tokenType;
	@ApiModelProperty(value = "角色名")
	private String	authority;
	@ApiModelProperty(value = "用户名")
	private String	userName;
	@ApiModelProperty(value = "账号名")
	private String	account;
	@ApiModelProperty(value = "过期时间")
	private long	expiresIn;
}