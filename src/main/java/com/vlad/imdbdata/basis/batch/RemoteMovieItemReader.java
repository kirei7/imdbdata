package com.vlad.imdbdata.basis.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class RemoteMovieItemReader implements ItemReader<Map<String, String>> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RemoteMovieItemReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;
    private Map<String, String> data;
    private int index = 0;

    public RemoteMovieItemReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    //here all connections and data fetching occurs
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        Map<String, JobParameter> parameters = stepExecution
                .getJobExecution()
                .getJobParameters()
                .getParameters();
        String url = makeUrl(apiUrl, parameters);
        data = fetchDataFromRemote(url);
        /*
        since we can obtain short plot and full plot only separately
        we have to execute second query
        */
        Map<String, String> additionalData = fetchDataFromRemote(
                url + "&plot=full&tomatoes=true"
        );
        DataMapper.mapAdditionalData(data,additionalData);
        index = 0;
    }

    @Override
    public Map<String, String> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index != 0) return null;
        index++;
        LOGGER.info("read() returns: " + data.toString());
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
    private Map<String, String> fetchDataFromRemote(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url,
                String.class
        );
        return DataMapper.mapFromJson(response.getBody());
    }
    private static class DataMapper {
        static Map<String, String> mapFromJson(String source) {
            Map<String, String> map = null;
            try {
                map = new ObjectMapper().readValue(source, HashMap.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
            return map;
        }
        //adds additional data to 'data' map
        static void mapAdditionalData(
                Map<String, String> data,
                Map<String, String> additionalData
        ) {
            data.put(
                    "plotFull",
                    additionalData.get("Plot")
            );
            additionalData.remove("Plot");
            data.putAll(additionalData);
        }
    }
}
