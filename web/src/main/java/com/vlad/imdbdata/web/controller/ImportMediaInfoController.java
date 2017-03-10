package com.vlad.imdbdata.web.controller;

import com.vlad.imdbdata.basis.service.MediaInfoService;
import com.vlad.imdbdata.basis.service.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImportMediaInfoController {

    @Autowired
    @Qualifier(value = "MediaInfoServices")
    private Map<MediaType, MediaInfoService> services;

    @RequestMapping(method = RequestMethod.GET)
    public void importData(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "mediaType") MediaType mediaType,
            @RequestParam(name = "year", required = false) Integer year
    ) {
        services.get(mediaType).importMedia(title, year);
    }

}
