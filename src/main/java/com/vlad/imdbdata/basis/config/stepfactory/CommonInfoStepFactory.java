package com.vlad.imdbdata.basis.config.stepfactory;

import com.vlad.imdbdata.basis.batch.CustomReaderFactory;
import com.vlad.imdbdata.basis.batch.processor.CommonMediaInfoProcessor;
import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommonInfoStepFactory implements StepFactory{

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CustomReaderFactory customReaderFactory;
    @Autowired
    private CommonMediaRepository repository;

    @Override
    public Step createStep() {
        return stepBuilderFactory.get("commonMediaInfoStep1")
                .<Map<String, String>, CommonMediaInfo>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    private ItemReader<Map<String, String>> reader() {
        return customReaderFactory.create(MediaType.MOVIE);
    }

    private ItemProcessor processor() {
        return new CommonMediaInfoProcessor();
    }

    private RepositoryItemWriter<CommonMediaInfo> writer() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            private final Logger LOGGER = LoggerFactory.getLogger(RepositoryItemWriter.class);
            @Override
            public void write(List items) throws Exception {
                LOGGER.info("Write entity do common repo");
                super.write(items);
            }
        };
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
}
