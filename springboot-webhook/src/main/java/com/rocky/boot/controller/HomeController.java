package com.rocky.boot.controller;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 2017-12-08.
 */
@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/endpointA")
    public void handlerA() throws InterruptedException {
        logger.info("/endpointA");
        Thread.sleep(RandomUtils.nextLong(0, 100));
    }

    @RequestMapping("/endpointB")
    public void handlerB() throws InterruptedException {
        logger.info("/endpointB");
        Thread.sleep(RandomUtils.nextLong(0, 100));
    }
}
