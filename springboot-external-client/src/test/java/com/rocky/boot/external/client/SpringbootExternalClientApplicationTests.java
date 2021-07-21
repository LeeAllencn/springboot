package com.rocky.boot.external.client;

import com.rocky.boot.external.client.sample.factory.SampleClientServiceFactory;
import com.rocky.boot.external.client.sample.model.response.Contributor;
import com.rocky.boot.external.client.sample.service.SampleClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootExternalClientApplicationTests {

    @Resource
    private SampleClientServiceFactory sampleClientServiceFactory;

    @Test
    void sampleClientApiTest() {
        String baseUrl = "https://api.github.com";
        String token = "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";
        SampleClientService sampleClientService = sampleClientServiceFactory.createSampleClientService(baseUrl, token);
        List<Contributor> contributors = sampleClientService.contributors("square", "retrofit");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }

    @Test
    void contextLoads() {
    }

}
