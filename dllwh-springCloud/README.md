dllwh-springCloud 组织架构说明
---

> 即使你是天才，如果你不努力，你也会被其它人超越。

| 项目编号  | 模块 | 名称 | 说明 |
| --- | --- | --- | --- |
| dllwh-cloud-auth  | auth-service |服务鉴权| 基于SpringSecurity进行安全认证，采用OAuth2.0认证体系，<br/>对客户端、用户进行认证及授权，支持账号密码登录，短信验证码登录 |
| dllwh-cloud-config  | config-service | 配置服务 | 基于Spring Cloud构建统一配置服务，负责管理所有服务的配置文件 |
| dllwh-cloud-devTools  | devTools | 开发工具 |  |
| dllwh-cloud-gatewar  | gateway | 网关服务 | 构建服务网关 |
| dllwh-cloud-monitor  | monitor | 监控服务 | 对应用状态进行监控，对服务调用进行追踪 |
| dllwh-cloud-registry  | registy | 服务注册 | 基于Euraka构建服务注册中心，负责服务注册于发现 |
| dllwh-cloud-modules | message| 消息中心 | 公共基础通知服务，支持系统消息、短信、邮件、推送通知 |
| dllwh-cloud-modules  | common-service | 基础服务 |  |
| dllwh-cloud-modules  | upm-service | 权限中心 | 提供角色、资源、授权服务 |
| dllwh-cloud-modules  | uc-service | 用户服务 | 用户中心 |
| dllwh-cloud-modules  | notice-service |  通知中心 | 基于RabbitMQ异步通知发送短信、邮件、WebSocket消息  |
| dllwh-cloud-service  | sysLog  | 日志管理  |   |
| dllwh-cloud-service  | sysDict | 数据字典  |   |


Spring Cloud 学习路线
---

<table border="1" style="border-collapse: collapse;width: 100%;">
	<tr>
		<th colspan="3"></th>
		<th>说明</th>
	</tr>
	<tr>
		<td colspan="3">spring cloud eureka server</td>
		<td style="text-align: center;">注册中心</td>
	</tr>
	<tr>
		<td colspan="3">spring cloud eureka client</td>
		<td style="text-align: center;">客户端</td>
	</tr>
	<tr>
		<td rowspan="3">spring cloud feign</td>
		<td colspan="2"></td>
		<td style="text-align: center;">声明式调用</td>
	</tr>
	<tr>
		<td colspan="2">HTTP client</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="2">OK HTTP</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="3">spring cloud rabbit</td>
		<td style="text-align: center;">负载均衡</td>
	</tr>
	<tr>
		<td colspan="3">spring cloud zuul </td>
		<td style="text-align: center;">路由网关</td>
	</tr>
	<tr>
		<td colspan="3">spring cloud hystrix</td>
		<td style="text-align: center;">熔断器</td>
	</tr>
	<tr>
		<td colspan="3">spring cloud hystrix dashboard</td>
		<td style="text-align: center;">熔断器监控</td>
	</tr>
	<tr>
		<td colspan="3">spring cloud config </td>
		<td style="text-align: center;">配置中心</td>
	</tr>
	<tr>
		<td colspan="4"></td>
	</tr>
	<tr>
		<td colspan="3">spring cloud turbine</td>
		<td style="text-align: center;">聚合监控</td>
	</tr>
	<tr>
		<td rowspan="7">spring cloud sleuth</td>
		<td colspan="3" style="text-align: center;">服务链路追踪</td>
	</tr>
	<tr>
		<td>zipkin</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td rowspan="2"></td>
		<td>mysql</td>
		<td></td>
	</tr>
	<tr>
		<td>elastic search/kibana</td>
		<td></td>
	</tr>
	<tr>
		<td>rabbitmq</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td rowspan="2"></td>
		<td>mysql</td>
		<td></td>
	</tr>
	<tr>
		<td>elastic search/kibana</td>
		<td></td>
	</tr>
	<tr>
		<td rowspan="3">spring boot admin</td>
		<td colspan="3" style="text-align: center;">微服务监控--集中管理</td>
	</tr>
	<tr>
		<td>turbine</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>security/login</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>spring boot security </td>
		<td colspan="3" style="text-align: center;">安全组件</td>
	</tr>
	<tr>
		<td rowspan="2">spring cloud oath2 </td>
		<td colspan="3" style="text-align: center;">授权协议 结合security使用</td>
	</tr>
	<tr>
		<td>jwt</td>
		<td colspan="2" style="text-align: center;">开放标准 结合oath2使用</td>
	</tr>
</table>
		
[初识Spring Cloud](./SpringCloud.md)
---

版本控制
---

# SpringBoot 
```
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.0.5.RELEASE</version>
  <relativePath/>
</parent>
```

# SpringCloud 

```
<spring-cloud.version>Finchley.RELEASE</spring-cloud.version>
```
