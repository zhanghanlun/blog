---
title: 使用EasyExcel读写Excel
date: 2020-02-08 17:24:45
tags:
	- EasyExcel
	- Java
categories: Java
---

# 前言
 在没有EasyExcel之前我们经常使用Apache的poi jar包进行excel的读写，配置起来比较麻烦，也比较消耗内存，阿里开源了EasyExcel，配置上简化了，在一定程度上简化了内存溢出问题。

<!-- more -->

# 引入jar包
```java
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>easyexcel</artifactId>
	<version>1.1.2-beta5</version>
</dependency>
```

# 读Excel
EasyExcel支持对实体类中的属性进行注解，方便后续进行读写操作。
```
//表示第0行，表头是id
@ExcelProperty(value = "id", index = 0)
```
## Student实体类
```java
public class Student extends BaseRowModel {

    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "性别", index = 2)
    private String sex;

    @ExcelProperty(value = "年级", index = 3)
    private Integer grade;

    @ExcelProperty(value = "年龄", index = 4)
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }
}
```

## 读取Excel

```java
public static void readExcel(String fileName){
        try {
        	//创建输入流
            InputStream inputStream = new FileInputStream(fileName);
			//构建要添加Student类
            Sheet sheet = new Sheet(1,1,Student.class);
            //读sheet表中的内容
            List<Object> studentList = EasyExcelFactory.read(inputStream,sheet);
            List<Student> students = new LinkedList<Student>();
            for (Object student : studentList){
                students.add((Student)student);
            }
            for (Student student : students){
                System.out.println(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

```output
//结果：
Student{id=1, sex='男', grade=1, score=12}
Student{id=2, sex='女', grade=2, score=13}
```

# 写Excel

```java
public static void writeExcel(String fileName,List<Student> students) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(2, 0, Student.class);
            writer.write(students, sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```