package com.vlad.imdbdata.basis.batch;

import org.springframework.stereotype.Component;

/*
* This class is used to pass some values through the batch steps etc
* */
public class ValueContainer {
    private Long totalSeasons;
    //last added media
    private String mediaTitle;
    //lst added media type
    private String mediaType;


    public void setTotalSeasons(Long totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
    public Long getTotalSeasons() {
        return totalSeasons;
    }
    public String getMediaTitle() {
        return mediaTitle;
    }
    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
