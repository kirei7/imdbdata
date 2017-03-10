package com.vlad.imdbdata.basis.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RemoteItemReader implements ItemReader<MediaInfoEntity> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteItemReader.class);

    private final String apiUrl;//"https://www.omdbapi.com/"
    private final RestTemplate restTemplate;
    private int nextItemIndex;
    private List<MediaInfoEntity> data;
    private String queryParams;

    public RemoteItemReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextItemIndex = 0;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }
    @Override
    public MediaInfoEntity read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (data == null) data = fetchDataFromRemote();
        LOGGER.debug("RAW: ", data.get(0));
        MediaInfoEntity mediaInfoEntity = null;
        if (nextItemIndex < data.size()) {
            /*mediaInfoEntity = EntityMapper.fromJson(
                    data.get(nextItemIndex)
            );*/
            nextItemIndex++;
        }
        return mediaInfoEntity;
    }

    private List<MediaInfoEntity> fetchDataFromRemote() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                apiUrl + queryParams,
                String.class
        );

        //TODO
        return null;
    }

    private static class EntityMapper {
        public static MediaInfoEntity fromJson(String source) {
            MediaInfoEntity result = null;
            try {
                HashMap<String,String> map
                        = new ObjectMapper().readValue(source, HashMap.class);
                result = new MediaInfoEntity()
                        .withImdbId(map.get("imdbID"))
                        .withMediaInfo(map);
                //do not forget to remove id from the map, since we
                //already have it in a field
                map.remove("imdbID");
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            } finally {
                return result;
            }
        }
    }
}
