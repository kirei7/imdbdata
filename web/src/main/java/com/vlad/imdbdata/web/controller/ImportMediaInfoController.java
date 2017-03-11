package com.vlad.imdbdata.web.controller;

import com.vlad.imdbdata.basis.repos.MediaInfoRepository;
import com.vlad.imdbdata.basis.service.MediaInfoService;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImportMediaInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportMediaInfoController.class);

    @Autowired
    private MediaInfoService service;

    @RequestMapping(method = RequestMethod.GET)
    public void importData(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "mediaType") MediaType mediaType,
            @RequestParam(name = "year", required = false) Integer year
    ) {
        service.importMedia(title, year, mediaType);
        LOGGER.debug(Integer.toString(repository.findAll().size()));
    }

    //test
    @Autowired
    private MediaInfoRepository repository;
    /*@PostConstruct
    public void test() {
        service.importMedia("hannibal", null, MediaType.MOVIE);
        //LOGGER.debug(repository.findByImdbId("tt0212985").get(0).getMediaInfo().get("Title"));
    }*/
}
