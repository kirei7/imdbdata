package com.vlad.imdbdata.basis.batch.reader;

import com.vlad.imdbdata.basis.batch.processor.ValueContainer;
import com.vlad.imdbdata.basis.batch.reader.util.DataFetcher;
import com.vlad.imdbdata.basis.batch.reader.util.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EpisodeReader implements ItemReader<Map<String, String>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EpisodeReader.class);

    private DataFetcher dataFetcher;
    private String apiUrl;
    private ValueContainer container;
    private List<Map<String, String>> dataList;
    private int index = 0;

    public EpisodeReader(DataFetcher dataFetcher, String apiUrl, ValueContainer container) {
        this.dataFetcher = dataFetcher;
        this.apiUrl = apiUrl;
        this.container = container;
    }

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        Long totalSeasons = container.getTotalSeasons();
        dataList = new ArrayList<>(totalSeasons.intValue());
        Map<String, JobParameter> parameters = stepExecution
                .getJobExecution()
                .getJobParameters()
                .getParameters();
        String url = apiUrl + DataMapper.makeUrl(parameters);
        for (int i = 1; i <= totalSeasons; i++) {
            LOGGER.info("Getting season: " + i);
            String seasonParam = "&season=" + i;
            Map<String, String> seasonInfo = dataFetcher.fetchData(url + seasonParam);
            Object seriesArray =  seasonInfo.get("Episodes");
            List list = (List) seriesArray;
            int totalEpisodes = list.size();
            for (int j = 1; j <= totalEpisodes; j++) {
                LOGGER.debug("Getting episode: " + j);
                Map<String, String> episode = dataFetcher.fetchData(
                        url + seasonParam + "&episode=" + j
                );
                dataList.add(episode);
            }
        }
    }
    @Override
    public Map<String, String> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index >= dataList.size()) return null;
        return dataList.get(index++);
    }

    @AfterStep
    public void cleanUp() {
        index = 0;
        dataList = null;
    }

}