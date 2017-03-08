package com.vlad.imdbdata.service;

import com.vlad.imdbdata.protocol.MediaInfoRequest;
import com.vlad.imdbdata.service.util.RequestURLBuilder;

public class RequestHandler {

    public void handle(MediaInfoRequest request) {
        RequestURLBuilder builder = RequestURLBuilder.getBuilder(request.getType());
        String
    }
}
