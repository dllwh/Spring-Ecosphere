# SpringBoot2-chapter14-shiro

> 即使你是天才，如果你不努力，你也会被其它人超越。

## 项目说明

- 采用SpringBoot、MyBatis、Shiro框架基于RBAC发的一套权限系统

**数据权限设计思想** 

- 管理员管理、角色管理
- 菜单管理、定时任务、参数管理、字典管理、系统日志
- 业务功能，按照用户数据权限，查询、操作数据

**项目结构** 

```
├── src/main/java
│   ├── org.dllwh
│      ├── common// 公共模块
│          ├── constants                   // 通用常量
│          ├── exception                   // 通用异常
│          ├── util                        // 通用工具
│          ├── BaseEntity.java             // 基础pojo类
│          ├── RestResult.java             // 返回结果
│      ├── framework                       // 项目
│          ├── aspectj						// 注解实现
│          ├── config						// 系统配置
│          ├── controller					// 基础controller
│          ├── exception					// 全局异常配置
│          ├── factory						// 工厂
│          ├── model						// 日志
│          ├── mongoDB						// MongoDB
│          ├── queue						// 队列(生产者-消费者模式)
│          ├── scheduled					// 调整中心
│          ├── shiro						// 权限控制
│      ├── modules                         // 功能模块
│         ├── monitor
│         ├── system
│         ├── 
│      ├── ShiroSpringBootApplication
├── src/main/resource
│   ├── static                              // 静态资源
│   ├── webviews                            // 系统页面
│   ├── error
│      ├── include
│      ├── monitor
│          ├── job                         // 任务调度(定时任务)
│          ├── log                         // 系统日志
│      ├── system
│          ├── dict                        // 字典管理模块
│          ├── sysMenu                     // 菜单模块
│          ├── sysRole                     // 角色模块
│          ├── sysUser                     // 管理员模块
│      ├── index.html
│      ├── kickout.html
│   ├── application.yml                   //全局配置文件

```


## 开发环境

JDK1.8、Maven、Eclipse、SpringBoot2.0.5

### 技术栈
#### 后端

名称 | 描述 | 官网
---|--- |---
Spring Framework | 容器	| http://projects.spring.io/spring-framework/
Spring Boot 2.0 | 核心框架  | http://spring.io/projects/spring-boot#learn
SpringMVC | MVC框架	| http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc
MyBatis | ORM框架 |  	http://www.mybatis.org/mybatis-3/zh/index.html
MyBatis Generator | 代码生成 | http://www.mybatis.org/generator/index.html
Apache Shiro | 安全框架 | http://shiro.apache.org/
Maven | 项目构建管理 | http://maven.apache.org/
MySQL | 数据库 | https://www.mysql.com/
Tomcat 8.0 | 服务器 | http://tomcat.apache.org/

#### 前端

名称 | 描述 | 官网
---|--- |---
jQuery | 函数库 | http://jquery.com/
Bootstrap | 前端框架 | 	http://getbootstrap.com/
layui | 前端框架 | http://www.layui.com/
Bootstrap-table | 数据表格 | http://bootstrap-table.wenzhixin.net.cn/
layui | 弹出层 | http://layer.layui.com/
echarts | 图表 | http://echarts.baidu.com/
