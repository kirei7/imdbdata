package com.vlad.imdbdata.web;

import com.vlad.imdbdata.CommonConfig;
import com.vlad.imdbdata.basis.BasisTestConfig;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
import com.vlad.imdbdata.basis.repo.EpisodeRepository;
import com.vlad.imdbdata.basis.service.MediaInfoService;
import com.vlad.imdbdata.web.config.WebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

@Configuration
@Import({CommonConfig.class, WebConfig.class})
@ComponentScan("com.vlad.imdbdata.web.controller")
public class WebTestConfig {
    @Bean
    public EpisodeRepository episodeRepository() {
        return mock(EpisodeRepository.class);
    }
    @Bean
    public CommonMediaRepository commonMediaRepository() {
        return mock(CommonMediaRepository.class);
    }
    @Bean
    public MediaInfoService mediaInfoService() {
        return mock(MediaInfoService.class);
    }
}
