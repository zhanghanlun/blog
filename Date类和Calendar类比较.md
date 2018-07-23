# 前言
目前Java版本的Date类中已经逐步抛弃getDate(）、getDay()的方法了，取而代之的是Calendar类的get()方法。所以在这边对Date类和Calendar类做一个总结。
# Date类
这个类的来源非常的古老，从JDK1.0就有了，但是因为后面不支持国际化，Java逐渐向Calendar类倾斜了。
先来谈一下Date类的使用
## 获取当前的时间
```java
Date date = new Date();
System.out.println(date);
```
结果为：
```java
Thu Jun 28 19:24:32 CST 2018
```
## 获取分钟、小时、天、月、年等信息
这类方法现在已经被JDK所放弃了，但考虑但老业务，还是继续提供接口的，不推荐大家用。
```java
		Date date = new Date();
        System.out.println(date);
        // 年
        System.out.println(date.getYear() + 1900);
        // 月
        System.out.println(date.getMonth() + 1);
        // 日
        System.out.println(date.getDate());
        // 小时
        System.out.println(date.getHours());
        //分钟
        System.out.println(date.getMinutes());
        // 秒
        System.out.println(date.getSeconds());
        // 时间戳
        System.out.println(date.getTime());
```
结果为
```java
Thu Jun 28 19:47:03 CST 2018
2018
6
28
19
47
3
1530186423092
```
# Date类的格式化
我们经常用的格式是"yyyy-MM-dd HH:mm:ss"而Date类该怎么转呢
```java
	Date date = new Date();
	System.out.println(date);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date类型转换成 yyyy-MM-dd HH:mm:ss类型
	System.out.println(sdf.format(date));
        try {
		//yyyy-MM-dd HH:mm:ss转换成Date类型
            System.out.println(sdf.parse("2018-06-28 19:55:42"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
```
结果为：
```java
Thu Jun 28 19:58:46 CST 2018
2018-06-28 19:58:46
Thu Jun 28 19:55:42 CST 2018
```
 
# Calendar类
```java
	Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        //年
        System.out.println(calendar.get(Calendar.YEAR));
        //月
        System.out.println(calendar.get(Calendar.MONTH));
        //日
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //时
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        //分
        System.out.println(calendar.get(Calendar.MINUTE));
        //秒
        System.out.println(calendar.get(Calendar.SECOND));
```

结果输出为：
```java
2018-07-23 15:28:55
2018
6
23
15
28
55
```