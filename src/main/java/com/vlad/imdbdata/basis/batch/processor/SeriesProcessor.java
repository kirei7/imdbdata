package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import com.vlad.imdbdata.basis.entity.SeriesEpisodeInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

/**
 * Created by vlad on 13.03.17.
 */
public class SeriesProcessor implements ItemProcessor<Map<String, String>, SeriesEpisodeInfoEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesProcessor.class);

    private CustomEntityMapper mapper;

    public SeriesProcessor() {
        mapper = new CustomEntityMapper();
    }

    @Override
    public SeriesEpisodeInfoEntity process(Map<String, String> map) throws Exception {
        SeriesEpisodeInfoEntity entity = mapper.mapToSeriesEntity(map);
        LOGGER.info("processed mapToEntity, converted to entity: " + entity.getImdbId());
        return entity;
    }
}
