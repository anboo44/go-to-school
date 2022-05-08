package com.uet.gts.report.batch;

import com.uet.gts.report.config.LaborServiceConfig;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ResetServiceInstanceBatch implements Tasklet {

    private LaborServiceConfig laborServiceConfig;

    public ResetServiceInstanceBatch(LaborServiceConfig laborServiceConfig) {
        this.laborServiceConfig = laborServiceConfig;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        laborServiceConfig.clearInstanceCache();
        System.out.println("====[ Done task ResetServiceInstance ]====");
        return RepeatStatus.FINISHED;
    }
}
