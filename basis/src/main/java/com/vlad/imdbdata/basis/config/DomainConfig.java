package com.vlad.imdbdata.basis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BatchConfig.class)
@ComponentScan("com.vlad.imdbdata.basis.service")
public class DomainConfig {
}
