package com.vlad.imdbdata.basis.batch.reader;

import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.repo.EpisodeInfoRepository;
import com.vlad.imdbdata.basis.repo.MediaInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeriesReader extends MediaInfoReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesReader.class);

    MediaInfoRepository repo;
    String parentId;

    public SeriesReader(String apiUrl, RestTemplate restTemplate, MediaInfoRepository repo) {
        super(apiUrl, restTemplate);
        this.repo = repo;
    }

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        data = null;
        index = 0;
        if (data == null) {
            Map<String, JobParameter> parameters = stepExecution
                    .getJobExecution()
                    .getJobParameters()
                    .getParameters();
            parentId = (String) parameters.get("entityId").getValue();
            downloadData(
                    makeUrl(apiUrl, parameters)
            );
        }
    }

    @Override
    protected void downloadData(String url) {
        LOGGER.info("Start downloading...");
        data = new ArrayList<>(1);
        CommonMediaInfo entity = repo.findByImdbId(parentId);
        LOGGER.debug();
        LOGGER.info("Downloaded series info...");
        LOGGER.debug(result.toString());
        Integer numOfSeasons = entity.;
        for (int i = 1; i <= numOfSeasons; i++) {
            LOGGER.info("Getting season: " + i);
            String seasonParam = "&season=" + i;
            Map<String, String> seasonInfo = fetchSingleRow(
                    url + seasonParam
            );
            Object seriesArray =  seasonInfo.get("Episodes");
            List list = (List) seriesArray;
            int numOfEpisodes = list.size();
            for (int j = 1; j <= numOfEpisodes; j++) {
                LOGGER.debug("Getting episode: " + j);
                Map<String, String> episode = fetchSingleRow(
                        url + seasonParam + "&episode=" + j
                );
                data.add(episode);
            }
        }
        LOGGER.info("Downloaded info for " + numOfSeasons + "seasons");
    }
}