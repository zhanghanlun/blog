---
title: GithubPage和Hexo搭建个人博客网站
date: 2020-02-09 12:24:45
tags:
	- github
	- Hexo
categories: GithubPage
---
# 前言
github支持githubPage静态界面来搭建我们的个人博客，自己配置。

<!-- more -->

# 创建github仓库
创建username.github.io仓库，同时setting中勾选githubpage
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200208202550128.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW5naGFubHVu,size_16,color_FFFFFF,t_70)
创建好后我们可以通过[zhanghanlun.github.io](https://zhanghanlun.github.io)来访问该博客
# 利用hexo配置博客
GithubPage支持Jelly和Hexo来搭建静态界面，本教程使用Hexo来搭建博客。
## 初始化hexo
本节截取自hexo的[官方文档](https://hexo.io/zh-cn/)
```shell
$ npm install hexo-cli -g
$ hexo init blog
$ cd blog
$ npm install
$ hexo server
```
## 生成和运行静态网页
通过如下命令来生成静态网页
```shell
$ hexo generate
```
通过如下命令来在本地运行静态网页
```shell
$ hexo deploy
```
第一次运行该命令，要先安装hexo-server
```shell
$ npm install hexo-server --save
```
安装完成后，输入以下命令以启动服务器，您的网站会在 http://localhost:4000 下启动

## 部署到Github
安装hexo部署插件hexo-deploy-git
```shell
$ npm install hexo-deployer-git --save
```
同时修改博客配置文件_config.yml
```
deploy:
  type: git # 部署方式git
  repo: git@github.com:zhanghanlun/zhanghanlun.github.io.git #远端仓库地址
  branch: master # 分支
```
最后通过如下命令部署到github
```shell
$ hexo deploy
```

# 配置next主题
## 添加next主题
本节参考next主题[官方文档](https://github.com/theme-next/hexo-theme-nex)
添加next主题
```shell
$ cd hexo
$ git clone https://github.com/theme-next/hexo-theme-next themes/next
```
修改博客配置文件_config.yml,修改主题为next
```
# Extensions
## Plugins: https://hexo.io/plugins/
## Themes: https://hexo.io/themes/
theme: next
```
## 修改next主题语言
在next主题最新版本中依赖博客的配置文件，而不是主题本身的配置文件的语言
```
title: 张函仑的技术博客
subtitle: ''
description: ''
keywords:
author: 张函仑
language: zh-CN
timezone: ''
```
## 配置标签和分类
通过修改主题配置文件_config.yml来添加菜单
```
menu:
  home: / || home # 主页
  about: /about/ || user # 关于页
  tags: /tags/ || tags # 标签
  categories: /categories/ || th # 分类
  archives: /archives/ || archive # 归档
```
运行命令
```shell
$ hexo new page categories
$ hexo new page tags
$ hexo new page about
```
## 添加文章评论和文章阅读次数
该功能通过[valine](https://valine.js.org)来实现，而valine是通过leancloud来实现单点
首先去[leanclode官网](https://leancloud.cn/)l注册账号然后在控制台创建一个免费的应用，找到appId和appKey。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020020908013578.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW5naGFubHVu,size_16,color_FFFFFF,t_70)
修改主题配置文件_config.yml
```
valine:
  enable: true
  appid: *** # Your leancloud application appid
  appkey: ***  # Your leancloud application appkey
  notify: true # Mail notifier
  verify: false # Verification code
  placeholder: Just go go # Comment box placeholder
  avatar: mm # Gravatar style
  guest_info: nick,mail,link # Custom comment header
  pageSize: 10 # Pagination size
  language: zh-cn # Language, available values: en, zh-cn
  visitor: true # 文章阅读数量统计
  comment_count: true # If false, comment count will only be displayed in post page, not in home page
  recordIP: false # Whether to record the commenter IP
  serverURLs: # When the custom domain name is enabled, fill it in here (it will be detected automatically by default, no need to fill in)
  #post_meta_order: 0
```

## 添加本地搜索
安装 hexo-generator-search
```shell
$  npm install hexo-generator-search --save
```
然后在hexo配置文件中_config.yml修改
```
search:
    path: search.xml
    field: post
    format: html
    limit: 10000
```
## 添加打赏功能
修改主题配置_config.yml,并添加收款图片到**theme/next/source/image**文件夹下
```
reward_settings:
  # If true, reward will be displayed in every article by default.
  enable: true
  animation: false
  comment: 原创技术分享，您的支持将鼓励我继续创作。

reward:
  wechatpay: /images/wechatpay.png
  alipay: /images/alipay.jpg
```
## 添加sitemap到谷歌和百度搜索
安装sitemap生成插件
```shell
$ npm install hexo-generator-sitemap --save
$ npm install hexo-generator-baidu-sitemap --save
```
添加hexo配置_config.yml
```
Plugins:
- hexo-generator-baidu-sitemap
- hexo-generator-sitemap

baidusitemap:
    path: baidusitemap.xml
sitemap:
    path: sitemap.xml
```
同时修改hexo的url配置为你的博客域名
```
# URL
## If your site is put in a subdirectory, set url as 'http://yoursite.com/child' and root as '/child/'
url: https://zhanghanlun.github.io
```
然后在[谷歌搜索网站](https://search.google.com/search-console)进行配置添加
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200209082916388.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW5naGFubHVu,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200209083150282.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW5naGFubHVu,size_16,color_FFFFFF,t_70)
将该文件保存到source目录下
同理[百度搜索资源平台](https://ziyuan.baidu.com/)提交也是这么做
**踩坑注意**需要修改hexo配置文件,将该html在生成过程中保留原有格式不变
```
skip_render:
    - baidu_verify_.html
    - google.html
```
