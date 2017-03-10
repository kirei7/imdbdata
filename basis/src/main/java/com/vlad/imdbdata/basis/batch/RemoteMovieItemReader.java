package com.vlad.imdbdata.basis.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Scope("step")
public class RemoteMovieItemReader implements ItemReader<MediaInfoEntity> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteMovieItemReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;
    private MediaInfoEntity data;
    private int index = 0;

    public RemoteMovieItemReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    private MediaInfoEntity fetchDataFromRemote(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url,
                String.class
        );
        return EntityMapper.mapFromJson(response.getBody());
    }

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        Map<String, JobParameter> parameters = stepExecution
                .getJobExecution()
                .getJobParameters()
                .getParameters();
        String url = makeUrl(apiUrl, parameters);
        data = fetchDataFromRemote(url);
    }

    @Override
    public MediaInfoEntity read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index != 0) return null;
        index++;
        return data;
    }

    private String makeUrl(String apiUrl, Map<String, JobParameter> parameters) {
        StringBuilder sb = new StringBuilder();
        sb.append(apiUrl)
                .append("?")
                .append("t=")
                .append(parameters.get("title").getValue());
        JobParameter yp = parameters.get("year");

        if (yp != null) {
            Long year = (Long) yp.getValue();
            sb
                .append("&")
                .append("y=")
                .append(year.toString());
        }
        return sb.toString();
    }
    private static class EntityMapper {
        public static MediaInfoEntity mapFromJson(String source) {
            MediaInfoEntity result = null;
            try {
                HashMap<String,String> map
                        = new ObjectMapper().readValue(source, HashMap.class);
                LOGGER.debug(map.toString());
                result = new MediaInfoEntity()
                        .withImdbId(map.get("imdbID"))
                        .withMediaInfo(map);
                //do not forget to remove id from the map, since we
                //already have it in a field
                map.remove("imdbID");
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
            return result;
        }
    }
}
