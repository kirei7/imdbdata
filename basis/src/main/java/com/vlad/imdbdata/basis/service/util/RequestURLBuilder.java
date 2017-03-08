package com.vlad.imdbdata.basis.service.util;

import com.vlad.imdbdata.basis.protocol.MediaInfoRequest;
import com.vlad.imdbdata.basis.protocol.MediaInfoRequest.ContentType;

public abstract class RequestURLBuilder {

    private static final String BASE_URL = "http://www.omdbapi.com";

    public static final RequestURLBuilder getBuilder(ContentType type) {
        switch (type) {
        case MOVIE:
            return new MovieRequestBuilder();
        case SERIES:
            //return new SeriesRequestBuilder();
            return new MovieRequestBuilder();
        default:
            throw new UnsupportedOperationException(
                    "Current type of media isn't supported: " + type.name()
            );
        }
    }

    abstract String build(MediaInfoRequest request);
}
