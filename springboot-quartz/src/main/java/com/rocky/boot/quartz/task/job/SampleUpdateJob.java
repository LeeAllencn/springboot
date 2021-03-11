package com.rocky.boot.quartz.task.job;

import com.rocky.boot.quartz.config.QuartzConfig;
import com.rocky.boot.quartz.task.service.SampleUpdateService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author rocky
 * Description: job示例类
 * Created in 2021/3/9
 */
public class SampleUpdateJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContext springContext = QuartzConfig.QuartzSpringHelper.getSpringContext(jobExecutionContext);
        SampleUpdateService sampleUpdateService = springContext.getBean(SampleUpdateService.class);
        sampleUpdateService.update();
    }
}
