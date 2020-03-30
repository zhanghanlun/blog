---
title: 分布式ID算法snowflake(Java版)详解
tags:
  - 分布式ID
  - 分布式系统
  - Java
categories: 分布式系统
abbrlink: 802469613
mathjax: true
date: 2020-03-30 11:50:45
---

# 前言
snowflake算法是Twitter技术团队在2010年开源的分布式ID的生成算法，后续美团和百度都相应的根据该算法进行了改进，并且开源了其分布式ID生成算法。本文将详细介绍snowflake算法的数据结构以及其工作原理
<!-- more -->
# 数据结构
snowflake算法生成的分布式ID是一个一个64bit大小的整数，其结构如下：
![snowflake数据结构](https://upyun.zhanghanlun.com/blog/2020/03/2020_330111998.jpg)
- 1位，占位符不用，我们知道一般第一位表示正负数的符号，所以固定位为0
- 41位，用来记录时间的时间戳（毫秒）
41位可以表示的最大的数字为$2^{41}$,其可以使用的最长的时间为
$2^{41}\div(1000\times60\times60\times24\times356)=71$年
- 10位，工作机器ID，可以部署在$2^{10}=1024$个结点，其中5位为workerId，5位为datacenterId
- 12位，序列号，用来记录同毫秒内产生的ID，可以表示$2^{12}=4096$个序列号
# 代码详解
Twitter的官方是用Scala写的，在这里我改成了Java版
```java
package 数组;

public class IdWorker {

	//worker ID
    private long workerId;

    /**
     * 数据中心ID
     */
    private long datacenterId;

    /**
     * 系统起始时间
     * 2010/11/4 9:42:54
     */
    private long twepoch = 1288834974657L;

    /**
     * workerId位数
     */
    private long workerIdBits = 5L;

    /**
     * datacenter位数
     */
    private long datacenterIdBits = 5L;

    /**
     * 最大的workerId 0x1f = 31
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 最大的数据中心ID 0x1f = 31
     */
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列位数
     */
    private long sequenceBits = 12L;


    /**
     * workerId位移 12位
     */
    private long workerIdShift = sequenceBits;

    /**
     * datacenter位移 17位
     */
    private long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间位移 22位
     */
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 序列掩码，便于求余
     */
    private long sequenceMask = -1L ^ (-1L << sequenceBits);


    private long lastTimestamp = -1L;

    private long sequence = 0L;


    public IdWorker(Long workerId, Long datacenterId) {

        if (workerId > maxWorkerId || workerId < 0) {
            System.out.println("worker Id can't be greater than %d or less than 0");
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            System.out.println("datacenter Id can't be greater than %d or less than 0");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    private Long getWorkerId() {
        return workerId;
    }

    private Long getDataCenterId() {
        return datacenterId;
    }

    private Long getTimestamp() {
        return System.currentTimeMillis();
    }

    public synchronized Long getId() {
        //获取当前时间戳
        Long timestamp = getTimeGen();
        if (timestamp < lastTimestamp) {
            System.out.println("error,clock is moving backwards.  Rejecting requests until ");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //阻塞到下一个毫秒
                timestamp = getNextMills(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        //整合时间戳-数据中心ID-workerID-序列号
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 阻塞到下一个毫秒
     * @param lastTimestamp
     * @return
     */
    protected Long getNextMills(Long lastTimestamp) {
        Long timestamp = getTimeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimeGen();
        }
        return timestamp;
    }

    protected Long getTimeGen() {
        return System.currentTimeMillis();
    }
}
```
该算法的整体思路如下：
 1. 获取ID的时候，先获取当前的时间戳，和上一个时间戳比较，如果小，则产生了时钟回拨，错误。
 2. 如果时间戳相等则序列号加1和序列号最大值求余，当序列号为0的时候则阻塞到下个毫秒
 3. 如果时间戳大于上一个时间戳，则序列号赋值为0
 4. 通过位运算生成分布式ID结果
