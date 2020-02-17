# 1、获取安装脚本
```
wget --no-check-certificate https://github.com/zhanghanlun/ssr/blob/master/shadowsocksR.sh
```
# 2、授予脚本权限
```
chmod +x shadowsocksR.sh
```
# 3、安装ssr
```
./shadowsocksR.sh 2>&1 | tee shadowsocksR.log
```