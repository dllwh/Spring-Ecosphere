# SpringBoot2-chapter15-task(尚在学习中，还未开始)

基于springBoot 2.x + quartz 的CRUD任务管理系统。

> 即使你是天才，如果你不努力，你也会被其它人超越。

## 开发环境

JDK1.8、Maven、Eclipse

## 技术栈

SpringBoot 2.0.5、thymeleaf 3.0.9、quartz 2.3.0、vue、layer、bootstrap

## 启动说明
+ server.port = 8215
+ server.servlet.context-path=/task

## 定时器 Quartz

Quartz 设计有三个核心类，分别是

- Scheduler 调度器
    - 调度器就相当于一个容器，装载着任务和触发器。该类是一个接口，代表一个 Quartz 的独立运行容器， Trigger 和 JobDetail 可以注册到 Scheduler 中， 两者在 Scheduler 中拥有各自的组及名称， 组及名称是 Scheduler 查找定位容器中某一对象的依据， Trigger 的组及名称必须唯一， JobDetail 的组和名称也必须唯一（但可以和 Trigger 的组和名称相同，因为它们是不同类型的）。Scheduler 定义了多个接口方法， 允许外部通过组及名称访问和控制容器中 Trigger 和 JobDetail
- Job任务
    - 定义需要执行的任务。该类是一个接口，只定义一个方法 execute(JobExecutionContext context)，在实现类的 execute 方法中编写所需要定时执行的 Job（任务）， JobExecutionContext 类提供了调度应用的一些信息。Job 运行时的信息保存在 JobDataMap 实例中
- Trigger 触发器
    - 负责设置调度策略。该类是一个接口，描述触发 job 执行的时间触发规则。主要有 SimpleTrigger 和 CronTrigger 这两个子类。当且仅当需调度一次或者以固定时间间隔周期执行调度，SimpleTrigger 是最适合的选择；而 CronTrigger 则可以通过 Cron 表达式定义出各种复杂时间规则的调度方案：如工作日周一到周五的 15：00~16：00 执行调度等

## 功能
- [x] 任务列表
- [ ] 任务新增
- [ ] 任务修改
- [ ] 任务执行
- [x] 表达式生成器(集成：https://gitee.com/finira/cronboot)
- [ ] 任务移除
- [ ] 任务开启
- [ ] 任务停止
- [ ] 任务列表搜索以及分页