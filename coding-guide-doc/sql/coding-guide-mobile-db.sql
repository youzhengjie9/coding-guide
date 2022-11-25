DROP DATABASE IF EXISTS `coding-guide-mobile-db`;
CREATE DATABASE `coding-guide-mobile-db`;

USE `coding-guide-mobile-db`;

/* 面试题表 */
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`(
                             `id` bigint(20) NOT NULL COMMENT '主键',
                             `user_id` bigint(20) NULL COMMENT '发布面试题的用户id',
                             `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '面试题标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '面试题内容',
                             `allow_comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论（0代表不允许评论，1代表允许评论）',
                             `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否为推荐（0代表不推荐，1代表推荐）',
                             `is_public` tinyint(1) NOT NULL DEFAULT 1 COMMENT '题目是否公开（0代表私密，1代表公开）',
                             `read_count` int(10) NULL DEFAULT 0 COMMENT '阅读数',
                             `like_count` int(10) NULL DEFAULT 0 COMMENT '点赞数',
                             `collect_count` int(10) NULL DEFAULT 0 COMMENT '收藏数',
                             `comment_count` int(10) NULL DEFAULT 0 COMMENT '评论数',
                             `meet_count` int(10) NULL DEFAULT 0 COMMENT '题目遇到数',
                             `difficulty` tinyint(5) NULL DEFAULT 1 COMMENT '题目难度（分为1=简单、2=中等、3=较难、4=困难）',
                             `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '该面试题标签名称字符串，用‘,’分隔',
                             `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
                             `update_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
                             `sort` int DEFAULT '1' COMMENT '排序,值越大优先级越高，越排在上面',
                             `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题表';

INSERT INTO `t_question` VALUES
                             (5705371473282053,5700000000000001,'我是标题5705371473282053','我是内容5705371473282053',1,1,1,6615,14811,7654,12010,11704,1,'kubernetes,ElasticSearch,SpringCloud','2022-11-23 16:51:01','2022-12-05 11:12:20',80,0),
                             (5705371487634437,5700000000000002,'我是标题5705371487634437','我是内容5705371487634437',0,1,1,19606,801,12249,10487,17336,2,'Mybatis','2022-11-23 16:51:02','2022-12-05 11:12:20',259,0),
                             (5705371500938245,5700000000000001,'我是标题5705371500938245','我是内容5705371500938245',1,0,1,1934,838,12911,8931,8848,3,'前端,Docker,前端','2022-11-23 16:51:02','2022-12-05 11:12:20',116,0),
                             (5705371514110981,5700000000000002,'我是标题5705371514110981','我是内容5705371514110981',0,1,1,2578,16782,13996,7641,19215,4,'SpringCloud,Redis','2022-11-23 16:51:02','2022-12-05 11:12:20',198,0),
                             (5705371527480325,5700000000000001,'我是标题5705371527480325','我是内容5705371527480325',1,0,1,2515,19426,8748,14321,7041,1,'Docker,SpringMVC,Linux','2022-11-23 16:51:02','2022-12-05 11:12:20',62,0),
                             (5705371540980741,5700000000000002,'我是标题5705371540980741','我是内容5705371540980741',0,1,1,12766,12569,11608,4974,7918,2,'kubernetes,Kafka,SpringBoot','2022-11-23 16:51:02','2022-12-05 11:12:20',146,0),
                             (5705371554087941,5700000000000001,'我是标题5705371554087941','我是内容5705371554087941',0,1,1,1609,1176,9190,18401,936,3,'SpringCloud,Python,kubernetes','2022-11-23 16:51:03','2022-12-05 11:12:20',276,0),
                             (5705371567653893,5700000000000002,'我是标题5705371567653893','## Java手写线程池（第一代）

- **经常使用线程池，故今天突发奇想，手写一个线程池，会有很多不足，请多多宽容。因为这也是第一代的版本，后续会更完善。**

### 手写线程池-定义参数

```java
	private final AtomicInteger taskcount=new AtomicInteger(0);
    private final AtomicInteger threadNumber=new AtomicInteger(0);
    private volatile int corePoolSize;
    private final Set<MyThreadPoolExecutor.MyWorker> workers;
    private final BlockingQueue<Runnable> waitingQueue;
    private final String THREADPOOL_NAME="MyThread-Pool-";
    private volatile boolean isRunning=true;
    private volatile boolean STOPNOW=false;
    private final ThreadFactory threadFactory;
```

 **taskcount：执行任务次数**
 **threadNumber：线程编号，从0开始依次递增。**
 **corePoolSize：核心线程数**
 **workers：工作线程**
 **waitingQueue：等待队列**
 **THREADPOOL_NAME：线程名称**
 **isRunning：是否运行**
 **STOPNOW：是否立刻停止**
 **threadFactory：线程工厂**



### 手写线程池-构造器

```java
    public MyThreadPoolExecutor(int corePoolSize, BlockingQueue<Runnable> waitingQueue,ThreadFactory threadFactory) {
        this.corePoolSize=corePoolSize;
        this.workers=new HashSet<>(corePoolSize);
        this.waitingQueue=waitingQueue;
        this.threadFactory=threadFactory;
        //线程预热
        for (int i = 0; i < corePoolSize; i++) {
            new MyWorker();
        }
    }
```

- 该构造器作用：
- **1：对参数进行赋值。**
- **2：线程预热。根据corePoolSize的大小来调用MyWorker的构造器**。我们可以看看MyWorker构造器做了什么。

```java
	final Thread thread; //为每个MyWorker

        MyWorker(){
            Thread td = threadFactory.newThread(this);
            td.setName(THREADPOOL_NAME+threadNumber.getAndIncrement());
            this.thread=td;
            this.thread.start();
            workers.add(this);
        }
```

- MyWorker构造器通过**线程工厂**对当前对象**生成Thread**；
- 并**设置线程名**为：MyThread-Pool-自增线程编号；
- 然后**调用线程的start方法**启动线程；
- 最后**存放在workers这个Set集合中**，这样就可以实现线程复用了。

#### 手写线程池-默认构造器

```java
	public MyThreadPoolExecutor(){
        this(5,new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory());
    }
```

- 默认构造器的**赋初始值**：
- **corePoolSize：5**
-  **waitingQueue：new ArrayBlockingQueue<>(10)，长度为10的有限阻塞队列**
-  **threadFactory：Executors.defaultThreadFactory()**

### 手写线程池-execute方法

```java
	public boolean execute(Runnable runnable)
    {
        return waitingQueue.offer(runnable);
    }
```

- 本质上其实就是**把Runnable（任务）放到waitingQueue中**。

### 手写线程池-处理任务

```java
	   @Override
        public void run() {
            //循环接收任务
                while (true)
                {
                    if((!isRunning&&waitingQueue.size()==0)||STOPNOW)
                    {
                        break;
                    }else {
                        Runnable runnable = waitingQueue.poll();
                        if(runnable!=null){
                            runnable.run();
                            System.out.println("task==>"+taskcount.incrementAndGet());
                        }
                    }
                }
        }
```

-  本质上就是一个死循环接收任务，退出条件如下：
- **1：优雅的退出。当isRunning为false并且waitingQueue的队列大小为0（也就是无任务了）**
- **2：暴力退出。当STOPNOW为true，则说明调用了shutdownNow方法**
- **else语句块会不断取任务，当任务！=null时则调用run方法处理任务**



### 手写线程池-优雅关闭线程池

```java
	public void shutdown()
    {
        this.isRunning=false;
    }
```



### 手写线程池-暴力关闭线程池

```java
	public void shutdownNow()
    {
        this.STOPNOW=true;
    }
```

### 手写线程池-源代码

#### 手写线程池类的源代码

```java
package com.springframework.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池类
 * @author 游政杰
 */
public class MyThreadPoolExecutor {

    private final AtomicInteger taskcount=new AtomicInteger(0);//执行任务次数
    private final AtomicInteger threadNumber=new AtomicInteger(0); //线程编号
    private volatile int corePoolSize; //核心线程数
    private final Set<MyThreadPoolExecutor.MyWorker> workers; //工作线程
    private final BlockingQueue<Runnable> waitingQueue; //等待队列
    private final String THREADPOOL_NAME="MyThread-Pool-";//线程名称
    private volatile boolean isRunning=true; //是否运行
    private volatile boolean STOPNOW=false; //是否立刻停止
    private final ThreadFactory threadFactory; //线程工厂

    public MyThreadPoolExecutor(){
        this(5,new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory());
    }

    public MyThreadPoolExecutor(int corePoolSize, BlockingQueue<Runnable> waitingQueue,ThreadFactory threadFactory) {
        this.corePoolSize=corePoolSize;
        this.workers=new HashSet<>(corePoolSize);
        this.waitingQueue=waitingQueue;
        this.threadFactory=threadFactory;
        //线程预热
        for (int i = 0; i < corePoolSize; i++) {
            new MyWorker();
        }
    }

    /**
     * MyWorker就是我们每一个线程对象
     */
    private final class MyWorker implements Runnable{

        final Thread thread; //为每个MyWorker

        MyWorker(){
            Thread td = threadFactory.newThread(this);
            td.setName(THREADPOOL_NAME+threadNumber.getAndIncrement());
            this.thread=td;
            this.thread.start();
            workers.add(this);
        }

        @Override
        public void run() {
            //循环接收任务
                while (true)
                {
                    //循环退出条件：
                    //1：当isRunning为false并且waitingQueue的队列大小为0（也就是无任务了），会优雅的退出。
                    //2：当STOPNOW为true，则说明调用了shutdownNow方法进行暴力退出。
                    if((!isRunning&&waitingQueue.size()==0)||STOPNOW)
                    {
                        break;
                    }else {
                        //不断取任务，当任务！=null时则调用run方法处理任务
                        Runnable runnable = waitingQueue.poll();
                        if(runnable!=null){
                            runnable.run();
                            System.out.println("task==>"+taskcount.incrementAndGet());
                        }
                    }
                }
        }
    }

    public boolean execute(Runnable runnable)
    {
        return waitingQueue.offer(runnable);
    }
    //优雅的关闭
    public void shutdown()
    {
        this.isRunning=false;
    }
    //暴力关闭
    public void shutdownNow()
    {
        this.STOPNOW=true;
    }
}

```



#### 测试使用手写线程池代码

```java
package com.springframework.test;

import com.springframework.concurrent.MyThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

  public static void main(String[] args) {


      MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor
              (5,new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory());

      for(int i=0;i<10;i++){

          int finalI = i;
          myThreadPoolExecutor.execute(()->{
              System.out.println(Thread.currentThread().getName()+">>>>"+ finalI);
          });

      }

      myThreadPoolExecutor.shutdown();

//      myThreadPoolExecutor.shutdownNow();



  }
}

```

### 问题：为什么自定义线程池的execute执行的任务有时会变少？



- **那是因为waitingQueue满了放不下任务了，导致任务被丢弃，相当于DiscardPolicy拒绝策略**
  - **解决办法有：**
  - **1：设置最大线程数，自动对线程池扩容。**
  - **2：调大waitingQueue的容量capacity**



**最后：因为这是我手写的线程池的初代版本，基本实现线程池的复用功能，然而还有很多未完善，将来会多出几篇完善后的文章，对目前手写的线程池进行升级。**

**后续还会继续出关于作者手写Spring框架，手写Tomcat等等框架的博文！！！！！**',1,0,1,20360,6582,18327,13978,9498,4,'Redis,Golang,SpringBoot','2022-11-23 16:51:03','2022-12-05 11:12:20',85,0),
                             (5705371580892165,5700000000000001,'我是标题5705371580892165','我是内容5705371580892165',1,0,1,16718,1430,19546,4630,7905,1,'SpringBoot,Golang,Linux','2022-11-23 16:51:03','2022-12-05 11:12:20',254,0),
                             (5705371594261509,5700000000000002,'我是标题5705371594261509','我是内容5705371594261509',1,1,1,14527,17015,20229,9620,7993,2,'Linux,Spring,Vue','2022-11-23 16:51:03','2022-12-05 11:12:20',163,0),
                             (5705371607565317,5700000000000001,'我是标题5705371607565317','我是内容5705371607565317',0,1,0,5742,16348,18617,1670,9027,3,'Kafka,Python','2022-11-23 16:51:03','2022-12-05 11:12:20',203,0),
                             (5705371620934661,5700000000000002,'我是标题5705371620934661','我是内容5705371620934661',1,0,1,3410,3137,7761,19055,9501,4,'Java基础,Redis,Java基础','2022-11-23 16:51:04','2022-12-05 11:12:20',278,0),
                             (5705371634435077,5700000000000001,'我是标题5705371634435077','我是内容5705371634435077',1,1,0,14476,3343,14448,3377,4544,1,'前端,Kafka,Vue','2022-11-23 16:51:04','2022-12-05 11:12:20',14,0),
                             (5705371647673349,5700000000000002,'我是标题5705371647673349','我是内容5705371647673349',0,1,1,4425,9288,8706,17518,13664,2,'SpringMVC,SpringBoot,SpringMVC','2022-11-23 16:51:04','2022-12-05 11:12:20',236,0),
                             (5705371661042693,5700000000000001,'我是标题5705371661042693','我是内容5705371661042693',1,1,0,3500,10340,13810,13762,1080,3,'ElasticSearch,SpringBoot,Vue','2022-11-23 16:51:04','2022-12-05 11:12:20',189,0),
                             (5705371674608645,5700000000000002,'我是标题5705371674608645','我是内容5705371674608645',0,1,1,8522,15880,15295,2429,13104,4,'SpringBoot,SpringCloud','2022-11-23 16:51:04','2022-12-05 11:12:20',140,0),
                             (5705371687912453,5700000000000001,'我是标题5705371687912453','我是内容5705371687912453',1,1,1,12948,11815,6642,14850,9822,1,'Kafka,Docker','2022-11-23 16:51:05','2022-12-05 11:12:20',25,0),
                             (5705371701281797,5700000000000002,'我是标题5705371701281797','我是内容5705371701281797',0,1,1,9475,15980,17189,11078,12853,2,'Python,ElasticSearch','2022-11-23 16:51:05','2022-12-05 11:12:20',134,0),
                             (5705371714520069,5700000000000001,'我是标题5705371714520069','我是内容5705371714520069',1,0,0,2211,16257,10794,7224,5359,3,'Golang','2022-11-23 16:51:05','2022-12-05 11:12:20',148,0),
                             (5705371727954949,5700000000000002,'我是标题5705371727954949','我是内容5705371727954949',1,1,1,3575,3933,12772,7779,6449,4,'Golang,Vue,Kafka','2022-11-23 16:51:05','2022-12-05 11:12:20',50,0),
                             (5705371741193221,5700000000000001,'我是标题5705371741193221','我是内容5705371741193221',1,1,0,4719,17393,1157,4149,19078,1,'Git','2022-11-23 16:51:05','2022-12-05 11:12:20',194,0),
                             (5705371754628101,5700000000000002,'我是标题5705371754628101','我是内容5705371754628101',1,1,1,15374,14819,7473,10011,3329,2,'Golang','2022-11-23 16:51:06','2022-12-05 11:12:20',258,0),
                             (5705371767931909,5700000000000001,'我是标题5705371767931909','我是内容5705371767931909',0,1,1,13395,4814,7198,2959,11257,3,'ElasticSearch,Redis','2022-11-23 16:51:06','2022-12-05 11:12:20',204,0),
                             (5705371781170181,5700000000000002,'我是标题5705371781170181','我是内容5705371781170181',1,0,1,9093,15093,15164,8735,9399,4,'Kafka,SpringCloud,前端','2022-11-23 16:51:06','2022-12-05 11:12:20',28,0),
                             (5705371794473989,5700000000000001,'我是标题5705371794473989','我是内容5705371794473989',1,1,1,6092,17693,13031,7259,12964,1,'Kafka,前端,Docker','2022-11-23 16:51:06','2022-12-05 11:12:20',216,0),
                             (5705371807908869,5700000000000002,'我是标题5705371807908869','我是内容5705371807908869',1,1,0,11728,7926,7909,12726,13459,2,'SpringCloud','2022-11-23 16:51:06','2022-12-05 11:12:20',220,0),
                             (5705371821409285,5700000000000001,'我是标题5705371821409285','我是内容5705371821409285',1,1,1,577,4566,11354,8421,2223,3,'Spring','2022-11-23 16:51:07','2022-12-05 11:12:20',254,0),
                             (5705371834909701,5700000000000002,'我是标题5705371834909701','我是内容5705371834909701',0,1,0,18644,4399,7865,13922,1061,4,'Kafka,Linux','2022-11-23 16:51:07','2022-12-05 11:12:20',254,0),
                             (5705371848410117,5700000000000001,'我是标题5705371848410117','我是内容5705371848410117',1,1,1,10783,11532,5215,1388,3813,1,'Golang,kubernetes,Java基础','2022-11-23 16:51:07','2022-12-05 11:12:20',144,0),
                             (5705371861779461,5700000000000002,'我是标题5705371861779461','我是内容5705371861779461',1,1,1,6365,15228,5448,2827,19792,2,'Spring','2022-11-23 16:51:07','2022-12-05 11:12:20',294,0),
                             (5705371875148805,5700000000000001,'我是标题5705371875148805','我是内容5705371875148805',1,0,1,10945,2075,18085,8139,4223,3,'Mybatis,Java基础,kubernetes','2022-11-23 16:51:07','2022-12-05 11:12:20',244,0),
                             (5705371888518149,5700000000000002,'我是标题5705371888518149','我是内容5705371888518149',1,1,0,14355,8583,4019,9041,16046,4,'SpringCloud,前端,Java基础','2022-11-23 16:51:08','2022-12-05 11:12:20',164,0),
                             (5705371901690885,5700000000000001,'我是标题5705371901690885','我是内容5705371901690885',1,1,0,15062,825,4510,11828,7952,1,'ElasticSearch,Docker','2022-11-23 16:51:08','2022-12-05 11:12:20',272,0),
                             (5705371915256837,5700000000000002,'我是标题5705371915256837','我是内容5705371915256837',1,0,1,12675,10989,12133,14072,14950,2,'Kafka','2022-11-23 16:51:08','2022-12-05 11:12:20',109,0),
                             (5705371928691717,5700000000000001,'我是标题5705371928691717','我是内容5705371928691717',1,1,0,6388,18850,18908,11100,4200,3,'SpringBoot,SpringBoot','2022-11-23 16:51:08','2022-12-05 11:12:20',281,0),
                             (5705371941929989,5700000000000002,'我是标题5705371941929989','我是内容5705371941929989',0,1,1,18527,10478,9821,7361,8163,4,'SpringCloud','2022-11-23 16:51:08','2022-12-05 11:12:20',76,0),
                             (5705371955364869,5700000000000001,'我是标题5705371955364869','我是内容5705371955364869',1,1,1,17703,4624,16597,15500,15166,1,'Golang,Golang','2022-11-23 16:51:09','2022-12-05 11:12:20',241,0),
                             (5705371968865285,5700000000000002,'我是标题5705371968865285','我是内容5705371968865285',1,1,1,8105,19156,9083,6916,5250,2,'Linux,ElasticSearch,Git','2022-11-23 16:51:09','2022-12-05 11:12:20',49,0),
                             (5705371982038021,5700000000000001,'我是标题5705371982038021','我是内容5705371982038021',0,1,1,11107,5397,6273,13303,11628,3,'SpringBoot','2022-11-23 16:51:09','2022-12-05 11:12:20',127,0),
                             (5705371995472901,5700000000000002,'我是标题5705371995472901','我是内容5705371995472901',1,1,0,9015,1908,9275,1888,20043,4,'Git','2022-11-23 16:51:09','2022-12-05 11:12:20',222,0),
                             (5705372008711173,5700000000000001,'我是标题5705372008711173','我是内容5705372008711173',1,1,1,18158,8384,5752,7667,8568,1,'kubernetes','2022-11-23 16:51:10','2022-12-05 11:12:20',149,0),
                             (5705372022146053,5700000000000002,'我是标题5705372022146053','我是内容5705372022146053',0,1,1,4435,7849,5435,3709,18509,2,'Python,kubernetes','2022-11-23 16:51:10','2022-12-05 11:12:20',89,0),
                             (5705372035646469,5700000000000001,'我是标题5705372035646469','我是内容5705372035646469',1,1,0,16686,14305,9441,18681,7708,3,'Git','2022-11-23 16:51:10','2022-12-05 11:12:20',141,0),
                             (5705372048950277,5700000000000002,'我是标题5705372048950277','我是内容5705372048950277',1,0,1,14291,9317,11609,16767,15178,4,'Java基础,Spring','2022-11-23 16:51:10','2022-12-05 11:12:20',29,0),
                             (5705372062319621,5700000000000001,'我是标题5705372062319621','我是内容5705372062319621',1,0,1,2500,15679,9609,6394,13422,1,'Git,SpringBoot','2022-11-23 16:51:10','2022-12-05 11:12:20',57,0),
                             (5705372075688965,5700000000000002,'我是标题5705372075688965','我是内容5705372075688965',0,1,0,14175,9241,10412,8544,7767,2,'Kafka,Spring','2022-11-23 16:51:11','2022-12-05 11:12:20',288,0),
                             (5705372088927237,5700000000000001,'我是标题5705372088927237','我是内容5705372088927237',1,1,0,19126,7413,14381,18956,15958,3,'Docker,SpringMVC','2022-11-23 16:51:11','2022-12-05 11:12:20',294,0),
                             (5705372102296581,5700000000000002,'我是标题5705372102296581','我是内容5705372102296581',1,1,1,2917,20429,14481,14369,4628,4,'SpringMVC,Spring,SpringCloud','2022-11-23 16:51:11','2022-12-05 11:12:20',37,0),
                             (5705372115469317,5700000000000001,'我是标题5705372115469317','我是内容5705372115469317',1,0,1,17412,19327,5237,7325,3658,1,'Java基础,Java基础','2022-11-23 16:51:11','2022-12-05 11:12:20',232,0),
                             (5705372128773125,5700000000000002,'我是标题5705372128773125','我是内容5705372128773125',0,1,1,5110,11884,10726,3099,3398,2,'ElasticSearch,Golang','2022-11-23 16:51:11','2022-12-05 11:12:20',204,0);

/* 标签表 */
DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag`(
                        `id` bigint(20) NOT NULL COMMENT '主键',
                        `tag_name` VARCHAR(255) NOT NULL COMMENT '标签名称',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `sort` int DEFAULT '1' COMMENT '排序,值越大优先级越高，越排在上面',
                        `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';

INSERT INTO `t_tag`
VALUES (10001, 'Java基础', '2022-11-09 17:21:50',2400,0)
     , (10002, 'Spring', '2022-11-09 17:21:50',2300,0)
     , (10003, 'SpringBoot', '2022-11-09 17:21:50',2200,0)
     , (10004, 'SpringCloud', '2022-11-09 17:21:50',2100,0)
     , (10005, 'Redis', '2022-11-09 17:21:50',2000,0)
     , (10006, 'SpringMVC', '2022-11-09 17:21:50',1900,0)
     , (10007, 'Mybatis', '2022-11-09 17:21:50',1800,0)
     , (10008, 'Docker', '2022-11-09 17:21:50',1700,0)
     , (10009, 'kubernetes', '2022-11-09 17:21:50',1600,0)
     , (10010, 'Kafka', '2022-11-09 17:21:50',1500,0)
     , (10011, 'Linux', '2022-11-09 17:21:50',1400,0)
     , (10012, 'Vue', '2022-11-09 17:21:50',1300,0)
     , (10013, 'Git', '2022-11-09 17:21:50',1200,0)
     , (10014, 'ElasticSearch', '2022-11-09 17:21:50',1100,0)
     , (10015, '前端', '2022-11-09 17:21:50',1000,0)
     , (10016, 'Python', '2022-11-09 17:21:50',900,0)
     , (10017, 'Golang', '2022-11-09 17:21:50',800,0)
     , (10018, 'C++', '2022-11-09 17:21:50',700,0);


/* 面试题关联标签表 */

DROP TABLE IF EXISTS `t_question_tag`;

CREATE TABLE `t_question_tag`(
                                 `id` bigint(20) NOT NULL COMMENT '主键',
                                 `question_id` bigint(20) NULL COMMENT '面试题id',
                                 `tag_id` bigint(20) NULL COMMENT '标签id',
                                 INDEX `idx_qid` (`question_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题关联标签表';

INSERT INTO `t_question_tag` VALUES
                                 (5705371473544197,5705371473282053,10009),
                                 (5705371473544198,5705371473282053,10014),
                                 (5705371473544199,5705371473282053,10004),
                                 (5705371487634438,5705371487634437,10007),
                                 (5705371500938246,5705371500938245,10015),
                                 (5705371500938247,5705371500938245,10008),
                                 (5705371500938248,5705371500938245,10015),
                                 (5705371514110982,5705371514110981,10004),
                                 (5705371514110983,5705371514110981,10005),
                                 (5705371527480326,5705371527480325,10008),
                                 (5705371527480327,5705371527480325,10006),
                                 (5705371527480328,5705371527480325,10011),
                                 (5705371540980742,5705371540980741,10009),
                                 (5705371540980743,5705371540980741,10010),
                                 (5705371540980744,5705371540980741,10003),
                                 (5705371554087942,5705371554087941,10004),
                                 (5705371554087943,5705371554087941,10016),
                                 (5705371554087944,5705371554087941,10009),
                                 (5705371567653894,5705371567653893,10005),
                                 (5705371567653895,5705371567653893,10017),
                                 (5705371567653896,5705371567653893,10003),
                                 (5705371580892166,5705371580892165,10003),
                                 (5705371580892167,5705371580892165,10017),
                                 (5705371580892168,5705371580892165,10011),
                                 (5705371594261510,5705371594261509,10011),
                                 (5705371594261511,5705371594261509,10002),
                                 (5705371594261512,5705371594261509,10012),
                                 (5705371607565318,5705371607565317,10010),
                                 (5705371607565319,5705371607565317,10016),
                                 (5705371620934662,5705371620934661,10001),
                                 (5705371620934663,5705371620934661,10005),
                                 (5705371620934664,5705371620934661,10001),
                                 (5705371634435078,5705371634435077,10015),
                                 (5705371634435079,5705371634435077,10010),
                                 (5705371634435080,5705371634435077,10012),
                                 (5705371647673350,5705371647673349,10006),
                                 (5705371647673351,5705371647673349,10003),
                                 (5705371647673352,5705371647673349,10006),
                                 (5705371661042694,5705371661042693,10014),
                                 (5705371661042695,5705371661042693,10003),
                                 (5705371661042696,5705371661042693,10012),
                                 (5705371674608646,5705371674608645,10003),
                                 (5705371674608647,5705371674608645,10004),
                                 (5705371687912454,5705371687912453,10010),
                                 (5705371687912455,5705371687912453,10008),
                                 (5705371701281798,5705371701281797,10016),
                                 (5705371701281799,5705371701281797,10014),
                                 (5705371714520070,5705371714520069,10017),
                                 (5705371727954950,5705371727954949,10017),
                                 (5705371727954951,5705371727954949,10012),
                                 (5705371727954952,5705371727954949,10010),
                                 (5705371741193222,5705371741193221,10013),
                                 (5705371754628102,5705371754628101,10017),
                                 (5705371767931910,5705371767931909,10014),
                                 (5705371767931911,5705371767931909,10005),
                                 (5705371781170182,5705371781170181,10010),
                                 (5705371781170183,5705371781170181,10004),
                                 (5705371781170184,5705371781170181,10015),
                                 (5705371794473990,5705371794473989,10010),
                                 (5705371794473991,5705371794473989,10015),
                                 (5705371794473992,5705371794473989,10008),
                                 (5705371807908870,5705371807908869,10004),
                                 (5705371821409286,5705371821409285,10002),
                                 (5705371834909702,5705371834909701,10010),
                                 (5705371834909703,5705371834909701,10011),
                                 (5705371848410118,5705371848410117,10017),
                                 (5705371848410119,5705371848410117,10009),
                                 (5705371848410120,5705371848410117,10001),
                                 (5705371861779462,5705371861779461,10002),
                                 (5705371875148806,5705371875148805,10007),
                                 (5705371875148807,5705371875148805,10001),
                                 (5705371875148808,5705371875148805,10009),
                                 (5705371888518150,5705371888518149,10004),
                                 (5705371888518151,5705371888518149,10015),
                                 (5705371888518152,5705371888518149,10001),
                                 (5705371901690886,5705371901690885,10014),
                                 (5705371901690887,5705371901690885,10008),
                                 (5705371915256838,5705371915256837,10010),
                                 (5705371928691718,5705371928691717,10003),
                                 (5705371928691719,5705371928691717,10003),
                                 (5705371941929990,5705371941929989,10004),
                                 (5705371955364870,5705371955364869,10017),
                                 (5705371955364871,5705371955364869,10017),
                                 (5705371968865286,5705371968865285,10011),
                                 (5705371968865287,5705371968865285,10014),
                                 (5705371968865288,5705371968865285,10013),
                                 (5705371982038022,5705371982038021,10003),
                                 (5705371995472902,5705371995472901,10013),
                                 (5705372008711174,5705372008711173,10009),
                                 (5705372022146054,5705372022146053,10016),
                                 (5705372022146055,5705372022146053,10009),
                                 (5705372035646470,5705372035646469,10013),
                                 (5705372048950278,5705372048950277,10001),
                                 (5705372048950279,5705372048950277,10002),
                                 (5705372062319622,5705372062319621,10013),
                                 (5705372062385157,5705372062319621,10003),
                                 (5705372075688966,5705372075688965,10010),
                                 (5705372075688967,5705372075688965,10002),
                                 (5705372088927238,5705372088927237,10008),
                                 (5705372088927239,5705372088927237,10006),
                                 (5705372102296582,5705372102296581,10006),
                                 (5705372102296583,5705372102296581,10002),
                                 (5705372102296584,5705372102296581,10004),
                                 (5705372115469318,5705372115469317,10001),
                                 (5705372115469319,5705372115469317,10001),
                                 (5705372128773126,5705372128773125,10014),
                                 (5705372128773127,5705372128773125,10017);


/* 面试题点赞表 */
DROP TABLE IF EXISTS `t_question_like`;

CREATE TABLE `t_question_like`(
                                  `id` bigint(20) NOT NULL COMMENT '主键',
                                  `user_id` bigint(20) NULL COMMENT '用户id',
                                  `question_id` bigint(20) NULL COMMENT '被点赞的面试题id',
                                  `like_time` datetime DEFAULT NULL COMMENT '点赞时间',
                                  PRIMARY KEY (`id`),
                                  INDEX `idx_uid` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题点赞表';

/* 面试题收藏表 */
DROP TABLE IF EXISTS `t_question_collect`;

CREATE TABLE `t_question_collect`(
                                     `id` bigint(20) NOT NULL COMMENT '主键',
                                     `user_id` bigint(20) NULL COMMENT '用户id',
                                     `question_id` bigint(20) NULL COMMENT '被收藏的面试题id',
                                     `collect_time` datetime DEFAULT NULL COMMENT '收藏时间',
                                     PRIMARY KEY (`id`),
                                     INDEX `idx_uid` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题收藏表';

/* 评论表 */

/* 遇见面试题表 */


/* 用户表 */
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
                          `id` bigint NOT NULL COMMENT '主键',
                          `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
                          `nick_name` varchar(32) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
                          `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
                          `status` tinyint(1) DEFAULT '0' COMMENT '用户状态（0正常 1停用）',
                          `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                          `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
                          `sex` tinyint(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
                          `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像地址',
                          `create_time` date DEFAULT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
                          `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

# 明文密码都为123456
INSERT INTO `t_user` VALUES (5700000000000001, 'youzhengjie1', '蔡徐坤', '$2a$10$HebtQPbLFf3YrO6B1n8Sb.AWHAz8SZtAc48IFGm8iSXjZsym3GPii', 0, '1550324080@qq.com', '18420163207', 0, 'https://img2.baidu.com/it/u=670341883,3643142939&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '2022-09-26 23:46:02', '2022-09-26 23:46:05', 0);
INSERT INTO `t_user` VALUES (5700000000000002, 'youzhengjie2', '周杰伦', '$2a$10$HebtQPbLFf3YrO6B1n8Sb.AWHAz8SZtAc48IFGm8iSXjZsym3GPii', 0, '1550324080@qq.com', '18420163207', 0, 'https://img2.baidu.com/it/u=670341883,3643142939&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '2022-09-26 23:46:02', '2022-09-26 23:46:05', 0);


