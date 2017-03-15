package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.batch.ValueContainer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
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

    @Bean
    public ValueContainer valueContainer() {
        return new ValueContainer();
    }
}
