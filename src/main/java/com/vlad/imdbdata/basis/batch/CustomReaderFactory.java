package com.vlad.imdbdata.basis.batch;

import com.vlad.imdbdata.basis.batch.reader.MediaInfoReader;
import com.vlad.imdbdata.basis.batch.reader.SeriesReader;
import com.vlad.imdbdata.basis.service.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomReaderFactory {
    private final String API_URL;

    private Environment env;
    private RestTemplate restTemplate;

    @Autowired
    private CustomReaderFactory(Environment env) {
        API_URL = env.getProperty("remote.api.url");
        restTemplate = new RestTemplate();
    }
    public MediaInfoReader create(MediaType type) {
        switch (type) {
        case SERIES:
            return new SeriesReader(API_URL, restTemplate);
        case MOVIE:
        default:
            return new MediaInfoReader(API_URL, restTemplate);
        }
    }
}
