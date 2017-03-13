package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

public class MovieProcessor implements ItemProcessor<Map<String, String>, MediaInfoEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieProcessor.class);

    private CustomEntityMapper mapper;

    public MovieProcessor() {
        mapper = new CustomEntityMapper();
    }

    @Override
    public MediaInfoEntity process(Map<String, String> map) throws Exception {
        MediaInfoEntity entity = mapper.mapToCommonEntity(map);
        LOGGER.info("processed mapToEntity, converted to entity: " + entity.getImdbId());
        return entity;
    }
}
