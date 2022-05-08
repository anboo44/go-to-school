package com.uet.gts.report.config;

import com.uet.gts.report.batch.JobLauncher;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
public class QuartzBatchConfig {

    @Autowired
    private org.springframework.batch.core.launch.JobLauncher jobLauncher;

    @Autowired
    private JobLocator jobLocator;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    @Bean
    public JobDetail jobOneDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "ResetServiceInstanceJob");  // matching `job-name` in BatchConfiguration
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);

        return JobBuilder.newJob(JobLauncher.class)
                .withIdentity("jobOneDetail")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger jobOneTrigger() {
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.repeatMinutelyForever(5);

        return TriggerBuilder
                .newTrigger()
                .forJob(jobOneDetail())
                .withIdentity("jobOneTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(jobOneTrigger());
        scheduler.setQuartzProperties(quartzProperties());
        scheduler.setJobDetails(jobOneDetail());
        return scheduler;
    }

    @SneakyThrows
    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
