---
title: Linux常用命令总结
date: 2019-08-18 17:24:45
tags:
	- Linux
categories: Linux
---

# 日期相关
查看时间：
```shell
[root@VM_0_11_centos ~]# date
Mon Aug  6 19:11:09 CST 2018
```
<!-- more -->
# 目录相关

## cd命令
```shell
//返回上一级
$ cd ..
//返回用户目录
$ cd ~
//打开相对路径、绝对路径
$ cd dir
```
## 查看当前目录 pwd
```shell
[root@VM_0_11_centos zhanghanlun]# pwd
/root/zhanghanlun
```
## ls列出目录内容

ls选项

| 选项 | 解释 |
| --- | --- |
| -a | 列出所有文件 |
| -d | 目录的详细信息 |
| -F | 目录名会加 ' / ' |
| -h | 可读格式显示大小 |
| -l | 长格式输出 |
| -r | 相反顺序显示结果 |
| -s | 按照文件大小来排序 |
| -t | 按照修改时间来排序 |

## less命令看文本文件

```shell
$ less filena
```

**q退出**

# 操作文件和目录

## mkdir 创建目录

```shell
$ mkdir dirname
```
## cp 复制文件和命令

```shell
//将item1复制到item2
$ cp item1 item2
```

**cp选项**

| 选项 | 意义 |
| --- | --- |
| -a | 复制文件和目录 |
| -i | 重写文件提示 |
| -r | 递归复制（复制文件夹） |
| -u | 仅复制不存在的文件 |
| -v | 显示详细的信息 |

## mv 移动和重命名文件

```shell
//将item1移动为item2，或者将item1重命名为item2
$ mv item1 item2
```

**mv 选项**

| 选项 | 意义 |
| --- | --- |
| -i | 重写提示 |
| -u | 当把文件从一个目录移动另一个目录时，只是移动不存在的文件|
| -v |详细信息 |

## rm 删除文件和目录

```shell
//删除文件
$ rm filename
```

**rm选项**

| 选项 | 意义 |
| --- | --- |
| -i | 提示信息 |
| -r | 递归删除文件（删除文件夹必备）|
| -f | 不显示提示信息|
| -v | 显示详细信息 |

## 通配符

|通配符 | 意义|
| --- | --- |
| * | 多个字符，包含0个，1个 |
| ？| 特指一个字符 |
| [   ] | 一个属于字符集的元素 |
| [!   ] | 一个不属于特定字符集的元素 |
| [[:class:]] | 指定字符集中的字符 |

# 查询目录所占的空间大小
```shell
$ du -sh *
```

# Linux连接mysql
```shell
mysql -h域名或者ip -P端口号 -u用户名 -p密码
$ mysql -hlocalhost -P3306 -uroot -p123456
```