package com.vlad.imdbdata.basis.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeriesReader extends MediaInfoReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesReader.class);

    public SeriesReader(String apiUrl, RestTemplate restTemplate) {
        super(apiUrl, restTemplate);
        LOGGER.debug("Created " + this.getClass().getSimpleName());
    }

    @Override
    protected void downloadData(String url) {
        LOGGER.info("Start downloading...");
        data = new ArrayList<>(1);
        Map<String, String> result = fetchSingleRow(url);
        data.add(result);
        LOGGER.info("Downloaded series info...");
        LOGGER.debug(result.toString());
        Integer numOfSeasons = Integer.parseInt(result.get("totalSeasons"));
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