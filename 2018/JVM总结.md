---
title: JVM总结
date: 2018-04-02 16:12:11
tags: [Java,JVM]
---
# JVM知识总结
### 1. JVM内存分配
>* 程序计数器
>* 虚拟机栈
>* 本地方法栈
>* 堆
>* 方法区
>* 常量池

<!-- more -->

### 2. GC

#### 2.1 GC分代
>* 新生代
>* 老年代
>* 永久代
#### 2.2 收集算法
>* 复制算法
>* 标记-清除
>* 标记-整理
#### 2.3 GC收集器
>* Serial收集器
>* ParNew收集器
>* CMS收集器
>* G1收集器

### 3. 类加载

#### 3.1 类加载过程
>* 加载：
>* 验证：确保Class文件的字节流包含的信息符合当前虚拟机要求
>* 准备：分配内存变量，并设置类变量的初始值
>* 解析：符号引用替换成直接引用
>* 初始化：执行类构造器clint()方法

#### 3.2 双亲委派模型
&emsp;&emsp;双亲委派模型的基本描述是：如果一个类加载器收到类加载的请求，它首先不会尝试加载它，而是把请求传给父类加载器去完成，最终传到启动类加载器。当父类加载器反馈无法加载此类时，子类加载器才尝试自己去加载。
<img src="/images/doubleparent.jpg" width="50%" height="50%">

上述图片中有几个类加载器，具体如下：
1.启动类加载器：负责加载存放JAVA_HOME\lib目录中的类
2.扩展类加载器：负责加载存放JAVA_HOME\lib\etc目录中的类
3.系统类加载器：负责加载用户类路径上所指定的类库