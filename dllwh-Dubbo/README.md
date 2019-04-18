# dllwh-Dubbo

> [初始 Dubbo](./Dubbo.md)
 
> [zookeeper注册服务中心](./Zookeeper.md)

> [服务治理要先于SOA](./SOA.md)

- 项目目录
	- dllwh-dubbo-chapter01-registry
        - 服务注册，我们一般会采取 Zookeeper 作为我们的注册中心
	- dllwh-dubbo-chapter01-provider
        - 服务提供者（生产者），提供具体的服务实现
	- dllwh-dubbo-chapter01-consumer
        - 消费者，从注册中心中订阅服务
	- dllwh-dubbo-chapter01-monitor
        - 监控中心，RPC调用次数和调用时间监控