package com.vlad.imdbdata.basis.batch;

import com.vlad.imdbdata.basis.batch.reader.CommonMediaInfoReader;
import com.vlad.imdbdata.basis.batch.reader.EpisodeReader;
import com.vlad.imdbdata.basis.batch.reader.util.DataFetcher;
import com.vlad.imdbdata.basis.config.MediaType;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CustomReaderFactory {
    private final String API_URL;

    @Autowired
    private DataFetcher dataFetcher;
    @Autowired
    private ValueContainer container;

    @Autowired
    private CustomReaderFactory(Environment env) {
        API_URL = env.getProperty("remote.api.url");
    }
    public ItemReader create(MediaType type) {
        switch (type) {
        case EPISODE:
            return new EpisodeReader(dataFetcher, API_URL, container);
        case MOVIE:
        case SERIES:
        default:
            return new CommonMediaInfoReader(dataFetcher, API_URL);
        }
    }
}
