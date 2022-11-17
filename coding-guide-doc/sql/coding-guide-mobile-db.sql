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
                             `is_public` tinyint(1) NOT NULL DEFAULT 1 COMMENT '题目是否公开（0代表私有，1代表公开）',
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
                             (10000001,3123000001,'我是标题10000001','我是内容10000001',1,1,1,5501,14043,1947,1499,1585,1,'Golang,ElasticSearch,Golang','2022-11-12 00:46:09','2022-12-05 11:12:20',279,0),
                             (10000002,3123000002,'我是标题10000002','我是内容10000002',1,1,1,1474,18796,5816,1781,526,2,'SpringBoot,SpringMVC,SpringMVC','2022-11-12 00:46:10','2022-12-05 11:12:20',76,0),
                             (10000003,3123000003,'我是标题10000003','我是内容10000003',1,1,1,1061,16874,11879,10394,18498,3,'SpringCloud,Python','2022-11-12 00:46:10','2022-12-05 11:12:20',262,0),
                             (10000004,3123000004,'我是标题10000004','我是内容10000004',1,1,1,6310,11791,5935,8108,17980,4,'SpringMVC','2022-11-12 00:46:10','2022-12-05 11:12:20',138,0),
                             (10000005,3123000005,'我是标题10000005','我是内容10000005',1,1,1,9880,14101,17095,17399,15157,1,'Kafka,Kafka','2022-11-12 00:46:10','2022-12-05 11:12:20',5,0),
                             (10000006,3123000006,'我是标题10000006','我是内容10000006',1,1,1,8203,20297,2712,7306,2370,2,'SpringBoot,Kafka,Java基础','2022-11-12 00:46:11','2022-12-05 11:12:20',78,0),
                             (10000007,3123000007,'我是标题10000007','我是内容10000007',1,1,1,3704,11225,3778,16887,12589,3,'Docker,Git','2022-11-12 00:46:11','2022-12-05 11:12:20',244,0),
                             (10000008,3123000008,'我是标题10000008','我是内容10000008',1,1,1,1833,5746,16427,2991,1682,4,'Vue,kubernetes,Git','2022-11-12 00:46:11','2022-12-05 11:12:20',116,0),
                             (10000009,3123000009,'我是标题10000009','我是内容10000009',1,1,1,18252,16004,16856,9017,18926,1,'Python,Linux,Kafka','2022-11-12 00:46:11','2022-12-05 11:12:20',137,0),
                             (10000010,3123000010,'我是标题10000010','我是内容10000010',1,1,1,8044,692,3482,5167,6755,2,'Java基础','2022-11-12 00:46:11','2022-12-05 11:12:20',38,0),
                             (10000011,3123000011,'我是标题10000011','我是内容10000011',1,1,1,2333,17302,20206,5005,13255,3,'前端','2022-11-12 00:46:12','2022-12-05 11:12:20',3,0),
                             (10000012,3123000012,'我是标题10000012','我是内容10000012',1,1,1,11232,13961,15886,19687,17132,4,'SpringCloud','2022-11-12 00:46:12','2022-12-05 11:12:20',31,0),
                             (10000013,3123000013,'我是标题10000013','我是内容10000013',1,1,1,6313,3962,8755,17676,19482,1,'Spring','2022-11-12 00:46:12','2022-12-05 11:12:20',148,0),
                             (10000014,3123000014,'我是标题10000014','我是内容10000014',1,1,1,16542,20156,3962,18599,14437,2,'Java基础','2022-11-12 00:46:12','2022-12-05 11:12:20',166,0),
                             (10000015,3123000015,'我是标题10000015','我是内容10000015',1,1,1,11855,6594,19386,4919,7854,3,'Python,前端','2022-11-12 00:46:13','2022-12-05 11:12:20',112,0),
                             (10000016,3123000016,'我是标题10000016','我是内容10000016',1,1,1,1019,12748,10306,5164,10257,4,'Vue','2022-11-12 00:46:13','2022-12-05 11:12:20',24,0),
                             (10000017,3123000017,'我是标题10000017','我是内容10000017',1,1,1,6489,2296,11536,15001,2314,1,'ElasticSearch,Spring','2022-11-12 00:46:13','2022-12-05 11:12:20',1,0),
                             (10000018,3123000018,'我是标题10000018','我是内容10000018',1,1,1,5839,9964,6403,8385,2291,2,'Linux','2022-11-12 00:46:13','2022-12-05 11:12:20',35,0),
                             (10000019,3123000019,'我是标题10000019','我是内容10000019',1,1,1,11668,8874,17365,19659,15556,3,'Java基础','2022-11-12 00:46:13','2022-12-05 11:12:20',213,0),
                             (10000020,3123000020,'我是标题10000020','我是内容10000020',1,1,1,7734,4201,10791,17822,1593,4,'Golang,SpringMVC','2022-11-12 00:46:14','2022-12-05 11:12:20',199,0),
                             (10000021,3123000021,'我是标题10000021','我是内容10000021',1,1,1,8817,1141,3403,5540,10039,1,'Redis','2022-11-12 00:46:14','2022-12-05 11:12:20',266,0),
                             (10000022,3123000022,'我是标题10000022','我是内容10000022',1,1,1,1625,18396,15798,2725,9841,2,'SpringMVC,ElasticSearch,Spring','2022-11-12 00:46:14','2022-12-05 11:12:20',4,0),
                             (10000023,3123000023,'我是标题10000023','我是内容10000023',1,1,1,3390,14604,15567,16092,12086,3,'Docker','2022-11-12 00:46:14','2022-12-05 11:12:20',217,0),
                             (10000024,3123000024,'我是标题10000024','我是内容10000024',1,1,1,12847,12192,13173,6726,14022,4,'Docker','2022-11-12 00:46:14','2022-12-05 11:12:20',158,0),
                             (10000025,3123000025,'我是标题10000025','我是内容10000025',1,1,1,17899,7074,4244,1057,19245,1,'Git','2022-11-12 00:46:15','2022-12-05 11:12:20',132,0),
                             (10000026,3123000026,'我是标题10000026','我是内容10000026',1,1,1,16033,7027,3166,1730,1760,2,'Vue,Linux','2022-11-12 00:46:15','2022-12-05 11:12:20',71,0),
                             (10000027,3123000027,'我是标题10000027','我是内容10000027',1,1,1,12336,5053,3207,14685,7637,3,'Vue,ElasticSearch','2022-11-12 00:46:15','2022-12-05 11:12:20',170,0),
                             (10000028,3123000028,'我是标题10000028','我是内容10000028',1,1,1,4229,18650,20281,11420,15183,4,'Docker,Redis,Spring','2022-11-12 00:46:15','2022-12-05 11:12:20',134,0),
                             (10000029,3123000029,'我是标题10000029','我是内容10000029',1,1,1,10801,14487,17739,11554,9351,1,'Spring,Spring','2022-11-12 00:46:15','2022-12-05 11:12:20',45,0),
                             (10000030,3123000030,'我是标题10000030','我是内容10000030',1,1,1,12065,17458,8066,10327,17020,2,'Linux,Redis,Java基础','2022-11-12 00:46:16','2022-12-05 11:12:20',292,0),
                             (10000031,3123000031,'我是标题10000031','我是内容10000031',1,1,1,8325,3157,11717,13409,15738,3,'Linux,SpringMVC','2022-11-12 00:46:16','2022-12-05 11:12:20',51,0),
                             (10000032,3123000032,'我是标题10000032','我是内容10000032',1,1,1,2139,2262,2319,7547,20000,4,'Docker,Redis','2022-11-12 00:46:16','2022-12-05 11:12:20',229,0),
                             (10000033,3123000033,'我是标题10000033','我是内容10000033',1,1,1,15969,12328,7720,13938,2678,1,'SpringCloud,Vue','2022-11-12 00:46:16','2022-12-05 11:12:20',195,0),
                             (10000034,3123000034,'我是标题10000034','我是内容10000034',1,1,1,8372,14069,9700,6776,16507,2,'Docker,kubernetes,Vue','2022-11-12 00:46:16','2022-12-05 11:12:20',242,0),
                             (10000035,3123000035,'我是标题10000035','我是内容10000035',1,1,1,19671,722,9029,4846,5456,3,'ElasticSearch','2022-11-12 00:46:17','2022-12-05 11:12:20',55,0),
                             (10000036,3123000036,'我是标题10000036','我是内容10000036',1,1,1,1171,15204,13596,15398,15771,4,'Vue,SpringCloud,Linux','2022-11-12 00:46:17','2022-12-05 11:12:20',62,0),
                             (10000037,3123000037,'我是标题10000037','我是内容10000037',1,1,1,18400,1555,8075,12048,6052,1,'Linux','2022-11-12 00:46:17','2022-12-05 11:12:20',206,0),
                             (10000038,3123000038,'我是标题10000038','我是内容10000038',1,1,1,6534,7539,6439,18574,15909,2,'SpringBoot,Kafka','2022-11-12 00:46:17','2022-12-05 11:12:20',226,0),
                             (10000039,3123000039,'我是标题10000039','我是内容10000039',1,1,1,14773,18484,13957,15403,14687,3,'SpringMVC,Spring','2022-11-12 00:46:17','2022-12-05 11:12:20',161,0),
                             (10000040,3123000040,'我是标题10000040','我是内容10000040',1,1,1,11225,16231,10386,18723,1657,4,'Mybatis','2022-11-12 00:46:18','2022-12-05 11:12:20',283,0),
                             (10000041,3123000041,'我是标题10000041','我是内容10000041',1,1,1,4399,13202,19984,991,18646,1,'Spring,kubernetes','2022-11-12 00:46:18','2022-12-05 11:12:20',283,0),
                             (10000042,3123000042,'我是标题10000042','我是内容10000042',1,1,1,2050,10404,5148,8889,19317,2,'Redis,Mybatis','2022-11-12 00:46:18','2022-12-05 11:12:20',141,0),
                             (10000043,3123000043,'我是标题10000043','我是内容10000043',1,1,1,1956,3376,3462,4525,16838,3,'SpringBoot,Redis,Git','2022-11-12 00:46:18','2022-12-05 11:12:20',86,0),
                             (10000044,3123000044,'我是标题10000044','我是内容10000044',1,1,1,11447,5019,13850,8856,18729,4,'Java基础','2022-11-12 00:46:18','2022-12-05 11:12:20',276,0),
                             (10000045,3123000045,'我是标题10000045','我是内容10000045',1,1,1,8472,14285,10504,9275,19956,1,'SpringMVC,Vue','2022-11-12 00:46:19','2022-12-05 11:12:20',257,0),
                             (10000046,3123000046,'我是标题10000046','我是内容10000046',1,1,1,14732,15160,16712,4620,4622,2,'Mybatis','2022-11-12 00:46:19','2022-12-05 11:12:20',223,0),
                             (10000047,3123000047,'我是标题10000047','## Java手写线程池（第一代）

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

**后续还会继续出关于作者手写Spring框架，手写Tomcat等等框架的博文！！！！！**',1,1,1,20267,7193,10775,12897,11331,3,'Spring,Python','2022-11-12 00:46:19','2022-12-05 11:12:20',260,0),
                             (10000048,3123000048,'我是标题10000048','我是内容10000048',1,1,1,3455,14541,18563,1752,18185,4,'SpringMVC,Redis,Vue','2022-11-12 00:46:19','2022-12-05 11:12:20',122,0),
                             (10000049,3123000049,'我是标题10000049','我是内容10000049',1,1,1,19501,14258,6803,4078,19080,1,'Mybatis,SpringBoot,SpringCloud','2022-11-12 00:46:20','2022-12-05 11:12:20',141,0),
                             (10000050,3123000050,'我是标题10000050','我是内容10000050',1,1,1,8503,6331,18257,12677,11553,2,'Docker,Git,Python','2022-11-12 00:46:20','2022-12-05 11:12:20',282,0);

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
                                 (5639292067841029,10000001,10017),
                                 (5639292067841030,10000001,10014),
                                 (5639292067841031,10000001,10017),
                                 (5639292081538053,10000002,10003),
                                 (5639292081538054,10000002,10006),
                                 (5639292081538055,10000002,10006),
                                 (5639292095235077,10000003,10004),
                                 (5639292095235078,10000003,10016),
                                 (5639292108342277,10000004,10006),
                                 (5639292121515013,10000005,10010),
                                 (5639292121515014,10000005,10010),
                                 (5639292134884357,10000006,10003),
                                 (5639292134884358,10000006,10010),
                                 (5639292134884359,10000006,10001),
                                 (5639292148319237,10000007,10008),
                                 (5639292148319238,10000007,10013),
                                 (5639292162081797,10000008,10012),
                                 (5639292162081798,10000008,10009),
                                 (5639292162081799,10000008,10013),
                                 (5639292175844357,10000009,10016),
                                 (5639292175844358,10000009,10011),
                                 (5639292175844359,10000009,10010),
                                 (5639292189017093,10000010,10001),
                                 (5639292202779653,10000011,10015),
                                 (5639292216476677,10000012,10004),
                                 (5639292229649413,10000013,10002),
                                 (5639292243411973,10000014,10001),
                                 (5639292257108997,10000015,10016),
                                 (5639292257108998,10000015,10015),
                                 (5639292270281733,10000016,10012),
                                 (5639292284044293,10000017,10014),
                                 (5639292284044294,10000017,10002),
                                 (5639292297282565,10000018,10011),
                                 (5639292310914053,10000019,10001),
                                 (5639292324086789,10000020,10017),
                                 (5639292324086790,10000020,10006),
                                 (5639292337783813,10000021,10005),
                                 (5639292350956549,10000022,10006),
                                 (5639292350956550,10000022,10014),
                                 (5639292350956551,10000022,10002),
                                 (5639292364653573,10000023,10008),
                                 (5639292378416133,10000024,10008),
                                 (5639292391523333,10000025,10013),
                                 (5639292404696069,10000026,10012),
                                 (5639292404696070,10000026,10011),
                                 (5639292418327557,10000027,10012),
                                 (5639292418327558,10000027,10014),
                                 (5639292431434757,10000028,10008),
                                 (5639292431434758,10000028,10005),
                                 (5639292431434759,10000028,10002),
                                 (5639292445197317,10000029,10002),
                                 (5639292445197318,10000029,10002),
                                 (5639292458501125,10000030,10011),
                                 (5639292458501126,10000030,10005),
                                 (5639292458501127,10000030,10001),
                                 (5639292472787973,10000031,10011),
                                 (5639292472787974,10000031,10006),
                                 (5639292486484997,10000032,10008),
                                 (5639292486484998,10000032,10005),
                                 (5639292500182021,10000033,10004),
                                 (5639292500182022,10000033,10012),
                                 (5639292513944581,10000034,10008),
                                 (5639292513944582,10000034,10009),
                                 (5639292513944583,10000034,10012),
                                 (5639292527117317,10000035,10014),
                                 (5639292540290053,10000036,10012),
                                 (5639292540290054,10000036,10004),
                                 (5639292540290055,10000036,10011),
                                 (5639292554052613,10000037,10011),
                                 (5639292567159813,10000038,10003),
                                 (5639292567159814,10000038,10010),
                                 (5639292580267013,10000039,10006),
                                 (5639292580267014,10000039,10002),
                                 (5639292593505285,10000040,10007),
                                 (5639292606612485,10000041,10002),
                                 (5639292606612486,10000041,10009),
                                 (5639292620243973,10000042,10005),
                                 (5639292620243974,10000042,10007),
                                 (5639292633351173,10000043,10003),
                                 (5639292633351174,10000043,10005),
                                 (5639292633351175,10000043,10013),
                                 (5639292647048197,10000044,10001),
                                 (5639292660220933,10000045,10006),
                                 (5639292660220934,10000045,10012),
                                 (5639292673983493,10000046,10007),
                                 (5639292687680517,10000047,10002),
                                 (5639292687680518,10000047,10016),
                                 (5639292701443077,10000048,10006),
                                 (5639292701443078,10000048,10005),
                                 (5639292701443079,10000048,10012),
                                 (5639292715271173,10000049,10007),
                                 (5639292715271174,10000049,10003),
                                 (5639292715271175,10000049,10004),
                                 (5639292729099269,10000050,10008),
                                 (5639292729099270,10000050,10013),
                                 (5639292729099271,10000050,10016);



/* 面试题点赞表 */
DROP TABLE IF EXISTS `t_question_like`;

CREATE TABLE `t_question_like`(
                                  `id` bigint(20) NOT NULL COMMENT '主键',
                                  `user_id` bigint(20) NULL COMMENT '用户id',
                                  `question_id` bigint(20) NULL COMMENT '被点赞的面试题id',
                                  `create_time` datetime DEFAULT NULL COMMENT '点赞时间',
                                  PRIMARY KEY (`id`),
                                  INDEX `idx_uid` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题点赞表';

/* 面试题收藏表 */
DROP TABLE IF EXISTS `t_question_collect`;

CREATE TABLE `t_question_collect`(
                                     `id` bigint(20) NOT NULL COMMENT '主键',
                                     `user_id` bigint(20) NULL COMMENT '用户id',
                                     `question_id` bigint(20) NULL COMMENT '被收藏的面试题id',
                                     `create_time` datetime DEFAULT NULL COMMENT '收藏时间',
                                     PRIMARY KEY (`id`),
                                     INDEX `idx_uid` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题收藏表';

/* 评论表 */

/* 遇见面试题表 */
