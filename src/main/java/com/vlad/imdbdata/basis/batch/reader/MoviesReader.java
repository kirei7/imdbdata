package com.vlad.imdbdata.basis.batch.reader;

import org.springframework.web.client.RestTemplate;

public class MoviesReader extends RemoteItemReader {

    public MoviesReader(String apiUrl, RestTemplate restTemplate) {
        super(apiUrl, restTemplate);
    }

}
