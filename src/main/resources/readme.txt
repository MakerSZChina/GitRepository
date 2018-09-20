架构实现目标：
1、基本spring boot组件：devtools、lombok、Configuration Processor、session、cache、validation、Aspects
2、插件：redis、kafka、rabbitMq、activeMq
3、数据源使用druid动态数据源配置+mysql
4、持久层框架:mybatis+mybatis-plus+pagehelper实现动态映射
5、session、cache、validation、Aspects实现架构web请求优化处理
6、shiro、security实现访问控制
7、整合dubbo、spring cloud实现SOA微服务治理

构建过程：
1、添加mybatis配置，使用druid数据源配置
2、添加自定义注解，构建动态多数据源
3、添加mybatis-plus配置
4、添加mybatis-pagehelper配置

spring boot组件使用：
1、lombok配置 @Data
2、Aspects配置 @Aspects
3、Configuration Processor配置 @configurationprocessor
4、session配置 @EnableRedisHttpSession
5、spring exception统一处理 配置@RestControllerAdvice @ExceptionHandler
6、spring validation统一校验 @validated
7、swagger2.0
8、spring cloud 有创建新工程
9、dubbo

预留问题：
shiro、security实现访问控制
kafka、rabbitMq、activeMq使用spring stream访问
hibernate
