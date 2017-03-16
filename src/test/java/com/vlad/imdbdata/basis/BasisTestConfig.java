package com.vlad.imdbdata.basis;

import com.vlad.imdbdata.CommonConfig;
import com.vlad.imdbdata.basis.batch.ValueContainer;
import com.vlad.imdbdata.basis.config.MediaType;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
import com.vlad.imdbdata.basis.repo.EpisodeRepository;
import com.vlad.imdbdata.basis.repo.SeriesRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(CommonConfig.class)
@ComponentScan("com.vlad.imdbdata.basis.service")
public class BasisTestConfig {

}
