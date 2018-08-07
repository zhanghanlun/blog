# 日期相关
查看时间：
```java
[root@VM_0_11_centos ~]# date
Mon Aug  6 19:11:09 CST 2018
```
# 目录相关

## cd命令
```
//返回上一级
cd ..
//返回用户目录
cd ~
//打开相对路径、绝对路径
cd dir
```
## 查看当前目录 pwd
```java
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

```java
less filena
```

**q退出**

# 操作文件和目录

## 创建目录

```java
mkdir dirname
```
## 复制文件和命令

```java
//将item1复制到item2
cp item1 item2
```
