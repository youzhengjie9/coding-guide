package guide;

import com.coding.guide.mobile.CodingGuideMobileApplication;
import com.coding.guide.mobile.service.UserFollowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CodingGuideMobileApplication.class)
public class ServiceTest {

    private UserFollowService userFollowService;

    @Autowired
    public void setUserFollowService(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }

    @Test
    void test1(){

        userFollowService.isFollow(5700000000000001L,5700000000000003L);

        userFollowService.isFollow(5700000000000001L,5700000000000008L);

        userFollowService.isFollow(5700000000000009L,5700000000000003L);
    }


}
