package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.batch.processor.util.DataMapper;
import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

public class EpisodeProcessor implements ItemProcessor<Map<String, String>, EpisodeInfo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMediaInfoProcessor.class);

    @Override
    public EpisodeInfo process(Map<String, String> map) throws Exception {
        EpisodeInfo entity = DataMapper.mapToEpisodeEntity(map);
        LOGGER.info("processed mapToEntity, converted to entity: " + entity.getImdbId());
        return entity;
    }
}
