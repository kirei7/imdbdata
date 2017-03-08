package com.vlad.imdbdata.basis.service;

import com.vlad.imdbdata.basis.protocol.MediaInfoRequest;
import com.vlad.imdbdata.basis.service.util.RequestURLBuilder;

public class RequestHandler {

    public void handle(MediaInfoRequest request) {
        RequestURLBuilder builder = RequestURLBuilder.getBuilder(request.getType());
        //String
    }
}
