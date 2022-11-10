DROP DATABASE IF EXISTS `coding-guide-db`;
CREATE DATABASE `coding-guide-db`;

USE `coding-guide-db`;

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

INSERT INTO `t_question`
VALUES (10000001, 3123000001,
        '我是标题10000001',
        '我是内容10000001',
        1, 1, 1, 2969, 3822, 19310, 20340, 7358, 1,'Vue,前端,SpringCloud',
        '2022-11-10 15:19:00','2022-12-05 11:12:20',249,0);
INSERT INTO `t_question`
VALUES (10000002, 3123000002,
        '我是标题10000002',
        '我是内容10000002',
        1, 1, 1, 9190, 16787, 6953, 18241, 3606, 2,'Redis,Git',
        '2022-11-10 15:19:01','2022-12-05 11:12:20',184,0);
INSERT INTO `t_question`
VALUES (10000003, 3123000003,
        '我是标题10000003',
        '我是内容10000003',
        1, 1, 1, 10455, 18019, 6609, 16635, 12713, 3,'前端,Spring,kubernetes',
        '2022-11-10 15:19:01','2022-12-05 11:12:20',282,0);
INSERT INTO `t_question`
VALUES (10000004, 3123000004,
        '我是标题10000004',
        '我是内容10000004',
        1, 1, 1, 18170, 3002, 11113, 19270, 3961, 4,'SpringMVC',
        '2022-11-10 15:19:01','2022-12-05 11:12:20',94,0);
INSERT INTO `t_question`
VALUES (10000005, 3123000005,
        '我是标题10000005',
        '我是内容10000005',
        1, 1, 1, 9535, 7396, 1867, 2417, 609, 1,'Git,SpringBoot,Mybatis',
        '2022-11-10 15:19:01','2022-12-05 11:12:20',164,0);
INSERT INTO `t_question`
VALUES (10000006, 3123000006,
        '我是标题10000006',
        '我是内容10000006',
        1, 1, 1, 4901, 7290, 11331, 13881, 13067, 2,'Docker,Linux',
        '2022-11-10 15:19:01','2022-12-05 11:12:20',240,0);
INSERT INTO `t_question`
VALUES (10000007, 3123000007,
        '我是标题10000007',
        '我是内容10000007',
        1, 1, 1, 16615, 9134, 20468, 10616, 7339, 3,'SpringMVC',
        '2022-11-10 15:19:02','2022-12-05 11:12:20',88,0);
INSERT INTO `t_question`
VALUES (10000008, 3123000008,
        '我是标题10000008',
        '我是内容10000008',
        1, 1, 1, 10424, 6703, 16930, 13414, 16136, 4,'kubernetes,ElasticSearch',
        '2022-11-10 15:19:02','2022-12-05 11:12:20',216,0);
INSERT INTO `t_question`
VALUES (10000009, 3123000009,
        '我是标题10000009',
        '我是内容10000009',
        1, 1, 1, 11635, 7324, 3352, 14867, 4096, 1,'SpringBoot',
        '2022-11-10 15:19:02','2022-12-05 11:12:20',85,0);
INSERT INTO `t_question`
VALUES (10000010, 3123000010,
        '我是标题10000010',
        '我是内容10000010',
        1, 1, 1, 11081, 9203, 5303, 15942, 1440, 2,'Docker,Redis',
        '2022-11-10 15:19:02','2022-12-05 11:12:20',3,0);
INSERT INTO `t_question`
VALUES (10000011, 3123000011,
        '我是标题10000011',
        '我是内容10000011',
        1, 1, 1, 3173, 11077, 18955, 1449, 16038, 3,'前端',
        '2022-11-10 15:19:02','2022-12-05 11:12:20',97,0);
INSERT INTO `t_question`
VALUES (10000012, 3123000012,
        '我是标题10000012',
        '我是内容10000012',
        1, 1, 1, 9820, 7676, 5265, 12270, 15862, 4,'Spring,Spring,Golang',
        '2022-11-10 15:19:03','2022-12-05 11:12:20',133,0);
INSERT INTO `t_question`
VALUES (10000013, 3123000013,
        '我是标题10000013',
        '我是内容10000013',
        1, 1, 1, 8746, 17479, 3502, 19125, 6748, 1,'前端,Python,Vue',
        '2022-11-10 15:19:03','2022-12-05 11:12:20',203,0);
INSERT INTO `t_question`
VALUES (10000014, 3123000014,
        '我是标题10000014',
        '我是内容10000014',
        1, 1, 1, 8592, 10133, 12714, 9805, 11024, 2,'Linux,Vue,Vue',
        '2022-11-10 15:19:03','2022-12-05 11:12:20',253,0);
INSERT INTO `t_question`
VALUES (10000015, 3123000015,
        '我是标题10000015',
        '我是内容10000015',
        1, 1, 1, 14014, 20154, 7150, 12999, 4039, 3,'Docker',
        '2022-11-10 15:19:03','2022-12-05 11:12:20',262,0);
INSERT INTO `t_question`
VALUES (10000016, 3123000016,
        '我是标题10000016',
        '我是内容10000016',
        1, 1, 1, 13005, 1441, 15822, 10078, 7539, 4,'Golang,SpringBoot',
        '2022-11-10 15:19:03','2022-12-05 11:12:20',109,0);
INSERT INTO `t_question`
VALUES (10000017, 3123000017,
        '我是标题10000017',
        '我是内容10000017',
        1, 1, 1, 13413, 12512, 8812, 10797, 9863, 1,'SpringCloud',
        '2022-11-10 15:19:04','2022-12-05 11:12:20',67,0);
INSERT INTO `t_question`
VALUES (10000018, 3123000018,
        '我是标题10000018',
        '我是内容10000018',
        1, 1, 1, 16479, 17770, 11333, 12510, 13948, 2,'SpringMVC',
        '2022-11-10 15:19:04','2022-12-05 11:12:20',256,0);
INSERT INTO `t_question`
VALUES (10000019, 3123000019,
        '我是标题10000019',
        '我是内容10000019',
        1, 1, 1, 11268, 14559, 6261, 16267, 5561, 3,'SpringCloud',
        '2022-11-10 15:19:04','2022-12-05 11:12:20',89,0);
INSERT INTO `t_question`
VALUES (10000020, 3123000020,
        '我是标题10000020',
        '我是内容10000020',
        1, 1, 1, 9255, 7202, 16596, 16162, 5677, 4,'kubernetes',
        '2022-11-10 15:19:04','2022-12-05 11:12:20',154,0);
INSERT INTO `t_question`
VALUES (10000021, 3123000021,
        '我是标题10000021',
        '我是内容10000021',
        1, 1, 1, 13542, 11192, 4627, 4064, 8371, 1,'Spring',
        '2022-11-10 15:19:04','2022-12-05 11:12:20',289,0);
INSERT INTO `t_question`
VALUES (10000022, 3123000022,
        '我是标题10000022',
        '我是内容10000022',
        1, 1, 1, 17183, 12633, 17422, 17312, 10484, 2,'Python,Mybatis,前端',
        '2022-11-10 15:19:05','2022-12-05 11:12:20',146,0);
INSERT INTO `t_question`
VALUES (10000023, 3123000023,
        '我是标题10000023',
        '我是内容10000023',
        1, 1, 1, 16523, 11578, 17383, 11304, 7706, 3,'前端,SpringMVC,Java基础',
        '2022-11-10 15:19:05','2022-12-05 11:12:20',157,0);
INSERT INTO `t_question`
VALUES (10000024, 3123000024,
        '我是标题10000024',
        '我是内容10000024',
        1, 1, 1, 16483, 19947, 19614, 4891, 12237, 4,'Git',
        '2022-11-10 15:19:05','2022-12-05 11:12:20',66,0);
INSERT INTO `t_question`
VALUES (10000025, 3123000025,
        '我是标题10000025',
        '我是内容10000025',
        1, 1, 1, 5304, 5655, 1722, 13300, 16193, 1,'Redis',
        '2022-11-10 15:19:05','2022-12-05 11:12:20',298,0);
INSERT INTO `t_question`
VALUES (10000026, 3123000026,
        '我是标题10000026',
        '我是内容10000026',
        1, 1, 1, 5127, 15922, 6894, 14319, 19034, 2,'前端,kubernetes',
        '2022-11-10 15:19:05','2022-12-05 11:12:20',298,0);
INSERT INTO `t_question`
VALUES (10000027, 3123000027,
        '我是标题10000027',
        '我是内容10000027',
        1, 1, 1, 14052, 2745, 16662, 17498, 4504, 3,'Vue',
        '2022-11-10 15:19:06','2022-12-05 11:12:20',116,0);
INSERT INTO `t_question`
VALUES (10000028, 3123000028,
        '我是标题10000028',
        '我是内容10000028',
        1, 1, 1, 13946, 5928, 15918, 12534, 3260, 4,'Docker,Mybatis',
        '2022-11-10 15:19:06','2022-12-05 11:12:20',179,0);
INSERT INTO `t_question`
VALUES (10000029, 3123000029,
        '我是标题10000029',
        '我是内容10000029',
        1, 1, 1, 2742, 18691, 3145, 20492, 19300, 1,'ElasticSearch,Mybatis',
        '2022-11-10 15:19:06','2022-12-05 11:12:20',25,0);
INSERT INTO `t_question`
VALUES (10000030, 3123000030,
        '我是标题10000030',
        '我是内容10000030',
        1, 1, 1, 5323, 7454, 1180, 20281, 16956, 2,'前端,前端,SpringCloud',
        '2022-11-10 15:19:06','2022-12-05 11:12:20',79,0);
INSERT INTO `t_question`
VALUES (10000031, 3123000031,
        '我是标题10000031',
        '我是内容10000031',
        1, 1, 1, 2084, 13878, 11024, 16046, 19250, 3,'SpringCloud,Linux',
        '2022-11-10 15:19:06','2022-12-05 11:12:20',134,0);
INSERT INTO `t_question`
VALUES (10000032, 3123000032,
        '我是标题10000032',
        '我是内容10000032',
        1, 1, 1, 14905, 12305, 7104, 14484, 8127, 4,'Vue,SpringMVC,前端',
        '2022-11-10 15:19:07','2022-12-05 11:12:20',112,0);
INSERT INTO `t_question`
VALUES (10000033, 3123000033,
        '我是标题10000033',
        '<div id=\"content_views\" class=\"markdown_views prism-github-gist\"> \n <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"display: none;\"> \n  <path stroke-linecap=\"round\" d=\"M5,0 0,2.5 5,5z\" id=\"raphael-marker-block\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></path> \n </svg> \n <p><img src=\"https://img-blog.csdnimg.cn/20210607083354128.png\" alt=\"\"><br> 作者：小傅哥<br> 博客：<a href=\"https://bugstack.cn\">https://bugstack.cn</a></p> \n <blockquote> \n  <p>沉淀、分享、成长，让自己和他人都能有所收获！😄</p> \n </blockquote> \n <h2><a id=\"_6\"></a>一、前言</h2> \n <p><code>给你一台服务器，你能把你写的代码部署到线上吗？</code></p> \n <p>我们常常会听到这样一句话：“为了让研发只关心业务开发，我们做了某某某！”做了啥呢，做了让你不用关心，<code>系统搭建</code>、<code>技术框架</code>、<code>核心组件</code>、<code>通用模块</code>以及上线应用时也只是点点点就可以了，也根本了解不到一台应用服务器是如何，<code>部署环境</code>、<code>开通端口</code>、<code>申请域名</code>、<code>配置SSL</code>的。所以呢，大多数人的你变得越来越像车间中单一岗位的工具人，想在公司走到更高的岗位或者出了公司想做点事情，都会成为你的瓶颈！</p> \n <p><code>一套完整的能力范围，要涵盖哪些方面？</code></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/82c95cd4cc7823a4ac8e0451aa0dfbd7.png\" alt=\"\"></p> \n <ul>\n  <li>当我们以一个需求的诞生从承接到上线，这个过程中大概会经历的角色包括：业务、运营、产品、数据、研发(UI)、测试和运维，产品运用数据和模型，量化业务提出的需求，该如何迭代实现，满足运营使用完成业务目标，再由UI、前后端研发、测试完成项目的开发和验证以及部署到运维配置的线上环境中。</li>\n  <li>站在程序员的角色上以这一整套流程来看，其实很大一部分研发人员只能在编程开发的范围内互动，从技术角色上离的最近的是测试和上线部署，但如果让研发自己去部署测试环境，搭建线上环境就会非常困难，不是说技术层面有多难，而是这个事情几乎就没有经历过，也没想过要去做一做试试。</li>\n  <li>业务、运营、产品、数据中的模型、算法、量化，可能这一部分里研发人员就更远了，压根就不清楚因为什么场景、提出了什么目的、做了怎么的评估、提出了那些手段以及该如何落地，而到研发这能看到的可能仅仅是等待执行的 PRD，正因为总总是这样，所以才有那句话：“你离开公司可能就什么也干不了了！”</li>\n </ul> \n <p><strong>接下来</strong>，给小伙伴讲讲，我对热爱事情的折腾，不只是技术视野范围的拓宽，也可能让你有些意外收获！</p> \n <h2><a id=\"_22\"></a>二、在服务器上花出去的钱！</h2> \n <p><code>这种事你们可以花点钱嘛,花点,哪怕要呢，要不了多少钱！</code></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/6be144e428e2b754e62d81a15bbf8e36.png\" alt=\"\"></p> \n <p><strong>汤师爷说，花点钱，我听进去了！</strong></p> \n <p>其实我一直从不会吝啬于技术学习上的消费，也不会把时间浪费到非个人能长期成长的其他做兼职的事上。从13年毕业工作开始，因为赚钱少，合租的几个伙伴们也有人会出去找点兼职赚钱，我是属于那种不但没周末去赚钱，还把额外省下的钱都买了域名和服务器，从最早的主机屋到百度开始有BCH云服务，也看过七牛云还用过百度云存储，一路折腾下来服务器上也花费了上万块。</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/8cb3f4cccd0d29fe1ee8caa73cddfae6.png\" alt=\"\"></p> \n <p>这些钱都买啥了？仅域名就买过一堆，包括：itstack.org、yuyueqianli.com、fuzhengwe.cn、linuxjar.org、iteuds.cn、<a href=\"https://bugstack.cn/\">bugstack.cn</a>等等，那服务器呢？服务器除了正常消费的，还买终身的！！！</p> \n <p><strong>虽然</strong>，花了不少钱，但也正是因为这些消费和不断的倒腾，让我学会了<code>域名注册</code>、<code>域名备案</code>、<code>域名配置(A记录、CNAME记录、TXT)</code>、<code>证书申请</code>、<code>服务搭建</code>、<code>宝塔应用</code>、<code>配置环境</code>、<code>Linux命令</code>等等。当你有一条具体要做的事情时，你会以这条路径为指导，不断的搜索相应的资料并实践<code>造作</code>！</p> \n <p><strong>造作</strong>，出第一个能看得过去的论坛，拥有稍许的流量！</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/a418e035fc49ba66850c9901f8f0a477.png\" alt=\"\"></p> \n <ul>\n  <li>不过后来由于干不过一些流氓似的攻击以及 org 域名备案的影响，最终这个小论坛也挂在了奔走的路上。</li>\n  <li>不过好在网站没白<code>死</code>，从这里面还是学到了很多东西，包括：部署、上线，运维，在运维过程中发现的一些流量峰值、缓存处理、防刷处理、防盗链处理、用户注册与QQ关联、改造原有php代码，支持一些功能等等，挂的只是网站，但留下的是技术经验！</li>\n </ul> \n <h2><a id=\"_45\"></a>三、把花出去的钱赚回来了！</h2> \n <p><code>我这钱是怎么赚回来的？</code></p> \n <p>在我搭建论坛、博客、贴吧似的功能并逐步有些许后，就开始有人联系我能不能给他们做一个这样的网站或者企业门户网站。正好当时还在传统行业的我，也有不少业余时间，每天五点半就能下班，当然有时间搞了。好！说干就干，一顿操作下，2年内接了不少私活，也赚了几万块，算是把服务器、域名的钱都赚回来了，也算没白折腾！</p> \n <h3><a id=\"1_5000_51\"></a>1. 企业门户网站(5000元)</h3> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/a5119123c3fea8ef13f312155d1739ae.png\" alt=\"企业门户网站(5000元)\"></p> \n <ul>\n  <li><strong>指数</strong>：⭐⭐⭐⭐</li>\n  <li><strong>背景</strong>：刚上班一年左右，高中同学问我学计算机能帮他们公司做个网站吗，就模仿老罗那个锤子公司的样式就行，5000块钱。</li>\n  <li><strong>结果</strong>：我接了，可能也是初生牛犊不怕虎，人家需要用PHP语言写！我一个学Java的，写了快一年的C#，之后用PHP给人家做一个企业门户网站，该说不说胆子挺大！</li>\n  <li><strong>收获</strong>：项目顺利部署完成，5000块钱如约到手，买了我第一个苹果手机 iPhone 4s，仍然在我身边。</li>\n </ul> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/f87bcb1aa8e3fa56afa8e6971f00a43a.png\" alt=\"我第一个苹果手机 iPhone 4s\"></p> \n <h3><a id=\"2_2000_62\"></a>2. 卖家具宣传网站(2000元)</h3> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/a8ddef4c68e2923f2cb011ff02b2ed54.png\" alt=\"卖家具宣传网站(2000元)\"></p> \n <ul>\n  <li><strong>指数</strong>：⭐⭐⭐</li>\n  <li><strong>背景</strong>：14年年初，亲戚家开了一个制作水族箱的小作坊，也是得知我是学计算的。锣鼓喧天的找到我说做一个宣传他们公司商品的网站<em>外面找人做太贵了！</em>。</li>\n  <li><strong>结果</strong>：💰钱咱也不好意思要，只是把服务器和域名等费用的钱要了，不过后来给了我个大红包 2000 元，嘿嘿，手一抖，收了！</li>\n  <li><strong>收获</strong>：得益于我已经接过一个项目，所以PHP开发起来也是很容易，按照他们当时喜欢的样式，做了一个仿照点点网的风格网站布局。这次赚的钱交房租了！</li>\n </ul> \n <h3><a id=\"3_Netty2000_71\"></a>3. Netty通信框架(2000元)</h3> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/bc12f2677fad449e1f0d8fb27e33eac3.png\" alt=\"Netty通信框架(2000元)\"></p> \n <ul>\n  <li><strong>指数</strong>：⭐⭐⭐</li>\n  <li><strong>背景</strong>：14年左右，开始喜欢搞Netty。可能也是当时网上的资料并不多，很多人因为我写了一整套的Netty案例找到我。也就有了这么一次问我能给写个Netty的通信框架不，2000元。</li>\n  <li><strong>结果</strong>：这也是当时头一次不用PHP，而是用Java语言赚到的钱。对我来说还是蛮简单的，1个5:30下班回家就写完了，第二天就给过去了。</li>\n  <li><strong>收获</strong>：知识真的可以变成钱，尤其是那些稍微有点难度，又搞的人不多的时候。</li>\n </ul> \n <h3><a id=\"4_11000_80\"></a>4. 讲课、数据采集(11000元)</h3> \n <p>除了上面接到的私活，还接到了不少七七八八的小活。</p> \n <ul>\n  <li>本科生设计指导，1000元。来自猪八戒网。</li>\n  <li>研究生加密算法，2000元。一个研究生伙伴跟我一起设计出来的，给我从他们学院申请的费用。</li>\n  <li>在线给一个学生讲课，好像一天是50元，将来快1个月，1000元。</li>\n  <li>一个物流数据综合平台，其实功能不算多，有点像记录外贸订单的，5000元。</li>\n  <li>协助一个自己接项目的老板，写了一周Netty编解码部分代码，对接下位机。2000元。</li>\n </ul> \n <p>就这样，七七八八的在那两年，赚了2万多块钱。当然还有一部分小的收入，不足1000的。也有被骗过，比如人家拿到项目了就不给钱了或是拿到截图了「<em>我没加水印</em>」，人家够演示的了，也不给钱了。</p> \n <h2><a id=\"_91\"></a>四、搞一台服务器咋用起来？</h2> \n <p><code>接下来，教教你怎么把一个服务器用起来！</code></p> \n <p>对于一个在校的学校来说，或者是已经工作了，但从没有了解或者接触过服务器的配置，以及如何把自己的代码运行到服务器上。那么你可以参考下面的教程介绍，按照这样一个入门的指导把自己的代码也部署到服务器上试试。</p> \n <p>可能还有很多小伙伴都不知道服务器能干嘛，简单来说，这就是不在家里，你的一台虚拟电脑，而且是 24小时运行不宕机的，你可以在上面练习网络编程(有公网IP)、中转服务器，以及如下：</p> \n <ol>\n  <li>搭博客：https://mp.weixin.qq.com/s/ZoQ0xAphJQkP_pb8H08BMg</li>\n  <li>搞论坛：phpwind、Discuz、wordpress(有博客和论坛等模板)</li>\n  <li>弄网盘：https://mp.weixin.qq.com/s/gzUrFexHcyCrw7XZ_L7N7w</li>\n  <li>聊天室：https://mp.weixin.qq.com/s/OmXCY4fTfDpkvjlg5ME0ZA</li>\n  <li>其他的：练习下自己的项目、搞个集群、玩玩ES、弄弄实战、留着接私活给别人部署演示</li>\n </ol> \n <p>而这些内容的练习，都能让你把一整套从研发到运维的内容玩透，彻底的了解域名、备案、ssl、宝塔、Linux常用命令等等。</p> \n <h3><a id=\"1_neng_107\"></a>1. 先neng个服务器</h3> \n <p>首先，无论你是否有服务器，你都可以跟小傅哥一起学习关于服务器的使用，我们建了个群专门学习服务器，添加我的微信：fustack，备注：<code>服务器学习加群</code>。</p> \n <p>如果你还是一个新用户小白，那么可以跟着我的流程一起来，先neng一个便宜的服务器，学习使用即可。这里小傅哥给新人弄了个活动，79元即可买一台一年有效期的服务器，还是比我以前买的便宜多了！</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/2b72f69b94801bbdc78f0bef8f410448.png\" alt=\"\"></p> \n <ul>\n  <li>链接：<a href=\"https://dashi.aliyun.com/site/xiaofuge/618\">https://dashi.aliyun.com/site/xiaofuge/618</a></li>\n </ul> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/17a242c46b9abdd94a75a4bde279f862.png\" alt=\"\"></p> \n <ul>\n  <li>当你购买服务器的时候会看到，<code>地域</code>、<code>实例</code>、<code>操作系统</code>等，地域北京、上海、杭州的网速比较好，张家口的便宜但是网速会比较慢。操作系统默认即可，停机后可以更换。</li>\n </ul> \n <h3><a id=\"2__121\"></a>2. 服务器介绍</h3> \n <p>在购买完服务器后，等待云平台数分钟初始化服务，完事就可以直接使用配置。如下：</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/7add39e8953d975bcb5c4029cc86b2f8.png\" alt=\"\"></p> \n <ul>\n  <li>重置密码：点击你的实例，蓝色的这个字母，进入后再右侧有一个，<code>重置实例密码</code>，操作。</li>\n  <li>远程链接：点击<code>远程链接</code>即可链接到你服务上，它是一个在线的操作。你可以通过本地的软件 xshell 链接到服务上去。</li>\n  <li>更换系统：如果你对自己默认选择的系统不是很满意或者有其他需求，都可以点击停止系统，之后开始操作系统更换。</li>\n </ul> \n <h3><a id=\"3__131\"></a>3. 系统更换成宝塔镜像</h3> \n <p>对于服务器系统来说你可以使用Linux命令安装各项服务组件，例如k8s、docker、jdk、tomcat、mysql或者php需要的内容等，但对于实际使用的运维来说，我们更希望运维成本越低越好，所以这里我们选择了<a href=\"https://www.bt.cn/\">宝塔</a>，这样一个服务器运维面板来管理我们的服务器。</p> \n <p>在各类的云平台上，包括：百度云、华为云、阿里云、腾讯云，都可以安装宝塔的，有的云平台还会有自己的已经准备好的宝塔镜像，这里我们以阿里云服务为例，把系统停机更好为宝塔。</p> \n <p><strong>停机</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/2608c946b11f88c4ba6ef84092645d8e.png\" alt=\"\"></p> \n <ul>\n  <li>位置：点击云服务的实例，就可以进入到这个页面</li>\n  <li>操作：更换系统之前我们需要先进行停机操作，停机后就可以点击更换操作系统了</li>\n </ul> \n <p><strong>换系统</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/400e64db8d0349ae562a2a5d4328f053.png\" alt=\"\"></p> \n <p><strong>选镜像</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/077a9049f8219c4ebea4e5855abc8fc6.png\" alt=\"\"></p> \n <ul>\n  <li>更换完系统进行确认订单，接下来会跳转到管理后台，这时稍等会，服务器会进行启动。</li>\n </ul> \n <h3><a id=\"4__154\"></a>4. 配置并登录宝塔</h3> \n <p><strong>远程登录</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/85e7a912cfd3d8ceaef90ba6dcb71763.png\" alt=\"\"></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/dabd88651e1ca66ec96786fdfc1aba4f.png\" alt=\"\"></p> \n <ul>\n  <li>这一步我们直接在网页上登录了，你也可以使用 xshell 登录公网IP</li>\n </ul> \n <p><strong>初始化宝塔</strong></p> \n <p>命令：<code>[root@CodeGuide ~]# bt default</code></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/06e02f3b593ed8705b4ed24f5bab917f.png\" alt=\"\"></p> \n <p><strong>配置安全组</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/46527a8a5be6a7593a7b1d72438d1d27.png\" alt=\"\"></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/e0233df34b4b5ee45b36f8799319fd24.png\" alt=\"\"></p> \n <ul>\n  <li>宝塔的访问要配置 8888 端口，否则是不能访问到的，这个在服务器的安全组中开放即可。</li>\n  <li>这里我们为了方便就直接开启全部的了，如果你是实际使用，可不能这样操作，否则很不安全！</li>\n </ul> \n <p><strong>登录宝塔</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/00e6aa37f341af15e425e9dde398fd6c.png\" alt=\"\"></p> \n <ul>\n  <li>地址：http://39.96.73.xxx:8888/ - <code>换成你的地址</code></li>\n  <li>账号：用户名和密码已经在控制台打印，你可以复制自己的，登录宝塔后可以修改这个默认的密码</li>\n </ul> \n <h3><a id=\"5_FTP_186\"></a>5. 安装阿帕奇和FTP</h3> \n <p>接下来我们在宝塔中安装一个阿帕奇服务器和FTP，这样就可以部署和访问我们的静态博客了，也就是一个html，如下:</p> \n <p><strong>安装 Apache</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/7a031021a8fad18b360c6b3a441b9e27.png\" alt=\"\"></p> \n <ul>\n  <li>安装过程中会自动的执行一些命令，这个你不用管，只要默默看着就行了。</li>\n </ul> \n <p><strong>安装 FTP</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/1562b929718814a451b5060b3a0b1f90.png\" alt=\"\"></p> \n <ul>\n  <li>安装 FTP 主要是为了通过本地可以把文件传送到服务器上，比如你的一个静态博客是 html，就可以通过 FTP 传到服务器上。</li>\n </ul> \n <h3><a id=\"6__202\"></a>6. 网站配置</h3> \n <p>安装了阿帕奇和FTP我们就可以简单的配置一个站点了，有了这个站点就可以访问到我们自己的博客！</p> \n <p><strong>创建站点</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/d7ad92cd0f9a6e607e13b81f491ac8aa.png\" alt=\"\"></p> \n <ul>\n  <li>创建站点的适合如果你还没有申请域名，或者域名还没有备案呢，那么就可以直接把公网IP填写进来。</li>\n </ul> \n <p><strong>访问站点</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/4ec171a501ed087b11c7a3e7ed9e8e4d.png\" alt=\"\"></p> \n <ul>\n  <li>地址：http://39.96.73.167/　在访问的时候，你换成自己的ＩＰ即可</li>\n </ul> \n <h3><a id=\"7__218\"></a>7. 网站内容</h3> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/79dda355a45e86d6e393c6e236ca48ce.png\" alt=\"\"></p> \n <ul>\n  <li>在宝塔的文件里，你可以选择第6步骤中添加的站点，在里面找到你的文件，做一些修改动作。这个时候在访问网站，就会发现内容已经是你新的内容了。</li>\n </ul> \n <h3><a id=\"8__224\"></a>8. 域名配置</h3> \n <p>如果你有域名并已经备案好了，那么在创建站点的时候就可以直接把域名配置上，在访问你的网站的时候就可以通过域名访问了。</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/696037e35bee34291f4b82446d228970.png\" alt=\"\"></p> \n <ul>\n  <li>添加域名：这个里面小傅哥配置的是已经申请好并备案了的域名，你配置成你的就可以。记得配置好域名后，需要在你的域名服务里，通过A记录把服务器IP映射配置上去。</li>\n  <li>FTP 配置：为了更加方便的上传你的文件，你可以把FTP打开，这样就可以通过FTP传输配置了。</li>\n  <li>访问地址：<a href=\"http://blog.itedus.cn/\">http://blog.itedus.cn</a> - <code>由于域名不是在阿里云，可能http会监测为未备案，拒绝访问</code></li>\n </ul> \n <h3><a id=\"9_SSL__234\"></a>9. SSL 配置</h3> \n <p>关于 SSL 的申请可以有很多免费网站提供，也可以在宝塔中申请，如果你是用阿里云服务，可以免费申请20个 SSL 证书，另外如果你的域名和服务都是在阿里云，那么在申请 SSL 可以直接走 DNS 认证，否则你需要把 DNS 信息手动配置到你自己的域名上去。<em>放心这个在申请的时候都有提示，按照说明配置即可</em></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/daa6fba2f21645a56fa5a8eb28faaf94.png\" alt=\"\"></p> \n <p><strong>下载证书</strong></p> \n <p>因为我们需要把 ssl 配置到宝塔上，所以这里需要把 SSL 下载下来，选择 Apache 格式下载。</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/b8f8c0bf71adb646a4d0e13a941199a5.png\" alt=\"\"></p> \n <p><strong>配置证书</strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/1bc8ea974f73ddadb4c1a187f24174ca.png\" alt=\"\"></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/ef763394bdd9feecc1d1afdf9d732717.png\" alt=\"\"></p> \n <ul>\n  <li>配置后点击保存即可，另外需要强制开启 HTTPS，否则你的网站访问 http 也能继续访问，就没有意义了。</li>\n  <li>现在你就可以通过 https，访问自己的博客或者网站了，是不看上去高大上了不少！</li>\n </ul> \n <h3><a id=\"10__255\"></a>10. 其他说明</h3> \n <p>可能你还希望配置 jdk、tomcat，没关系，在宝塔里你都可以安装，也可以安装 mysql，有了这些入门的内容，剩下的就可以搜索一些通用配置的内容，也可以在阿里云中搜索。</p> \n <h2><a id=\"_259\"></a>五、总结</h2> \n <ul>\n  <li>本文主要介绍了关于一些技术成长有哪些知识点和内容可以扩充你的知识面，以及关于运维服务器的一些操作知识的入门学习。有了这样一个基础的操作领你进门，接下来就可以扩展的搜索很多其他的内容，来完善你要做的一些部署了。</li>\n  <li>另外本文没有介绍域名的注册和备案，这些内容还是很容易的，你只需要在云平台搜索域名或者百度搜索域名注册，都可以找到一个注册的入口。一般.cn的域名是比较便宜的，其他很多域名续费比较贵，另外像.org这样是不能备案的，所以不要选择太格鲁的域名。</li>\n  <li>像是这样的知识一定是动手操作起来才能学到东西，可能在这个过程中你会遇到各种各样的问题，没关系，这些问题都是可以搜到的。</li>\n  <li>另外可以加入我们的服务器学习群，添加我的微信：<code>fustack</code>，备注<code>服务器学习</code>，群里给大家录制一些操作小视频在群里。也有很多伙伴建好了自己的博客，互相交流学习！</li>\n </ul> \n <h2><a id=\"_266\"></a>六、系列推荐</h2> \n <ul>\n  <li><a href=\"https://bugstack.cn/itstack-code-life/2021/04/11/Cloudreve-%E8%87%AA%E5%BB%BA%E4%BA%91%E7%9B%98%E5%AE%9E%E8%B7%B5-%E6%88%91%E8%AF%B4%E4%BA%86%E6%B2%A1%E4%BA%BA%E8%83%BD%E9%99%90%E5%BE%97%E4%BA%86%E6%88%91%E7%9A%84%E5%AE%B9%E9%87%8F%E5%92%8C%E9%80%9F%E5%BA%A6.html\">Cloudreve 自建云盘搭建，我说了没人能限得了我的容量和速度！</a></li>\n  <li><a href=\"https://bugstack.cn/itstack-code-life/2020/03/28/GithubAndMyBlogAttacked.html\">20年3月27日，Github被攻击。我的GitPage博客也挂了，紧急修复之路，也教会你搭建 Jekyll 博客！</a></li>\n  <li><a href=\"https://bugstack.cn/itstack-demo-any/2019/11/23/%E5%B9%B6%E4%B8%8D%E6%83%B3%E5%90%B9%E7%89%9B%E7%9A%AE-%E4%BD%86-%E4%B8%BA%E4%BA%86%E6%8A%8AGithub%E5%8D%9A%E5%AE%A2%E7%B2%89%E4%B8%9D%E8%BD%AC%E7%A7%BB%E5%88%B0%E5%85%AC%E4%BC%97%E5%8F%B7-%E6%88%91%E5%B9%B2%E4%BA%86.html\">为了把Github博客粉丝转移到公众号，我做了公众号开发并部署到云服务</a></li>\n  <li><a href=\"https://bugstack.cn/itstack-code-life/2021/01/24/%E4%B8%80%E5%A4%A9%E5%BB%BA4%E4%B8%AA-%E5%B0%8F%E5%82%85%E5%93%A5%E6%95%99%E4%BD%A0%E6%90%AD%E5%8D%9A%E5%AE%A2.html\">hexo、docsify、jekyll、vuepress，博客搭建指导</a></li>\n  <li><a href=\"https://bugstack.cn/itstack-code-life/2020/08/25/13%E5%B9%B4%E6%AF%95%E4%B8%9A-%E7%94%A8%E4%B8%A4%E5%B9%B4%E6%97%B6%E9%97%B4%E4%BB%8E%E5%A4%96%E5%8C%85%E8%B5%B0%E8%BF%9B%E4%BA%92%E8%81%94%E7%BD%91%E5%A4%A7%E5%8E%82.html\">13年毕业，用两年时间从外包走进互联网大厂</a></li>\n </ul> \n</div>',
        1, 1, 1, 18299, 11350, 1585, 12617, 814, 1,'Docker,Java基础',
        '2022-11-10 15:19:07','2022-12-05 11:12:20',225,0);
INSERT INTO `t_question`
VALUES (10000034, 3123000034,
        '我是标题10000034',
        '<div id=\"content_views\" class=\"htmledit_views\"> \n <p style=\"margin-left:0cm;\"><strong><span style=\"color:#ff0000;\">项目编号：BS-XX-060</span></strong></p> \n <p style=\"margin-left:0cm;\">运行环境：</p> \n <p style=\"margin-left:0cm;\">开发工具：IDEA&nbsp; / ECLIPSE</p> \n <p style=\"margin-left:0cm;\">数据库：MYSQL5.7</p> \n <p style=\"margin-left:0cm;\">JAVA: JDK1.8</p> \n <p style=\"margin-left:0cm;\">应用服务器：TOMCAT8.5.31</p> \n <p style=\"margin-left:0cm;\">开发技术：Javaweb+JDBC</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">本系统基于Javaweb开发实现一套餐饮管理系统，可以实现用户在线定桌、点菜并进行支付等功能，系统管理员可以在系统后台对所有的菜品、餐桌、订单等进行相关信息的管理和维护工作。本系统功能完整，界面简洁大方，适合做毕业设计或课程设计使用。</p> \n <p style=\"margin-left:0;\">系统分为两个角色，一个是前端注册用户角色：主要实现在线订餐台、点菜、支付等操作；另一个是后台管理用户角色：主要是对系统的各种信息进行在线管理和维护。</p> \n <p style=\"margin-left:0;\">下面展示一下系统的具体功能：</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"1200\" src=\"https://img-blog.csdnimg.cn/20210615102653123.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">餐桌信息查看</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"771\" src=\"https://img-blog.csdnimg.cn/20210615102726807.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">餐桌预定</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"665\" src=\"https://img-blog.csdnimg.cn/20210615102726812.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"437\" src=\"https://img-blog.csdnimg.cn/20210615102726735.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">菜品信息查看</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"753\" src=\"https://img-blog.csdnimg.cn/20210615102741939.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">点菜</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"624\" src=\"https://img-blog.csdnimg.cn/20210615102741910.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"736\" src=\"https://img-blog.csdnimg.cn/20210615102756711.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">个人中心</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"591\" src=\"https://img-blog.csdnimg.cn/20210615102756693.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">个人资料修改</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"771\" src=\"https://img-blog.csdnimg.cn/20210615102756700.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">个人点菜查询</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"439\" src=\"https://img-blog.csdnimg.cn/2021061510280944.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">个人订单查询</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"469\" src=\"https://img-blog.csdnimg.cn/2021061510280945.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">餐桌预定查询</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"410\" src=\"https://img-blog.csdnimg.cn/2021061510280951.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">后台管理用户登陆</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"611\" src=\"https://img-blog.csdnimg.cn/2021061510280996.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"727\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"607\" src=\"https://img-blog.csdnimg.cn/2021061510282496.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">系统用户管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"562\" src=\"https://img-blog.csdnimg.cn/20210615102824100.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">美食资讯</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"781\" src=\"https://img-blog.csdnimg.cn/20210615102824145.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">用户管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"756\" src=\"https://img-blog.csdnimg.cn/20210615102851366.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">餐桌管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"767\" src=\"https://img-blog.csdnimg.cn/20210615102851367.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">菜品管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"783\" src=\"https://img-blog.csdnimg.cn/20210615102851426.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">点菜查询</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"777\" src=\"https://img-blog.csdnimg.cn/20210615102851398.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">订单管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"730\" src=\"https://img-blog.csdnimg.cn/20210615102851437.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">餐桌预定管理</p> \n <p style=\"margin-left:0;\"><img alt=\"\" height=\"743\" src=\"https://img-blog.csdnimg.cn/20210615102851441.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3doaXJsd2luZDUyNg==,size_16,color_FFFFFF,t_70\" width=\"1200\"></p> \n <p style=\"margin-left:0;\">&nbsp;</p> \n <p style=\"margin-left:0;\">以上是展示的基于Javaweb实现的饭店管理系统的部分功能，本系统功能 完整，界面简洁大方，是一款难得的优质系统，如果你的选题是关于餐饮管理方面的，这个系统比较适合使用。</p> \n</div>',
        1, 1, 1, 19989, 9379, 17615, 5868, 19862, 2,'SpringBoot,Java基础',
        '2022-11-10 15:19:07','2022-12-05 11:12:20',109,0);
INSERT INTO `t_question`
VALUES (10000035, 3123000035,
        '我是标题10000035',
        '我是内容10000035',
        1, 1, 1, 17561, 6617, 9869, 16022, 6029, 3,'Golang',
        '2022-11-10 15:19:07','2022-12-05 11:12:20',12,0);
INSERT INTO `t_question`
VALUES (10000036, 3123000036,
        '我是标题10000036',
        '<div id=\"content_views\" class=\"markdown_views prism-atom-one-dark\"> \n <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"display: none;\"> \n  <path stroke-linecap=\"round\" d=\"M5,0 0,2.5 5,5z\" id=\"raphael-marker-block\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></path> \n </svg> \n <p></p> \n <div class=\"toc\"> \n  <h3>Vite的使用</h3> \n  <ul>\n   <li>\n    <ul>\n     <li><a href=\"#esMoudle_1\">浏览器支持esMoudle的弊端</a></li>\n     <li><a href=\"#Vite_8\">Vite的安装和基本使用</a></li>\n     <li><a href=\"#ViteCSSLesspostcss_20\">Vite对CSS、Less、postcss的支持</a></li>\n     <li><a href=\"#Vitets_41\">Vite对ts的支持</a></li>\n     <li><a href=\"#Vite_47\">Vite服务器原理</a></li>\n     <li><a href=\"#ViteVue_51\">Vite对Vue的支持和预打包处理</a></li>\n     <li><a href=\"#Vite_65\">Vite的打包和预览过程</a></li>\n     <li><a href=\"#ViteESBuild_75\">Vite依赖ESBuild的学习</a></li>\n     <li><a href=\"#Vite_87\">Vite的脚手架使用</a></li>\n    </ul> </li>\n  </ul> \n </div> \n <p></p> \n <h2><a id=\"esMoudle_1\"></a>浏览器支持esMoudle的弊端</h2> \n <ul>\n  <li><code>script标签</code>需要加入<code>type=\"module\"</code></li>\n  <li>而且我们引入第三方包，我们只用了其中的一点点代码，但是浏览器还是会请求其相关没有用到的js文件</li>\n  <li>当我们有使用TypeScript，less时，浏览器不能识别。</li>\n  <li>引入文件时，必须定位到确定的文件，且后缀不能省略。</li>\n </ul> \n <h2><a id=\"Vite_8\"></a>Vite的安装和基本使用</h2> \n <p><strong>安装</strong>：<code>npm install vite -D</code> 可全局安装也可局部安装，我演示时使用的是局部安装</p> \n <p><strong>使用</strong>： <code>npx vite</code></p> \n <p>我们得提供一个<code>index.html</code>的文件，不然无法请求到。</p> \n <p>此时我们引入其他包时可以不用具体到某个文件，引入js文件可以不写后缀。</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/e60934dd9a8e5f9947d0b72109a420fc.png\" alt=\"image-20210613161317408\"></p> \n <h2><a id=\"ViteCSSLesspostcss_20\"></a>Vite对CSS、Less、postcss的支持</h2> \n <ul>\n  <li> <p><strong>vite是自动支持css的</strong></p> <img src=\"https://gitee.com/coderfzb/typora-imges/raw/master/demo/image-20210613161729259.png\" alt=\"image-20210613161729259\"> </li>\n  <li> <p><strong>vite对less的支持</strong></p> \n   <ul>\n    <li>需安装less <code>npm install less -D</code> vite会自动调用的，我们只要安装。</li>\n    <li>然后将写的less文件依赖引用进页面就可以了，就行了</li>\n   </ul> </li>\n  <li> <p><strong>vite对postcss的支持</strong></p> \n   <ul>\n    <li>我们只需要安装postcss <code>npm install postcss -D</code></li>\n    <li>但是我们还是得安装其他插件或者预设，才能起效果。插件或者预设安装看前面文章</li>\n    <li>postcss配置文件和前面文章一样，vite一样会自动调用</li>\n   </ul> </li>\n </ul> \n <h2><a id=\"Vitets_41\"></a>Vite对ts的支持</h2> \n <p>默认是直接支持的，我们不需要安装什么，和配置什么</p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/a7908d06852097b517be624332457e1e.png\" alt=\"image-20210613170550279\"></p> \n <h2><a id=\"Vite_47\"></a>Vite服务器原理</h2> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/a1affce53bbfa100cef810b35b713ec0.png\" alt=\"image-20210613172019994\"></p> \n <h2><a id=\"ViteVue_51\"></a>Vite对Vue的支持和预打包处理</h2> \n <p><strong>安装</strong></p> \n <ul>\n  <li> <p><code>npm install vue@next</code></p> </li>\n  <li> <p>vite对vue3单文件组件的支持<code>npm install @vitejs/plugin-vue -D</code></p> </li>\n  <li> <p><code>npm install @vue/compiler-sfc -D</code></p> </li>\n </ul> \n <p><strong>配置 <code>vite.config.js</code></strong></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/6a10325dc35cb8afc9fc973a4945d1ef.png\" alt=\"image-20210613174516766\"></p> \n <h2><a id=\"Vite_65\"></a>Vite的打包和预览过程</h2> \n <p><strong>打包</strong><code>npx vite build</code></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/761b20cd530d4a7f6cc4fbdf9842821c.png\" alt=\"image-20210613174651206\"></p> \n <p><strong>预览</strong><code>npx vite preview</code></p> \n <p><img src=\"https://img-blog.csdnimg.cn/img_convert/e5e6e741e990d5d0c98cc0d9b8c860ad.png\" alt=\"image-20210613174802069\"></p> \n <h2><a id=\"ViteESBuild_75\"></a>Vite依赖ESBuild的学习</h2> \n <p><strong>优点</strong></p> \n <ul>\n  <li>支持TypeScript等语法</li>\n  <li>支持 ES6、CommonJS的模块化</li>\n  <li>打包构建速度快</li>\n  <li>支持javaScript的API语法</li>\n  <li>支持代码压缩</li>\n  <li>扩展插件</li>\n  <li>Vite可以进行 Tree Sharking</li>\n </ul> \n <h2><a id=\"Vite_87\"></a>Vite的脚手架使用</h2> \n <p><strong>同时安装vite和脚手架</strong><code>npm init @vitejs/app</code></p> \n <ul>\n  <li>分开安装</li>\n </ul> \n <p><strong>安装</strong>：<code>npm install @vitejs/create-app -g</code></p> \n <p><strong>使用</strong>：<code>create-app 项目名称</code></p> \n</div>',
        1, 1, 1, 19960, 19539, 8841, 19474, 16084, 4,'Python',
        '2022-11-10 15:19:07','2022-12-05 11:12:20',40,0);
INSERT INTO `t_question`
VALUES (10000037, 3123000037,
        '我是标题10000037',
        '我是内容10000037',
        1, 1, 1, 8900, 8386, 13973, 15656, 16722, 1,'前端',
        '2022-11-10 15:19:08','2022-12-05 11:12:20',246,0);
INSERT INTO `t_question`
VALUES (10000038, 3123000038,
        '我是标题10000038',
        '我是内容10000038',
        1, 1, 1, 6124, 10084, 17668, 9186, 12121, 2,'Vue,Vue,Redis',
        '2022-11-10 15:19:08','2022-12-05 11:12:20',111,0);
INSERT INTO `t_question`
VALUES (10000039, 3123000039,
        '我是标题10000039',
        '我是内容10000039',
        1, 1, 1, 3858, 10897, 998, 5869, 16971, 3,'ElasticSearch',
        '2022-11-10 15:19:08','2022-12-05 11:12:20',156,0);
INSERT INTO `t_question`
VALUES (10000040, 3123000040,
        '我是标题10000040',
        '我是内容10000040',
        1, 1, 1, 14121, 15626, 4210, 17288, 1119, 4,'Linux,SpringCloud,Golang',
        '2022-11-10 15:19:08','2022-12-05 11:12:20',261,0);
INSERT INTO `t_question`
VALUES (10000041, 3123000041,
        '我是标题10000041',
        '我是内容10000041',
        1, 1, 1, 15685, 4037, 15111, 12874, 8762, 1,'Docker,Python',
        '2022-11-10 15:19:08','2022-12-05 11:12:20',95,0);
INSERT INTO `t_question`
VALUES (10000042, 3123000042,
        '我是标题10000042',
        '我是内容10000042',
        1, 1, 1, 4514, 16040, 15972, 17591, 9346, 2,'SpringBoot,前端',
        '2022-11-10 15:19:09','2022-12-05 11:12:20',77,0);
INSERT INTO `t_question`
VALUES (10000043, 3123000043,
        '我是标题10000043',
        '我是内容10000043',
        1, 1, 1, 5271, 6709, 4096, 20058, 2976, 3,'kubernetes,Git',
        '2022-11-10 15:19:09','2022-12-05 11:12:20',177,0);
INSERT INTO `t_question`
VALUES (10000044, 3123000044,
        '我是标题10000044',
        '我是内容10000044',
        1, 1, 1, 15256, 3537, 17584, 20148, 9934, 4,'Vue,kubernetes,Kafka',
        '2022-11-10 15:19:09','2022-12-05 11:12:20',145,0);
INSERT INTO `t_question`
VALUES (10000045, 3123000045,
        '我是标题10000045',
        '我是内容10000045',
        1, 1, 1, 12722, 2385, 14657, 584, 7290, 1,'SpringMVC',
        '2022-11-10 15:19:09','2022-12-05 11:12:20',196,0);
INSERT INTO `t_question`
VALUES (10000046, 3123000046,
        '我是标题10000046',
        '我是内容10000046',
        1, 1, 1, 8629, 11663, 9328, 637, 14944, 2,'SpringCloud',
        '2022-11-10 15:19:10','2022-12-05 11:12:20',11,0);
INSERT INTO `t_question`
VALUES (10000047, 3123000047,
        '我是标题10000047',
        '我是内容10000047',
        1, 1, 1, 8536, 15433, 1436, 2839, 5865, 3,'Java基础',
        '2022-11-10 15:19:10','2022-12-05 11:12:20',246,0);
INSERT INTO `t_question`
VALUES (10000048, 3123000048,
        '我是标题10000048',
        '我是内容10000048',
        1, 1, 1, 15333, 3561, 4677, 19698, 13041, 4,'Redis',
        '2022-11-10 15:19:10','2022-12-05 11:12:20',158,0);
INSERT INTO `t_question`
VALUES (10000049, 3123000049,
        '我是标题10000049',
        '我是内容10000049',
        1, 1, 1, 13839, 17798, 10372, 13347, 12831, 1,'ElasticSearch,Golang,Golang',
        '2022-11-10 15:19:10','2022-12-05 11:12:20',171,0);
INSERT INTO `t_question`
VALUES (10000050, 3123000050,
        '我是标题10000050',
        '我是内容10000050',
        1, 1, 1, 5473, 2411, 20095, 8627, 17855, 2,'前端,SpringBoot,Git',
        '2022-11-10 15:19:10','2022-12-05 11:12:20',143,0);



/* 标签表 */
DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag`(
                        `id` bigint(20) NOT NULL COMMENT '主键',
                        `tag_name` VARCHAR(255) NOT NULL COMMENT '标签名称',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';

INSERT INTO `t_tag`
VALUES (10001, 'Java基础', '2022-11-09 17:21:50')
     , (10002, 'Spring', '2022-11-09 17:21:50')
     , (10003, 'SpringBoot', '2022-11-09 17:21:50')
     , (10004, 'SpringCloud', '2022-11-09 17:21:50')
     , (10005, 'Redis', '2022-11-09 17:21:50')
     , (10006, 'SpringMVC', '2022-11-09 17:21:50')
     , (10007, 'Mybatis', '2022-11-09 17:21:50')
     , (10008, 'Docker', '2022-11-09 17:21:50')
     , (10009, 'kubernetes', '2022-11-09 17:21:50')
     , (10010, 'Kafka', '2022-11-09 17:21:50')
     , (10011, 'Linux', '2022-11-09 17:21:50')
     , (10012, 'Vue', '2022-11-09 17:21:50')
     , (10013, 'Git', '2022-11-09 17:21:50')
     , (10014, 'ElasticSearch', '2022-11-09 17:21:50')
     , (10015, '前端', '2022-11-09 17:21:50')
     , (10016, 'Python', '2022-11-09 17:21:50')
     , (10017, 'Golang', '2022-11-09 17:21:50')
     , (10018, 'C++', '2022-11-09 17:21:50');


/* 面试题关联标签表 */

# DROP TABLE IF EXISTS `t_question_tag`;
#
# CREATE TABLE `t_question_tag`(
#     `id` bigint(20) NOT NULL COMMENT '主键',
#     `question_id` bigint(20) NULL COMMENT '面试题id',
#     `tag_id` bigint(20) NULL COMMENT '标签id',
#      INDEX `idx_qid` (`question_id`) USING BTREE
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试题关联标签表';
#
# INSERT INTO `t_question_tag`
# VALUES (20001, 10000044, 10001)
# ,(20002, 10000044, 10002)
# ,(20003, 10000044, 10005)
# ,(20004, 10000044, 10008)
# ,(20005, 10000044, 10011)
# ,(20006, 10000008, 10001)
# ,(20007, 10000008, 10007)
# ,(20008, 10000008, 10009)
# ,(20009, 10000041, 10003)
# ,(20010, 10000041, 10010);



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
