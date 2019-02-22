dllwh-springCloud 组织架构说明
---

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

		
[初识Spring Cloud](./SpringCloud.md)
----------------------
