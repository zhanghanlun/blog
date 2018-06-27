# git切换分支
首先通过
```git
$ git branch -a 
```
来查看所在目录的分支
```git
$ git branch -a
  master
* trunk
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
  remotes/origin/trunk
```
然后输入命令切换分支
```git
$ git checkout -b trunk origin/trunk
```
切换到origin/trunk分支命令本地分支为"trunk"