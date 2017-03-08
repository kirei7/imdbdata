package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.config.batch.BatchConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BatchConfig.class)
public class DomainConfig {
}
