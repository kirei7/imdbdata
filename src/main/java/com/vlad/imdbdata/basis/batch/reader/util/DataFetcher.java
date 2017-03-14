package com.vlad.imdbdata.basis.batch.reader.util;

import com.vlad.imdbdata.basis.batch.reader.CommonMediaInfoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataFetcher {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, String> fetchData(String url) {
        /*
        since we can obtain short plot and full plot only separately
        we have to execute two queries
        */
        Map<String, String> result = DataMapper.mapFromJson(
                restTemplate.getForEntity(url,String.class).getBody(),
                HashMap.class
        );
        url += "&plot=full&tomatoes=true";
        //warning - mapAdditionalData() method modifies passed map
        DataMapper.mapAdditionalData(
                result,
                DataMapper.mapFromJson(
                        restTemplate.getForEntity(url,String.class).getBody(),
                        HashMap.class
                )
        );
        return result;
    }

}
