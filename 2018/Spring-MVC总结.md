---
title: Spring MVC总结
date: 2018-04-04 21:28:23
tags: [Java,Spring]
categories: Spring框架
---
# 1.MVC概述
<img src="/images/MVC.jpg" width="80%" height="80%">
<!-- more -->
&emsp;&emsp;根据上图我们清楚的知道
## 1.1. Model（模型）
&emsp;&emsp;它是业务处理层，负责最多的业务处理：
>* 对业务流程背后的数据和行为进行建模
>* 处理应用程序数据逻辑
>* 处理和数据库之间的交互

## 1.2. View（视图）
&emsp;&emsp;它是展示层，它主要负责：
>* 根据客户端的类型展示数据
>* 展示业务逻辑的结果
>* 是用户交互的界面

## 1.3. Controller（控制器）
&emsp;&emsp;它是控制器层，主要负责的业务为：
>* 将用户输入的数据传递给Model
>* 分发请求


# 2.SpringMVC 整体介绍
&emsp;&emsp;SpringMVC是Spring框架关于Web方面的应用。它基于经典的MVC模型来实现的。下图是描述SpringMVC的整体架构：
<img src="/images/SpringMVCFramework.jpg" width="80%" height="80%">
在上图中的过程可以这么描述：

>1. 一个HTTP请求由客户端传到DispatcherServlet.
>2. DispatcherServlet接受请求后，去HandlerMapping找对应的Handler
>3. DispatcherServlet根据HandlerMapping得到对应的当前请求的Handler后。通过HandlerAdapter对   Handler进行封装，然后以统一的接口调用Handler
>4. Handler处理完业务逻辑之后返回一个ModelAndView给DispatcherServlet
>5. ModelAndView中包含的**是逻辑视图名而不是真正的视图**，还需要借助ViewResolver完成逻辑视图名到真正视图的转换。
>6. 当DispatcherServlet得到真正的View后，DispatcherServlet根据这个View对ModelAndView中的模型数据进行视图渲染。

# 3.DispatcherServlet
&emsp;&emsp;DispatcherServlet是整个SpringMVC的核心，负责接收HTTP请求和协调SpringMVC的各个组件完成请求处理的工作。在web.xml中配置DispatcherServlet的信息。

DispatcherServlet通过在*< servlet-mapping >*中来指定处理的URL。
# 4. ModelAndView
ModelAndView中既包含视图信息又包含模型数据信息
数据模型可以看成一个Map< String,Object >对队形
在处理方法的时候可以使用下面的方法添加模型数据
```java
ModelAndView addObject(String attributeName,Object attributeValue)
ModelAndView addAlllObject(Map<String,?> modelMap)
```
如下的方法设置视图
```java
void setView(View view) //指定一个视图的对象
void setViewName(String viewName) //指定一个逻辑视图名

```
