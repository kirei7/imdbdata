package com.vlad.imdbdata.basis.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(BatchConfig.class)
@EnableJpaRepositories("com.vlad.imdbdata.basis.repo")
@EntityScan("com.vlad.imdbdata.basis.entity")
@ComponentScan("com.vlad.imdbdata.basis")
public class DomainConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
