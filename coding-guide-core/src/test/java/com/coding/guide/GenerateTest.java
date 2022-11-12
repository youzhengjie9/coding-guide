package com.coding.guide;

import com.coding.guide.entity.Tag;
import com.coding.guide.service.TagService;
import com.coding.guide.utils.SnowId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

@SpringBootTest(classes = CodingGuideApplication.class)
public class GenerateTest {

    @Autowired
    private TagService tagService;

    /**
     * 生成面试题数据sql
     */
    @Test
    public void generateQuestionDataSQL(){

        List<Tag> tagList = tagService.lambdaQuery()
                .select(Tag::getId,Tag::getTagName)
                .list();


        //t_question表的sql建造器
        final StringBuilder questionSqlBuilder=new StringBuilder("INSERT INTO `t_question` VALUES \n");
        //t_question_tag表的sql建造器
        final StringBuilder questionTagSqlBuilder=new StringBuilder("INSERT INTO `t_question_tag` VALUES \n");
        final AtomicLong s1=new AtomicLong(10000001L);
        final AtomicLong s2=new AtomicLong(3123000001L);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {

            int circle=random.nextInt(3)+1;
            StringBuilder tagBuilder = new StringBuilder();
            //存储每一组标签id的集合
            CopyOnWriteArrayList<Long> tagIdList = new CopyOnWriteArrayList<>();
            for (int x1 = 0; x1 < circle; x1++) {
                int tgIndex = random.nextInt(17);
                Tag tag = tagList.get(tgIndex);
                tagIdList.add(tag.getId());
                String tagName = tag.getTagName();
                tagBuilder.append(tagName).append(",");
            }
            String s3 = tagBuilder.toString();

            String tagNameList = s3.substring(0, s3.length() - 1);

            int readCount=random.nextInt(20000)+500;
            int likeCount=random.nextInt(20000)+500;
            int collectCount=random.nextInt(20000)+500;
            int commentCount=random.nextInt(20000)+500;
            int meetCount=random.nextInt(20000)+500;
            int difficulty=(i%4)+1;
            LocalDateTime localDateTime = LocalDateTime.now();
            String publishTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int sort=random.nextInt(300)+1;

            long questionId = s1.getAndIncrement();
            long questionUserId = s2.getAndIncrement();
            //生成t_question的sql

            String str1="("+questionId+","+questionUserId+","+"'我是标题"+questionId+"',"+"'我是内容"+questionId+"',"+"1,1,1,"+readCount+"" +
                    ","+likeCount+","+collectCount+","+commentCount+","+meetCount+","+difficulty+",'"+tagNameList+"',"+"'"+publishTime+"','"+"2022-12-05 11:12:20"+"',"+sort+",0),\n";
            questionSqlBuilder.append(str1);


            //生成t_question_tag的sql
            tagIdList.forEach(tagid -> {
                String str2="("+ SnowId.nextId()+","+questionId+","+tagid+"),\n";
                questionTagSqlBuilder.append(str2);
            });

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        //面试题sql
        String str1 = questionSqlBuilder.toString();
        String quesionSql = str1.substring(0, str1.length() - 2);
        quesionSql+=";";

        System.out.println(quesionSql);

        //面试题-标签的sql
        String str2 = questionTagSqlBuilder.toString();
        String quesionTagSql = str2.substring(0, str2.length() - 2);
        quesionTagSql+=";";

        System.out.println(quesionTagSql);


    }


}
