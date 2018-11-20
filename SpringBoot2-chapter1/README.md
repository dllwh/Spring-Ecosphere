## 代码层的结构


`Spring Boot框架对工程结构并没有什么特殊的限制,只是良好的工程结构划分可以使项目更清晰,明确,减少不必要的冲突,提高代码的统一性.`


根目录：com.cdeledu

* 1、工程启动类(ApplicationServer.java)置于com.cdeledu包下

`通常我们会在应用主类中做一些框架配置扫描等配置，我们放在root package下可以帮助程序减少手工配置来加载到我们希望被Spring加载的内容`

* 2、实体类(domain)置于com.cdeledu.domain或者 com.cdeledu.domain

* 3、数据访问层(Dao)置于com.cdeledu.repository 或者 com.cdeledu.dao

* 4、数据服务层(Service)置于com.cdeledu.service,数据服务的实现接口(serviceImpl)至于com.cdeledu.service.impl

* 5、前端控制器(Controller)置于com.cdeledu.controller

* 6、工具类(utils)置于com.cdeledu.utils

* 7、常量接口类(constant)置于com.cdeledu.constant

* 8、配置信息类(config)置于com.cdeledu.core.config