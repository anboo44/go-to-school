package com.uet.gts.report.config;

import com.uet.gts.report.batch.ResetServiceInstanceBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private LaborServiceConfig laborServiceConfig;

    /* Define job flow: step1 -> step2 -> ... -> stepN */
    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet((x, y) -> {
                    System.out.println("===[ Start Batch ]===");
                    return null;
                })
                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new ResetServiceInstanceBatch(laborServiceConfig)) // Config Task -> Step -> Job
                .build();
    }

    /* Want to use Reader, Processor, Writer. Need step.<String, String>chunk(n) */
    @Bean
    public Step stepThree(){
        return steps.get("stepThree")
                .tasklet((x, y) -> {
                    System.out.println("===[ End Batch ]===");
                    return null;
                })
                .build();
    }

    /* Add step(above) to specific job*/
    @Bean(name="ResetServiceInstanceJob")
    public Job setInstanceJob() {
        return jobs.get("ResetServiceInstanceJob")
                .start(stepOne())
                .next(stepTwo())
                .next(stepThree())
                .build();
    }
}
