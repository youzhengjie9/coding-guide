package com.coding.guide;

import com.coding.guide.entity.Tag;
import com.coding.guide.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest(classes = CodingGuideApplication.class)
public class GenerateTest {

    @Autowired
    private TagService tagService;

    /**
     * 生成面试题数据sql
     */
    @Test
    public void generateQuestionDataSQL(){

        List<Tag> tagList = tagService.query().list();

        final AtomicLong s1=new AtomicLong(10000001L);
        final AtomicLong s2=new AtomicLong(3123000001L);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {

            int circle=random.nextInt(3)+1;
            StringBuilder tagBuilder = new StringBuilder();
            for (int x1 = 0; x1 < circle; x1++) {
                int tgIndex = random.nextInt(17);
                String tagName = tagList.get(tgIndex).getTagName();
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

            long ss1 = s1.getAndIncrement();
            long ss2 = s2.getAndIncrement();
            String s="INSERT INTO `t_question`\n" +
                    "VALUES ("+ss1+", "+ss2+", \n" +
                    "        '我是标题"+ss1+"', \n" +
                    "        '我是内容"+ss1+"',\n" +
                    "        1, 1, 1, "+readCount+", "+likeCount+", "+collectCount+"," +
                    " "+commentCount+", "+meetCount+", "+difficulty+",'"+tagNameList+"',\n" +
                    "        '"+publishTime+"','2022-12-05 11:12:20',"+sort+",0);";

            System.out.println(s);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
