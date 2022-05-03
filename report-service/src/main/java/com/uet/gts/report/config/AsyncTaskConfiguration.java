package com.uet.gts.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class AsyncTaskConfiguration {

    @Bean
    TaskExecutor executeTask() {
        return new SimpleAsyncTaskExecutor();
    }
}
