package com.vlad.imdbdata.basis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BatchConfig.class)
public class DomainConfig {
}
