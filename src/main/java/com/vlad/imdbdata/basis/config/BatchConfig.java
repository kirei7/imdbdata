package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.config.stepfactory.CommonInfoStepFactory;
import com.vlad.imdbdata.basis.config.stepfactory.EpisodeStepFactory;
import com.vlad.imdbdata.basis.config.stepfactory.SeriesStepFactory;
import com.vlad.imdbdata.basis.repo.EpisodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:batch.properties")
public class BatchConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private EpisodeRepository episodeInfoRepository;
    @Autowired
    private CommonInfoStepFactory commonInfoStepFactory;
    @Autowired
    private SeriesStepFactory seriesStepFactory;
    @Autowired
    private EpisodeStepFactory episodeStepFactory;

    @Bean
    public Map<MediaType, Job> jobs() {
        Map<MediaType, Job> jobs = new HashMap<>();
        jobs.put(MediaType.MOVIE, commonInfoJob());
        jobs.put(MediaType.SERIES, seriesJob());
        return jobs;
    }

    @Bean
    public Job commonInfoJob() {
        return jobBuilderFactory.get("commonInfoJob")
                .incrementer(new RunIdIncrementer())
                .flow(commonInfoStep())
                .end()
                .build();
    }
    @Bean
    public Step commonInfoStep() {
        return commonInfoStepFactory.createStep();
    }

    @Bean
    public Job seriesJob() {
        return jobBuilderFactory.get("seriesJob")
                .incrementer(new RunIdIncrementer())
                .flow(seriesStep())
                .next(episodesStep())
                .end()
                .build();
    }
    @Bean
    public Step seriesStep() { return seriesStepFactory.createStep(); }
    @Bean
    public Step episodesStep() { return episodeStepFactory.createStep(); }
}
