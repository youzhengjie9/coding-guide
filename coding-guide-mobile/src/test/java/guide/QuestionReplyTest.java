package guide;

import com.alibaba.fastjson2.JSON;
import com.coding.guide.mobile.CodingGuideMobileApplication;
import com.coding.guide.mobile.entity.QuestionReply;
import com.coding.guide.mobile.service.QuestionReplyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CodingGuideMobileApplication.class)
@Slf4j
public class QuestionReplyTest {

    private QuestionReplyService questionReplyService;

    @Autowired
    public void setQuestionReplyService(QuestionReplyService questionReplyService) {
        this.questionReplyService = questionReplyService;
    }

    //递归生成多级回复列表
    private void generateReplyList(QuestionReply rp0, List<QuestionReply> secondLevelQuestionReplyList, List<QuestionReply> finallyQuestionReplyList){
        finallyQuestionReplyList.add(rp0);
        //获取一级回复的id
        long rp0Id = rp0.getId();
        for (QuestionReply rp1 : secondLevelQuestionReplyList) {
//          如果rp1是该一级回复的二级回复
            if(rp1.getRepliedId() == rp0Id){

                generateReplyList(rp1,secondLevelQuestionReplyList,finallyQuestionReplyList);

            }
        }
    }

    @Test
    void test() {

        //一级面试题回复列表（replied_id=0）,一级回复
        List<QuestionReply> firstLevelQuestionReplyList = questionReplyService.lambdaQuery()
                .eq(QuestionReply::getCommentId, 5905371473566001L)
                .eq(QuestionReply::getRepliedId, 0)
                .orderByDesc(QuestionReply::getLikeCount)
                .orderByAsc(QuestionReply::getReplyTime)
                .list();
        //二级面试题回复列表（replied_id!=0），二级/多级回复
        List<QuestionReply> secondLevelQuestionReplyList = questionReplyService.lambdaQuery()
                .eq(QuestionReply::getCommentId, 5905371473566001L)
                .ne(QuestionReply::getRepliedId, 0)
                .orderByDesc(QuestionReply::getLikeCount)
                .orderByAsc(QuestionReply::getReplyTime)
                .list();

        //存放最终回复结果集合，返回给前端
        List<QuestionReply> finallyQuestionReplyList = new ArrayList<>();

        for (QuestionReply rp0 : firstLevelQuestionReplyList) {

            generateReplyList(rp0,secondLevelQuestionReplyList,finallyQuestionReplyList);

//            //添加一级回复
//            finallyQuestionReplyList.add(rp0);
//            //获取一级回复的id
//            long rp0Id = rp0.getId();
//
//            //遍历二级回复列表找到所有该一级回复的二级回复
//            for (QuestionReply rp1 : secondLevelQuestionReplyList) {
//                //如果rp1是该一级回复的二级回复
//                if(rp1.getRepliedId() == rp0Id){
//
//                    //添加二级回复
//                    finallyQuestionReplyList.add(rp1);
//
//                    long r1Id = rp1.getId();
//                    for (QuestionReply rp2 : secondLevelQuestionReplyList) {
//
//                        if(rp2.getRepliedId() == r1Id){
//
//                            finallyQuestionReplyList.add(rp2);
//
//                            long r2Id=rp2.getId();
//                            for (QuestionReply rp3 : secondLevelQuestionReplyList) {
//
//                                if(rp3.getRepliedId() == r2Id){
//
//                                    finallyQuestionReplyList.add(rp3);
//                                    long r3Id=rp3.getId();
//
//                                }
//
//                            }
//
//                        }
//
//                    }
//
//                }
//            }
        }

        System.out.println(JSON.toJSONString(finallyQuestionReplyList));
    }

}
