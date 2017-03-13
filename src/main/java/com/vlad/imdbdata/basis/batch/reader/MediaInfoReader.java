package com.vlad.imdbdata.basis.batch.reader;

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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaInfoReader implements ItemReader<Map<String, String>> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MediaInfoReader.class);

    protected final String apiUrl;
    protected final RestTemplate restTemplate;
    protected List<Map<String, String>> data;
    protected int index;

    public MediaInfoReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    //here all connections and data fetching occurs
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        data = null;
        index = 0;
        if (data == null) {
            Map<String, JobParameter> parameters = stepExecution
                    .getJobExecution()
                    .getJobParameters()
                    .getParameters();
            downloadData(
                    makeUrl(apiUrl, parameters)
            );
        }
    }

    @Override
    public Map<String, String> read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index >= data.size()) {
            return null;
        }
        Map<String, String> result = data.get(index);
        LOGGER.info("read() returns: " + result.toString());
        index++;
        return result;
    }

    protected String makeUrl(String apiUrl, Map<String, JobParameter> parameters) {
        StringBuilder sb = new StringBuilder();
        sb.append(apiUrl)
                .append("?")
                .append("t=")
                .append(parameters.get("title").getValue())
                .append("&type=")
                .append(parameters.get("type").getValue());

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
    protected Map<String, String> fetchSingleRow(String url) {
        /*
        since we can obtain short plot and full plot only separately
        we have to execute second query
        */
        Map<String, String> result = DataMapper.mapFromJson(
                restTemplate.getForEntity(url,String.class).getBody(),
                HashMap.class
        );
        url += "&plot=full&tomatoes=true";
        DataMapper.mapAdditionalData(
                result,
                DataMapper.mapFromJson(
                        restTemplate.getForEntity(url,String.class).getBody(),
                        HashMap.class
                )
        );
        return result;
    }
    protected static class DataMapper {
        /*static Map<String, String> mapFromJson(String source) {
            Map<String, String> mapToEntity = null;
            try {
                mapToEntity = new ObjectMapper().readValue(source, HashMap.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
            return mapToEntity;
        }*/
        static <T> T mapFromJson(String source, Class<T> resultType) {
            T result = null;
            try {
                result = new ObjectMapper().readValue(source, resultType);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
            return result;
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
    protected void downloadData(String url) {
        //the purpose of this cycle is to fetch a
        //single row of data on each iteration
        data = new ArrayList<>(1);
        data.add(fetchSingleRow(url));
    }
}
