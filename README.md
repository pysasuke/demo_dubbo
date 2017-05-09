# demo_dubbo demo项目分布式版本

## 项目简介

该项目为demo项目的分布式拆分结构

## 内置项目

1.	demo_dubbo_api：model类和service接口类。
2.	demo_dubbo_common：常量类的公用模块。
3.	demo_dubbo_customer：消费者,controller层、拦截器、前段页面(处理前后端交互部分)。
4.	demo_dubbo_provider：接口实现类(接口实现、业务处理)。
5.	demo_dubbo_util：工具类。



## 技术选型

1、后端

* 核心框架：Spring 4.3.2
* 视图框架：Spring MVC 4.1、springframework
* 持久层框架：MyBatis 3.2.5
* 数据库连接池：Alibaba Druid 1.0.17
* 缓存框架：Redis
* 日志管理：slf4j 1.7.18、log4j 1.2.17
* 工具类：Apache Commons、Jackson 2.4.4
* 分布式：dubbo 2.8.4、zookeeper 3.5.1


4、平台

* 服务器中间件：在jdk1.8规范下开发，使用应用服务器为Tomcat 7。

* 开发环境：Java、IDEA 2017、Maven 3.9、Git
