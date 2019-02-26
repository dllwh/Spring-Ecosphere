# 什么是Spring  Cloud

&emsp; &emsp;Spring 提供了一系列的工具，可以帮助开发人员迅速搭建分布式系统中的公共组件(比如：配置管理、服务发现、断路器、智能路由、微代理、控制总线、一次性令牌、全局锁、主节点选举、分布式session、集群状态等等)。协调分布式环境中的各个系统，为各类服务提供模板型配置。使用 Spring Cloud , 开发者可以快速的启动服务或构建应用，并且在任何分布式环境中都能很多的工作，小到笔记本电脑，大到数据中心和云平台。Spring Cloud 并不重复造轮子 ，而是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过SpringBoot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具，从而减少了各模块的开发成本。换句话说：Spring Cloud 提供了构建分布式系统所需的“全家桶”。

&emsp; &emsp;Spring Cloud是一个基于Spring Boot实现的微服务架构开发工具，相比 Dubbo 等RPC框架, Spring Cloud 提供的全套的微服务架构实施的综合性解决方案。 最重要的是，跟 spring boot 框架一起使用的话，会让你开发微服务架构的云服务非常好的方便。

&emsp; &emsp;Spring Cloud 不像其他Spring子项目那样相对独立，它是一个拥有诸多子项目的大型综合项目。Spring Cloud 可以说是微服务架构解决方案的综合套件组合， 其包含的子项目也都独立进行着内容更新与迭代，各自都维护着自己的发布版本号。
 
# Spring Cloud 子项目

![Spring Cloud的子项目](dllwh-image/Spring%20Cloud%E7%9A%84%E5%AD%90%E9%A1%B9%E7%9B%AE.png)

- **Spring Cloud Config**：配置管理开发工具包，支持使用Git存储配置内容，支持应用配置的外部化存储，支持客户端配置信息刷新、加解密配置内容等
- **Spring Cloud Bus**：事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。
- **Spring Cloud Netflix**：针对多种Netflix组件提供的开发工具包，其中包括Eureka、Hystrix、Zuul、Archaius等
    - **Netflix Eureka**：一个基于rest服务的服务治理组件，包括服务注册中心、服务注册与服务发现机制的实现，实现了云端负载均衡和中间层服务器的故障转移。
    - **Netflix Hystrix**：容错管理工具，实现断路器模式，通过控制服务的节点,从而对延迟和故障提供更强大的容错能力。
    - **Netflix Ribbon**：客户端负载均衡的服务调用组件。
    - **Netflix Feign**：基于Ribbon和Hystrix的声明式服务调用组件。
    - **Netflix Zuul**：微服务网关，提供动态路由，访问过滤等服务。
    - **Netflix Archaius**：配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。
- **Spring Cloud for Cloud Foundry**：通过Oauth2协议绑定服务到CloudFoundry，CloudFoundry是VMware推出的开源PaaS云平台。
- **Spring Cloud Sleuth**：日志收集工具包，封装了Dapper,Zipkin和HTrace操作。
- **Spring Cloud Data Flow**：大数据操作工具，通过命令行方式操作数据流。
- **Spring Cloud Security**：安全工具包，为你的应用程序添加安全控制，主要是指OAuth2。
- **Spring Cloud Consul**：封装了Consul操作，consul是一个服务发现与配置工具，与Docker容器可以无缝集成。
- **Spring Cloud Zookeeper**：操作Zookeeper的工具包，用于使用zookeeper方式的服务注册和发现。
- **Spring Cloud Stream**：数据流操作开发包，封装了与Redis,Rabbit、Kafka等发送接收消息。
- **Spring Cloud CLI**：基于 Spring Boot CLI，可以让你以命令行方式快速建立云组件。
