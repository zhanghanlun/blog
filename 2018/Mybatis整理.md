# ORM概念和原理
ORM(Object Relational Mapping)对象关系映射。就是数据库的表和简单Java对象的映射模型。可以将查询数据库的结果和POJO对象之间相互映射。
# Mybatis 介绍
Mybatis是一个半自动框架。为我们在Java进行SQL操作中提供了POJO和映射关系，以及动态SQL的功能，这个会在执行MySQL数据库的时候回很方便。
# Mybatis 配置
## mybatis-config.xml 配置文件
常用的Mybatis的XML文件的代码以及注释如下：
```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration><!--配置 -->
    <properties resource="jdbc.properties"> <!--属性-->
    </properties>
    <settings>
        <setting name="cacheEnabled" value="true" /> <!-- 缓存是否开启 -->
        <setting name="lazyLoadingEnabled" value="true" /><!-- 延迟加载开关 -->
        <setting name="multipleResultSetsEnabled" value="true" />
        <setting name="useColumnLabel" value="true" />  <!-- 使用列标签代替列名-->
        <setting name="useGeneratedKeys" value="false" /> <!-- 是否自动生成主键 -->
        <setting name="autoMappingBehavior" value="PARTIAL" />  <!-- 如何自动映射结果集 -->
        <setting name="defaultExecutorType" value="SIMPLE" /> <!-- 默认的执行器 -->
        <setting name="defaultStatementTimeout" value="25" /> <!-- 超时时间，单位：秒 -->
        <setting name="safeRowBoundsEnabled" value="false" /> <!-- 是否允许在嵌套语句中使用分页 -->
        <setting name="mapUnderscoreToCamelCase" value="false" /><!-- 是否开启自动驼峰命名规则 -->
        <setting name="localCacheScope" value="SESSION" /> <!-- 本地缓存机制 -->
        <setting name="jdbcTypeForNull" value="OTHER" /> <!-- 指定JDBC类型 -->
        <!-- 指定对象的方法触发延迟加载 -->
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
    </settings>
    <!-- 别名 -->
    <typeAliases>
        <typeAlias alias="user" type="com.zhanghanlun.domain.User"/>
    </typeAliases>
    <environments default="test"><!-- 配置环境 可以配置多个数据源-->
        <environment id="test">
            <!-- 事务管理配置 一般采用JDBC -->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 数据源 -->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!-- 映射器的路径 -->
    <mappers>
        <mapper resource="META-INF/mybatis/mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

## Mapper.xml映射文件
### select元素
二话不说先来代码：
```java
    <resultMap id="userResultMap" type="com.zhanghanlun.domain.User">
        <id property="id" column="id"/>
        <result property="userName" column="user_name"/>
        <result property="sex" column="sex"/>
    </resultMap>
    <select id="getUser" parameterType="int" resultMap="userResultMap">
        SELECT id,user_name,sex FROM user WHERE id = #{id}
    </select>
```
在select元素中，我们常用的是id，resultType,resultMap。

| 参数 | 解释 |
| --- | --- |
| id | 在命名空间中唯一的标识符，可以被用来引用这条语句。|
| parameterType | 将会传入这条语句的参数类的完全限定名或别名。（**可以是类，Map，单个参数**）这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。|
| resultType |	从这条语句中返回的期望类型的类的完全限定名或别名。注意如果是集合情形，那应该是集合可以包含的类型，而不能是集合本身。使用 resultType 或 resultMap，但不能同时使用。
| resultMap |	外部 resultMap 的命名引用。结果集的映射是 MyBatis 最强大的特性，对其有一个很好的理解的话，许多复杂映射的情形都能迎刃而解。使用 resultMap 或 resultType，但不能同时使用。|

### insert元素
Mybatis在执行insert操作后会返回一个整数，表示进行插入操作后插入的记录数。
```java
<insert id="insertUser" parameterType="user" keyProperty="id" useGeneratedKeys="true">
        INSERT INTO user (user_name, sex) VALUES (#{userName}, #{sex})
</insert>
```
其中重点讨论一下一个属性

| 属性 | 解释 |
| --- | --- |
| useGeneratedKeys | 是否使用Mybatis的主键回填功能 |

### update和delete元素
```java
    <update id="updateUser" parameterType="com.zhanghanlun.domain.User">
        UPDATE user SET
        userName = #{userName},
        sex = #{sex}
        WHERE id = #{id}
    </update>
```
<<<<<<< HEAD
这两个元素中主要的还是parameterType和id为主要的属性。上面都介绍过了，就不一一介绍了。
### ResultMap解释
这是映射器中最重要也是比较难理解的部分。
=======

### ResultMap解释
>>>>>>> e33bb7354959b53b02d689f0b4d00dccd97b2c22
