package com.rocky.boot.quartz.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/10
 */
@Slf4j
@Service
public class SampleUpdateService extends BaseResourceUpdateService {

    @Override
    public void updateData() {
        System.out.println("SampleUpdate");
    }
}
