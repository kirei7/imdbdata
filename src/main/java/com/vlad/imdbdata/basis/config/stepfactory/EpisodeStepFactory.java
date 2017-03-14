package com.vlad.imdbdata.basis.config.stepfactory;

import com.vlad.imdbdata.basis.batch.CustomReaderFactory;
import com.vlad.imdbdata.basis.batch.processor.EpisodeProcessor;
import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import com.vlad.imdbdata.basis.repo.EpisodeRepository;
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
public class EpisodeStepFactory implements StepFactory {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CustomReaderFactory customReaderFactory;
    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public Step createStep() {
        return stepBuilderFactory.get("episodeInfoStep1")
                .<Map<String, String>, EpisodeInfo>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    
    public ItemReader<Map<String, String>> reader() {
        return customReaderFactory.create(MediaType.EPISODE);
    }

    public ItemProcessor processor() {return new EpisodeProcessor();}

    public RepositoryItemWriter<EpisodeInfo> writer() {
        RepositoryItemWriter writer = new RepositoryItemWriter() {
            private final Logger logger
                    = LoggerFactory.getLogger("Episode repository writer");
            @Override
            public void write(List items) throws Exception {
                logger.info("Write entity do episodes repo");
                super.write(items);
            }
        };
        writer.setRepository(episodeRepository);
        writer.setMethodName("save");
        return writer;
    }
}
