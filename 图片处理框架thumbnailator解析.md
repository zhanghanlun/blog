# 前言
Thumbnailator 包是处理图片缩略图的,可以对图片进行裁剪，修改格式，压缩等等。非常的方便。
# Thumbnailator压缩图片
简单例子代码直接上手
```java
Thumbnails.of("D:\\Images\\0gkZ4L5sxt.jpg")//输入路径
                    .size(100,100)//按长宽比例约束进行图片压缩至长宽限定的范围内
                    .toFile("D:\\1.jpg");//输出路径
```
其中of()方法可以接受三种类型的输入。
>* String字符串相关的路径
>* File类型的文件
>* BufferedImage类型

