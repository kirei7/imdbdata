package com.vlad.imdbdata.basis.batch;


/*
* This class is used to pass some values between batch steps, services etc
* Well, guess it smells but I couldn't find any other solution how to pass
* values between so different levels of a system
* */
public class ValueContainer {
    private Long totalSeasons;
    //last added media
    private String mediaTitle;
    //last added media type
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
