package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.batch.MovieItemProcessor;
import com.vlad.imdbdata.basis.batch.RemoteMovieItemReader;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import com.vlad.imdbdata.basis.repos.MediaInfoRepository;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
    private Environment env;
    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @Bean
    public Map<MediaType, Job> jobs() {
        Map<MediaType, Job> jobs = new HashMap<>();
        jobs.put(MediaType.MOVIE, movieJob());
        //TODO add seriesJob
        return jobs;
    }

    @Bean
    public Job movieJob() {
        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .flow(movieStep1())
                .end()
                .build();
    }

    @Bean
    public Step movieStep1() {
        return stepBuilderFactory.get("movieStep1")
                .<Map<String, String>, MediaInfoEntity> chunk(1)
                .reader(movieReader(env))
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<Map<String, String>> movieReader(Environment env) {
        return new RemoteMovieItemReader(
                env.getProperty("remote.api.url"),
                restTemplate()
        );
    }
    @Bean
    public MovieItemProcessor processor() {
        return new MovieItemProcessor();
    }
    @Bean
    public RepositoryItemWriter<MediaInfoEntity> writer() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            @Override
            public void write(List items) throws Exception{
                    LOGGER.debug("WRITE!");
                    super.write(items);
            }
        };
        writer.setRepository(mediaInfoRepository);
        writer.setMethodName("save");
        return writer;
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
