---
title: 使用Mybatis-generator自动生成Mybatis代码
date: 2018-08-22 17:24:45
tags:
	- Mybatis
	- Java
categories:
	- Java
	- Mybatis
---
# 1.前言
在我们使用Mybatis的时候，我们想要建立数据库中表的Mybatis基本的增删改查，如果表十分复杂，我们如果手写的话会十分的繁琐，这里调用了Mybatis的自动生成工具来自动生成Mybatis的代码，生成之后，我们在做自己的一些修改就可以用了。
<!-- more -->
基本的两个步骤是：

>* 配置pom.xml中添加<plugin>
>* 在resource目录下添加generatorConfig.xml文件

# 2.配置pom.xml文件

```java
		<plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.5</version>
                <configuration>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.2</version>
                    </dependency>
                </dependencies>
            </plugin>
```

# 3.写generatorConfig.xml文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
	<!-- 此处写MySQL-connector的具体路径-->
    <classPathEntry
            location="E:\repo\mysql\mysql-connector-java\5.1.11\mysql-connector-java-5.1.11.jar"/>
    <context id="my" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>   
		<!-- 配置数据库的信息 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/zhanghanlun" userId="root"
                        password="***"/>
			
			
        <!-- 实体类的路径配置 -->
        <javaModelGenerator targetPackage="com.zhanghanlun.entity"
                            targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        
		<!-- Mapper.xml路径配置-->
         <sqlMapGenerator targetPackage="mybatis.mapper"
                         targetProject="src/main/resources/META-INF">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

		<!-- Mapper接口配置 -->
        <javaClientGenerator targetPackage="com.zhanghanlun.mapper"
                             targetProject="src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator> 
               
		<!-- 其中tableName是表的名字，domainObjectName是实体类的名字 -->
        <table tableName="table_example" domainObjectName="TabelExample"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false">
            <columnRenamingRule searchString="^D_"
                                replaceString=""/>
        </table>    
                   
    </context>
</generatorConfiguration>
```

# 4.运行maven生成代码

![这里写图片描述](https://img-blog.csdn.net/2018082219474473?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW5naGFubHVu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)