package com.rocky.boot.quartz.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.listeners.BroadcastSchedulerListener;
import org.quartz.listeners.JobChainingJobListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/9
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.quartz.job")
public class QuartzConfig implements InitializingBean, DisposableBean, ApplicationContextAware {

    public static final String SPRING_CONTEXT = "springContext";
    public static final String DEFAULT_GROUP = "SpringBootGroup";

    private Boolean enable = true;

    @Getter
    @Setter
    private Map<String, String> triggerTime = new LinkedHashMap<>();

    private List<String> lazy = new LinkedList<>();

    private ApplicationContext applicationContext;

    @Resource
    private Scheduler scheduler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 不启动Quartz，直接返回
        if (!enable) {
            return;
        }
        this.applicationContext = applicationContext;

        if (scheduler != null && applicationContext != null) {
            try {
                // 将springContext传递进去
                scheduler.getContext().put(SPRING_CONTEXT, applicationContext);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!enable) {
            log.info("不启动quartz！");
            return;
        }

        // 防止两次加载
        if (scheduler == null) {
            return;
        }

        // 监听触发
        scheduler.getListenerManager().addTriggerListener(new MonitorTriggerListener(scheduler));
        // 新增监听器
        scheduler.getListenerManager().addSchedulerListener(new BroadcastSchedulerListener());
        scheduler.getListenerManager().addJobListener(new JobChainingJobListener("Springboot-Job-Listener"));
        // 把springContext传递进去
        scheduler.getContext().put(SPRING_CONTEXT, applicationContext);

        scheduler.clear();
        scheduler.start();
        log.info("------ Quartz Started Scheduler ------");

        loadJobs();
    }

    private void loadJobs() throws SchedulerException {
        String packageName = QuartzConfig.class.getPackage().getName();
        packageName = packageName.substring(0, packageName.lastIndexOf("."));
        Set<Class<?>> supClasses = ClassUtil.scanPackageBySuper(packageName, Job.class);
        log.info("开始加载用户自定义Job，扫描路径：{} ...{}", packageName, supClasses.size());
        for (Class<?> clazz : supClasses) {
            String groupName = DEFAULT_GROUP;
            String className = StrUtil.lowerFirst(clazz.getSimpleName());
            // 获取配置文件
            String triggerTime = this.triggerTime.get(className);
            log.info("加载job:groupName={} className={} triggerTime={}", groupName, className, triggerTime);

            if (StrUtil.isBlank(triggerTime)) {
                log.warn("triggerTime为空，无法加载，请设置！groupName={} className={}", groupName, className);
                continue;
            }

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerTime);
            CronTrigger cronTrigger = newTrigger().withIdentity(className + "Trigger", groupName).withSchedule(scheduleBuilder).build();

            log.info("------ Scheduling Job ------");

            JobDetail jobDetail = newJob((Class<? extends Job>) clazz).withIdentity(className, groupName).build();

            scheduler.scheduleJob(jobDetail, cronTrigger);

            log.info("{} will run at:{}", jobDetail.getKey(), cronTrigger.getNextFireTime());
            if (lazy.contains(className)) {
                scheduler.pauseJob(jobDetail.getKey());
                log.info("{}已被暂停，用户自行触发执行", jobDetail.getKey());
            }
        }
        log.info("Job 加载完毕");
    }

    @Override
    public void destroy() throws Exception {
        if (!enable) {
            return;
        }
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown(true);
            log.info("------ Quartz Scheduler Shutdown Completed ------");
        }
    }

    public static class QuartzSpringHelper {

        public static ApplicationContext getSpringContext(JobExecutionContext context) {
            ApplicationContext springContext = null;
            try {
                springContext = (ApplicationContext) context.getScheduler().getContext().get(SPRING_CONTEXT);
            } catch (SchedulerException e) {
                log.error("Job 无法获取 SpringContext!", e);
            }
            return springContext;
        }
    }

    class MonitorTriggerListener implements TriggerListener {

        private Scheduler scheduler;

        private Map<String, String> runningJobMap = new ConcurrentHashMap<>();

        public MonitorTriggerListener(Scheduler scheduler) {
            this.scheduler = scheduler;
        }

        private void printMsg(String type, String trigger) {
            try {
                int currentlyExecutingJobsSize = this.scheduler.getCurrentlyExecutingJobs().size();
                int numberOfJobsExecuted = this.scheduler.getMetaData().getNumberOfJobsExecuted();
                int threadPoolSize = this.scheduler.getMetaData().getThreadPoolSize();
                log.info("numberOfJobsExecuted:{} 当前任务:{} {} 线程池状态-currentlyExecutingJobsSize/threadPoolSize:{}/{} \n {}", numberOfJobsExecuted, trigger, type, currentlyExecutingJobsSize, threadPoolSize, JSONUtil.toJsonStr(runningJobMap));
            } catch (SchedulerException e) {
                log.error("获取scheduler的job数量信息异常", e);
            }
        }

        @Override
        public String getName() {
            return "QuartzMonitor";
        }

        @Override
        public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
            runningJobMap.putIfAbsent(trigger.getKey().getName(), DateUtil.now());
            log.info("触发Quartz Trigger Key : {} StartTime : {} JobDetail : {}", trigger.getKey(), trigger.getStartTime(), jobExecutionContext.getJobDetail());
            printMsg("triggerFired", trigger.getKey().getName());
        }

        @Override
        public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
            return false;
        }

        @Override
        public void triggerMisfired(Trigger trigger) {
            log.info("JobKey:{} StartTime:{} 错过执行了 misfireInstruction:{} priority:{}", trigger.getJobKey(), trigger.getStartTime(), trigger.getMisfireInstruction(), trigger.getPriority());
            printMsg("triggerMisfired", trigger.getKey().getName());
        }

        @Override
        public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
            runningJobMap.remove(trigger.getKey().getName());
            log.info("Trigger Key:{} StartTime:{} JobDetail:{} Completed:{} 执行完成！", trigger.getJobKey(), trigger.getStartTime(), jobExecutionContext.getJobDetail(), completedExecutionInstruction);
            printMsg("triggerComplete", trigger.getKey().getName());
        }
    }
}
