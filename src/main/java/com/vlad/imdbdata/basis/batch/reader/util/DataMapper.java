package com.vlad.imdbdata.basis.batch.reader.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;

import java.io.IOException;
import java.util.Map;

public class DataMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataMapper.class);

    public static <T> T mapFromJson(String source, Class<T> resultType) {
        T result = null;
        try {
            result = new ObjectMapper().readValue(source, resultType);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    //adds additional data to 'data' map
    public static void mapAdditionalData(
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

    public static String makeUrl(Map<String, JobParameter> parameters) {
        StringBuilder sb = new StringBuilder();

        sb.append("?")
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
}