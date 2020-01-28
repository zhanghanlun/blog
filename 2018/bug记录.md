# bug 日志
# 2018/06/26 
## 关于Maven中c3p0不能正确加载c3p0-config.xml文件中的配置信息
这个是在Maven中依赖的时候没有导入正确的版本号，所导致的，以后再pom.xml文件中加入依赖的时候，版本号必不可少。
```java
	<dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
```
# 2018/06/27
## 在Spring的xml文件中配置时出现“no declaration can be found for element 'context:component-scan”
确认在xml文件的开头加上
```java
http://www.springframework.org/schema/context
![enter description here][1]http://www.springframework.org/schema/context/spring-context.xsd
```

# 2018/06/29
## 在使用Mybatis插入或者更新数据的时候出现类似"??"的乱码

在jdbcurl中要插入“useUnicode=true&characterEncoding=utf8”
```java
url = jdbc:mysql://localhost:3306/zhanghanlun?useUnicode=true&characterEncoding=utf8
```