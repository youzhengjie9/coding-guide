

## 项目需求分析文档⭐

- **项目名**：coding-guide

- **项目概述:**

    - coding-guide是一个专注于面试刷题、使用简单的移动端网站，采用当今十分流行的前后端分离架构开发的项目，拥有一整套完整的前台（移动端）和后台管理系统（PC端），项目的后端采用的是SpringBoot、前端采用的是Vue2。

- **项目背景，为何开发这个项目？**

    - 主要是在这个人口红利的时代，互联网行业（特别是开发岗）的面试难度越来越高（越来越卷），并且经常会问面试题（八股文），一个人的技术好不好其实只是其次，八股文必须要会。我作为一个大三学生很快就要面临就业，所以打算开发一个专注于面试的网站，市面上有很多面试题都是直接放在PDF、Word文档中的，而且里面的知识点不够全面，如果需要更加全面的知识点的话，则需要收集大量的面试题的PDF文档去一个个看，很难做到针对某一个知识点逐个击破，把时间都浪费到收集面试题上去了。而这个网站不仅会收集大量的面试题知识点并且总结归类、而且用户还能在众多题库中随机抽题（考试）检验自己的学习成果，最重要的是，市面上有很多面试的网站都是基于电脑端，而我们这个是基于移动端，在手机上就可以学习（复习）知识。

- **项目模块和对应的技术选型**：（下面分别是两套系统）

    - 子模块名：（主模块：coding-guide）

        - coding-guide-admin（移动端的后端模块）
        - coding-guide-mobile（后台管理系统的后端模块）
        - coding-guide-common（公共模块）
        - coding-guide-vue-admin（后台管理系统模块）
        - coding-guide-vue-mobile（前台移动端模块）
        - coding-guide-doc（文档资料模块）

    - 前台系统：（前台展示-移动端）

        - 数据库：MySQL8.0、Redis6、ElasticSearch7.8
        - 后端技术：SpringBoot2、SpringSecurity、Mybatis-Plus、JWT、Canal、minio（或者七牛云OSS）、Druid、fastjson2、hutool、Swagger3、knife4j、lombok
        - 前端技术：Vue2、Vue-Router、Vuex、Vue-Cli5、Axios、Vant（移动端ui组件）、Echarts
        - 测试技术：Jmeter
        - 运维技术：Docker、Kubernetes(后期才上)、Jenkins（看情况）
        - 开发环境：JDK17、IDEA2022.2.4、vscode

        - **功能列表（需求）：**

            - **系统的基础功能：**

                - [X] 帐号密码登录
                - [ ] 第三方登录（GitHub、Gitee）
                - [X] 用户注册
                - [X] 退出登录

            - **面试题功能：**

                - [X] 面试题列表展示
                - [X] 面试题推荐
                - [ ] 精选题目
                - [X] 面试题标签列表
                - [X] 面试题详情内容
                - [X] 面试题发布（内容、标签、是否可以评论等）
                - [X] 面试题模糊搜索
                - [X] 搜索历史记录
                - [ ] 热门搜索
                - [ ] 举报面试题
                - [X] 面试题点赞
                - [X] 面试题收藏
                - [ ] 面试题遇见
                - [X] 面试题访问量展示
                - [ ] 面试题分享
                - [X] 面试题评论/回复（多级评论）
                - [X] 首页轮播图展示

            - **用户功能：**

                - [X] 关注用户
                - [X] 评论点赞
                - [ ] 积分功能
                - [ ] 排行榜（积分）
                - [ ] 个人中心（修改密码）
                - [X] 我的作品
                - [X] 我的私密
                - [X] 我的收藏
                - [X] 我的点赞
                - [X] 浏览记录
                - [ ] 用户关注列表
                - [ ] 用户粉丝列表
                - [ ] 被点赞通知
                - [ ] 被关注通知
                - [ ] 被评论通知
                - [ ] 用户在线实时聊天
                
            - 在线考试功能：

                - [ ] 一键从面试题题库中抽题组成试卷
                - [ ] 自定义试卷（考试时长、得分等）
                - [ ] 试卷发布者打分功能


- 后台系统：（后台管理系统-PC端）

    - 数据库：MySQL8.0、Redis6、ElasticSearch7.8
    - 后端技术：SpringBoot2、SpringSecurity、Mybatis-Plus、JWT、Canal、minio（或者七牛云OSS）、Easy-Excel、Druid、fastjson2、hutool、Swagger3、knife4j、lombok
    - 前端技术：Vue2、Vue-Router、Vuex、Vue-Cli5、Axios、Element-Ui（电脑端ui组件）、Echarts
        - 测试技术：Jmeter
    - 运维技术：Docker、Kubernetes(后期才上)、Jenkins（看情况）
        - 开发环境：JDK17、IDEA2022.2.4、vscode
        
- 未来展望⭐
    - 1：使用分布式、微服务架构对整个后台项目进行全部重构、对整个后台项目进行模块拆分等。
    - 2：使用最新的前端技术对前台项目进行重构。
    - 3：前台项目适配电脑端。
    - 4：对代码进行优化，使网站更加稳定、性能更好。
    - ......

## 性能调优和部分问题解决⭐

> 1：调优（/mobile/question/selectQuestionDetail/{id}）接口过程
* jmeter(500线程、循环10次)
    * 1：直接查询数据库------->（qps：723.7）
    * 2：先查redis(没有事先将缓存加进redis，说白了就是没有缓存预热)、再查数据库-------->（qps：780）
    * 3：先查redis(先将缓存加进redis，进行缓存预热)、再查数据库-------->（qps：1826）
    * 4：先查caffeine本地缓存，然后查redis(先将缓存加进redis，进行缓存预热)、再查数据库-------->（qps：4251）

## 图片展示

[//]: # (![01.png]&#40;https://gitee.com/youzhengjie/coding-guide/raw/main/images/01.png&#41;)






