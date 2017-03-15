package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.batch.ValueContainer;
import com.vlad.imdbdata.basis.batch.processor.util.DataMapper;
import com.vlad.imdbdata.basis.entity.SeriesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;


public class SeriesProcessor implements ItemProcessor<Map<String, String>, SeriesInfo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesProcessor.class);

    private Long totalSeasons;
    private ValueContainer container;

    public SeriesProcessor(ValueContainer container) {
        this.container = container;
    }

    @Override
    public SeriesInfo process(Map<String, String> map) throws Exception {
        totalSeasons = null;
        SeriesInfo entity = DataMapper.mapToSeriesEntity(map);
        LOGGER.info("processed mapToEntity, converted to entity: " + entity.getImdbId());
        if (entity.getType().toLowerCase().equals("series")) {
            totalSeasons = new Long(entity.getTotalSeasons());
            container.setTotalSeasons(totalSeasons);
            container.setMediaTitle(entity.getTitle());
            container.setMediaType("Series");
        }
        return entity;
    }

    //putting totalSeasons (seasons number) to job parameters
    //so that episode reader could know how many seasons fetch
    /*@AfterStep
    public void afterProcess(StepExecution stepExecution) {
        if (totalSeasons == null) return;
        Map<String, JobParameter> parameters = stepExecution
                .getJobExecution()
                .getJobParameters()
                .getParameters();
        LOGGER.debug("Putting series totalSeasons to parameters: " + totalSeasons.toString());
        parameters.put("totalSeasons", new JobParameter(totalSeasons));
    }*/
}
