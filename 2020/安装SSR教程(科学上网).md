---
title: 安装SSR教程（科学上网）
date: 2020-02-15 15:28:45
tags:
	- SSR
	- 科学上网
categories: SSR
---

# 获取安装脚本
<!-- more -->
```shell
$ wget --no-check-certificate https://github.com/zhanghanlun/ssr/blob/master/shadowsocksR.sh
```
# 授予脚本权限
```shell
$ chmod +x shadowsocksR.sh
```
# 安装ssr
```shell
$ ./shadowsocksR.sh 2>&1 | tee shadowsocksR.log
```
# ssr常用命令

```shell
启动：/etc/init.d/shadowsocks start
停止：/etc/init.d/shadowsocks stop
重启：/etc/init.d/shadowsocks restart
状态：/etc/init.d/shadowsocks status
配置文件路径：/etc/shadowsocks.json
日志文件路径：/var/log/shadowsocks.log
代码安装目录：/usr/local/shadowsocks
```