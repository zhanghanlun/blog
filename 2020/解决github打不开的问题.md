---
title: 解决github打不开的问题
date: 2020-02-08 17:24:45
tags:
	- github
categories: Github
---
# 前言
我们经常遇到github在电脑上打不开的问题，下面我们通过添加host来解决

<!-- more -->

# 解决
windows下路径为：C:\Windows\System32\drivers\etc\hosts
Linux下路径：/etc/hosts

```
192.30.253.113 github.com
192.30.253.118 gist.github.com
192.30.253.119 gist.github.com
```