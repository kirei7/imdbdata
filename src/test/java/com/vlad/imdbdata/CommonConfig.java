package com.vlad.imdbdata;

import com.vlad.imdbdata.basis.batch.ValueContainer;
import com.vlad.imdbdata.basis.config.MediaType;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@Configuration
public class CommonConfig {

    @Bean
    public JobLauncher jobLauncher() {
        return mock(JobLauncher.class);
    }
    @Bean
    public Map<MediaType, Job> jobs() {
        Job first = mock(Job.class);
        Job second = mock(Job.class);
        Map<MediaType, Job> jobMap = new HashMap() {
            {
                put(MediaType.MOVIE, first);
                put(MediaType.SERIES, second);
            }
        };
        return jobMap;
    }
    @Bean
    public CommonMediaRepository commonMediaRepository() {
        return mock(CommonMediaRepository.class);
    }
    @Bean
    public ValueContainer container() {
        return mock(ValueContainer.class);
    }
}
