package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.batch.CustomReaderFactory;
import com.vlad.imdbdata.basis.batch.processor.MovieProcessor;
import com.vlad.imdbdata.basis.batch.processor.SeriesProcessor;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import com.vlad.imdbdata.basis.entity.SeriesEpisodeInfoEntity;
import com.vlad.imdbdata.basis.repo.EpisodeInfoRepository;
import com.vlad.imdbdata.basis.repo.MediaInfoRepository;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
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
    private MediaInfoRepository mediaInfoRepository;
    @Autowired
    private EpisodeInfoRepository episodeInfoRepository;
    @Autowired
    private CustomReaderFactory customReaderFactory;

    @Bean
    public Map<MediaType, Job> jobs() {
        Map<MediaType, Job> jobs = new HashMap<>();
        jobs.put(MediaType.MOVIE, movieJob());
        jobs.put(MediaType.SERIES, seriesJob());
        return jobs;
    }

    @Bean
    public Job movieJob() {
        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .flow(movieStep())
                .end()
                .build();
    }

    @Bean
    public Step movieStep() {
        return stepBuilderFactory.get("movie step 1")
                .<Map<String, String>, MediaInfoEntity>chunk(1)
                .reader(movieReader())
                .processor(movieProcessor())
                .writer(commonWriter())
                .build();
    }

    @Bean
    public ItemReader<Map<String, String>> movieReader() {
        return customReaderFactory.create(MediaType.MOVIE);
    }

    @Bean
    public ItemProcessor movieProcessor() {
        return new MovieProcessor();
    }

    @Bean
    public RepositoryItemWriter<MediaInfoEntity> commonWriter() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            @Override
            public void write(List items) throws Exception {
                LOGGER.info("Write entity do common repo");
                super.write(items);
            }
        };
        writer.setRepository(mediaInfoRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job seriesJob() {
        return jobBuilderFactory.get("seriesJob")
                .incrementer(new RunIdIncrementer())
                .flow(seriesStep())
                .end()
                .build();
    }

    @Bean
    public Step seriesStep() {
        return stepBuilderFactory.get("series step 1")
                .<Map<String, String>, SeriesEpisodeInfoEntity>chunk(1)
                .reader(seriesReader())
                .processor(seriesProcessor())
                .writer(seriesWriter())
                .build();
    }

    @Bean
    public ItemReader<Map<String, String>> seriesReader() {
        return customReaderFactory.create(MediaType.SERIES);
    }

    @Bean
    public ItemProcessor seriesProcessor() {
        return new SeriesProcessor();
    }

    @Bean
    public RepositoryItemWriter<SeriesEpisodeInfoEntity> seriesWriter() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            @Override
            public void write(List items) throws Exception {
                LOGGER.info("Write entity do episodes repo");
                super.write(items);
            }
        };
        writer.setRepository(episodeInfoRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
