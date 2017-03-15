package com.vlad.imdbdata.basis.config.stepfactory;

import com.vlad.imdbdata.basis.batch.CustomReaderFactory;
import com.vlad.imdbdata.basis.batch.processor.SeriesProcessor;
import com.vlad.imdbdata.basis.batch.processor.ValueContainer;
import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.entity.SeriesInfo;
import com.vlad.imdbdata.basis.repo.SeriesRepository;
import com.vlad.imdbdata.basis.config.MediaType;
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
public class SeriesStepFactory implements StepFactory {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CustomReaderFactory customReaderFactory;
    @Autowired
    private SeriesRepository repository;
    @Autowired
    private ValueContainer container;

    @Override
    public Step createStep() {
        return stepBuilderFactory.get("seriesInfoStep1")
                .<Map<String, String>, CommonMediaInfo>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    private ItemReader<Map<String, String>> reader() {
        return customReaderFactory.create(MediaType.SERIES);
    }

    private ItemProcessor processor() {
        return new SeriesProcessor(container);
    }

    private RepositoryItemWriter<SeriesInfo> writer() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            private final Logger logger
                    = LoggerFactory.getLogger("SeriesInfo repository writer");
            @Override
            public void write(List items) throws Exception {
                logger.info("Write entity to series repo");
                super.write(items);
            }
        };
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
}
