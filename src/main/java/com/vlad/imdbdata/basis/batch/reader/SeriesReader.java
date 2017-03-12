package com.vlad.imdbdata.basis.batch.reader;

import org.springframework.web.client.RestTemplate;

public class SeriesReader extends RemoteItemReader {

    public SeriesReader(String apiUrl, RestTemplate restTemplate) {
        super(apiUrl, restTemplate);
    }

}