# 使用INSERT INTO ... SELECT中null值插入问题

如果旧表中有null值，而新表不允许null值，这个问题就显现出来了。
根据MySQL的官方文档，在INSERT INTO中是改变不了什么的只能在select语句中改变了。
先看SQL语句：

```sql
INSERT INTO st (id,name,source,date) SELECT id,ifnull(name,''),source,ifnull(date,now()) FROM st;
```
表st有四个字段
| id | name | source | date |
| ---| --- | --- | --- |
| not null | not null | not null | not null |

表st1中的数据

| id | name | source | date |
| --- | --- | --- | --- |
| 1 | null | 2 |  null |

执行该SQL语句后表st中的值为

| id | name | source | date |
| --- | --- | --- | --- |
| 1 | | 2 |2018-07-18 14:33:16 |
