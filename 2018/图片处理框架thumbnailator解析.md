# 前言
Thumbnailator 包是处理图片缩略图的,可以对图片进行裁剪，修改格式，压缩等等。非常的方便。
# Thumbnailator压缩图片
## 按长宽约束比例压缩
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

其中toFile()方法可以接受两种类型的输出
>* String字符串相关的路径
>* File类型


## 直接压缩图片的大小

```java
Thumbnails.of(file)
.scale(0.1)      //将长宽按照指定的比例压缩
toFile(file);
```
例如当原图片为300 * 300像素
scale中的为0.1
得到的图片的为30 * 30像素大小


# Thumbnailator裁剪图片

```java
Thumbnails.of(file)
.sourceRegion(Positions.CENTER,100,200)//从中心区域取像素100*200
.size(100,200)//生成100*200大小的图片
.toFile(file);
```
在上述的裁剪代码中如果图片过大，我们这么裁剪会丧失很多画面内容，例子：
![enter description here][1]
图片的分辨率为4852 * 2823
执行上面的代码生成的图片如下：
!["][2]zhang6.jpg"
我们其实想把图片先压缩然后再取中间的100 * 200像素。
这种情况下就需要BufferedImage来实现
```java
private void processImage(File file, int widthThreshold, int heightThreshold) {

        double imageRatio = (double) widthThreshold / (double) heightThreshold;

        BufferedImage imageTemp = null;
        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            //根据长宽比来确定按那个边来压缩
            if ((double) width / height < imageRatio) {
                imageTemp = Thumbnails.of(file).width(widthThreshold).asBufferedImage();
            } else {
                imageTemp = Thumbnails.of(file).height(heightThreshold).asBufferedImage();
            }
            
            Thumbnails.of(imageTemp).sourceRegion(Positions.CENTER, widthThreshold, heightThreshold)
                    .size(widthThreshold, heightThreshold).toFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
执行完上述代码后图片为：保留了图片的更多内容
![enter description here][3]


  [1]: ./images/zhang6.jpg
  [2]: ./images/zhang6_3.jpg "zhang6.jpg"
  [3]: ./images/zhang6_4.jpg "zhang6.jpg"