package com.vlad.imdbdata.web.controller;

import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import com.vlad.imdbdata.basis.repo.CommonMediaRepository;
import com.vlad.imdbdata.basis.repo.EpisodeRepository;
import com.vlad.imdbdata.basis.service.MediaInfoService;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        LOGGER.info("Entities count after import: " + service.itemsCount());
    }

    @RequestMapping("/series")
    public List<EpisodeInfo> getSeriesEpisodesById(
            //@RequestParam(name = "seriesId") String seriesID
    ) {
        return seriesRepository.findAll();
    }
    @RequestMapping("/all")
    public List<CommonMediaInfo> getAll() {
        return mediaInfoRepository.findAll();
    }

    @Autowired
    private EpisodeRepository seriesRepository;
    @Autowired
    private CommonMediaRepository mediaInfoRepository;
}
