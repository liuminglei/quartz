# quartz

#### 项目简介
1. 基于quartz的二次集成
2. 支持集群
3. 支持其它web项目进行功能开发
4. 动态控制定时任务启动、暂停、重启、删除、添加、修改
5. 支持多数据库
6. 支持自实现Scheduler、Job、Trigger监听，系统自动注册
7. 数据源使用阿里Druid

#### 使用教程

1. 引入依赖
2. 修改jdbc.properties数据源配置
3. 实现QuartzTask
4. 功能开发
5. 任务展示
6. 调用接口控制任务

#### 使用说明

1. QuartzUtil 任务获取，其中有获取所有任务列表功能
2. TaskService 任务服务接口，其中有添加、修改、删除、暂停、重启等功能
3. AbstractSchedulerListener Scheduler监听，可自行实现自己需要的Scheduler监听
4. AbstractJobListener Job监听，可自行实现自己需要的Job监听
5. AbstractTriggerListener Trigger监听，可自行实现自己需要的Trigger监听

#### 版权说明
quartz使用 [Apache License 2.0](https://gitee.com/xbd521/quartz/blob/master/LICENSE "Apache License 2.0") 协议



