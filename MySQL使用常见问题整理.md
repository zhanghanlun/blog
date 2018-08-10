# 使用INSERT INTO ... SELECT中null值插入问题

如果旧表中有null值，而新表不允许null值，这个问题就显现出来了。
根据MySQL的官方文档，在INSERT INTO中是改变不了什么的只能在select语句中改变了。
先看SQL语句：

```sql
INSERT INTO st (id,name,source,date) SELECT id,ifnull(name,''),source,ifnull(date,now()) FROM st;
```
表st有四个字段

| id | name | source | date |
| --- | --- | --- | --- |
| not null | not null | not null | not null |

表st1中的数据

| id | name | source | date |
| --- | --- | --- | --- |
| 1 | null | 2 |  null |

执行该SQL语句后表st中的值为

| id | name | source | date |
| --- | --- | --- | --- |
| 1 | | 2 |2018-07-18 14:33:16 |


# 创建表的SQL语句

废话少说直接上SQL代码：

```sql
CREATE TABLE `t_topic_share` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`topic_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '主题帖ID',
	`share_count` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '主题帖分享次数',
	`add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `uk_topic_id` (`topic_id`)
)
COMMENT='主题帖分享表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
```

关于创建表的部分主要代码包括：
>* 字段名称（id，topic_id)
>* 字段类型 BIGINT、datetime
>* NOT NULL 
>* 默认值 
>* COMMENT 
>* 索引

**索引**
索引的话包括三种：
>* PRIMARY KEY 主键索引
>* UNIQUE INDEX `uk_topic_id` (`topic_id`) 唯一值索引
>* INDEX 索引
