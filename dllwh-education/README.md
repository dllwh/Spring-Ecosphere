# 在线教育系统

## 项目介绍

## 项目组织架构

| 项目编号  | 模块 | 说明 |
| --- | --- | --- 
| dllwh-education-auth        | 服务鉴权	  |基于SpringSecurity进行安全认证，采用OAuth2.0认证体系，<br/>对客户端、用户进行认证及授权，支持账号密码登录，短信验证码登录
| dllwh-education-config      | 配置服务 	  |基于Spring Cloud构建统一配置服务，负责管理所有服务的配置文件 
| dllwh-education-devTools    | 开发工具 	  |
| dllwh-education-gatewar     | 网关服务 	  |构建服务网关
| dllwh-education-registry    | 服务注册 	  |基于Euraka构建服务注册中心，负责服务注册于发现
| dllwh-education-ops         | 运维中心 	  |
| dllwh-education-service     | 业务模块   	|
| dllwh-education-service-api | 业务模块API |

## 技术框架

### 功能介绍
