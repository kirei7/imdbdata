package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.batch.processor.util.DataMapper;
import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;

public class CommonMediaInfoProcessor implements ItemProcessor<Map<String, String>, CommonMediaInfo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMediaInfoProcessor.class);

    @Override
    public CommonMediaInfo process(Map<String, String> map) throws Exception {
        CommonMediaInfo entity = DataMapper.mapToCommonEntity(map);
        LOGGER.info("processed mapToEntity, converted to entity: " + entity.getImdbId());
        return entity;
    }

}
