---
title: git切换分支
date: 2018-08-18 17:24:45
tags:
	- git
categories: git
---

git切换分支
<!-- more -->
首先通过
```shell
$ git branch -a
```
来查看所在目录的分支
```shell
$ git branch -a
  master
* trunk
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
  remotes/origin/zhanghanlun
```
然后输入命令切换分支
```shell
$ git checkout -b zhanghanlun origin/zhanghanlun
```
切换到origin/zhanghanlun分支命令本地分支为"zhanghanlun"