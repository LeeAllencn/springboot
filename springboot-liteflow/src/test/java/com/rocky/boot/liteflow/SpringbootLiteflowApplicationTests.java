package com.rocky.boot.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootLiteflowApplicationTests {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    void contextLoads() {
    }

    @Test
    public void testConfig(){
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
        System.out.println(response.isSuccess());
    }

}
