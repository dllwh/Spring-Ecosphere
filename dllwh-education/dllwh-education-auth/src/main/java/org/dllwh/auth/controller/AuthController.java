package org.dllwh.auth.controller;

import org.dllwh.auth.core.secure.AuthInfo;
import org.dllwh.devTools.api.ReturnRest;
import org.dllwh.devTools.utils.FuncHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 认证模块
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月8日 下午10:49:34
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {
	@PostMapping("token")
	@ApiOperation(value = "获取认证token", notes = "传入业务编号:tenantCode,账号:account,密码:password")
	public ReturnRest<AuthInfo> token(
			@ApiParam(value = "租户编号", required = true) @RequestParam(defaultValue = "000000", required = false) String tenantCode,
			@ApiParam(value = "账号", required = true) @RequestParam String account,
			@ApiParam(value = "密码", required = true) @RequestParam String password) {
		if (FuncHelper.hasEmpty(account, password)) {
			return ReturnRest.fail("接口调用不合法");
		}
		AuthInfo authInfo = new AuthInfo();
		return ReturnRest.data(authInfo);
	}
}