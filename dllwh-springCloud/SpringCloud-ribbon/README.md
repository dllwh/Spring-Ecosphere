客户端负载均衡：Spring Cloud Ribbon
---
&emsp;&emsp;Spring Cloud Ribbin 是一个基于HTTP和TCP的客户端负载均衡的工具，它基于Netfilx Ribbon 实现。通过Spring Cloud等封装，可以让我们轻松的面向服务的TEST模板请求自动转换成客户端负载均衡的服务调用。

# 客户端负载均衡

&emsp;&emsp;负载均衡在系统架构中是一个非常重要，并且是不得不去实施的内容。因为负载均衡是系统的高可用、网络压力的缓解以及处理能力的扩容的重要手段之一。而我们通常所说的负载均衡其实都是指的是服务端的负载均衡，其中可以分为硬件负载均衡和软件负载均衡两种。其中，硬件负载均衡主要是通过服务器节点之间安装专门用于负载均衡的设备，例如F5等；而软件负载均衡则是通过在服务器上安装一些具有负载均衡功能或者模块的软件来完成负载均衡分发工作，例如nginx等。

# 与Eureka 结合

&emsp;&emsp;当在于Spring Cloud 的应用中同时引用Spring Cloud Ribbon 与Spring Cloud Eureka依赖时，就会触发Rureka中的对Ribbon的自动化配置。