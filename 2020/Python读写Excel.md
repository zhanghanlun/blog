---
title: Python读写Excel
date: 2020-02-16 19:24:45
tags:
	- Python
	- Excel
categories: Python
---
# 前言
我们经常使用Python来读写Excel文件，该怎么做呢，本篇文章为大家介绍一下如何使用Python进行Excel文件的读写。
<!-- more -->
# 引入Excel读写包
安装xlrd和xlwt包
```shell
$ pip install xlrd
$ pip install xlwt
```
然后在Python程序中引用这两个包
```python
# 读Excel
import xlrd
# 写Excel
import xlwt
```
# 读Excel文件
Excel文件如图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216160447216.png)
```python
def read_excel(path):
    workbook = xlrd.open_workbook(path)
    # 打开工作表
    sheet = workbook.sheet_by_index(0)
    # 获取Excel文件的行数
    row_count = sheet.nrows
    # 循环遍历每行
    for i in range(0,row_count):
        # 获取每行的内容
        rows = sheet.row_values(i)
        for j in range(0,len(rows)):
            if j != len(rows)-1:
                print(rows[j],end="")
                print(" ",end="")
            else:
                print(rows[j])

read_excel("E:\\example.xlsx")
```
读Excel的结果为：
```
姓名 年龄 工作
张函仑 25.0 程序员
李四 29.0 教师
```
# 写Excel文件
xlwt模块只支持书写xls文件
```python
def write_excel(path):
    # 创建一个workbook
    workbook = xlwt.Workbook(encoding='utf-8')
    # 创建一个worksheet
    sheet1 = workbook.add_sheet('sheet1', cell_overwrite_ok=True)
    sheet1.write(3,0,"张三")
    sheet1.write(3,1,22)
    sheet1.write(3,2,"运营")
    workbook.save(path)
```
运行后的结果如下图所示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200216184842201.png)
对于xlsx文件可以用xlsxwriter包来实现，具体代码如下
```python
import xlsxwriter
def write_excel_xlsxwriter(path):
    # 创建一个workbook
    workbook = xlsxwriter.Workbook("E:\\example.xlsx")
    # 创建一个worksheet
    worksheet = workbook.add_worksheet('sheet1')
    worksheet.write(3,0,"张三")
    worksheet.write(3, 1, 22)
    worksheet.write(3, 2, "运营")
    workbook.close()
```