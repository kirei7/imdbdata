package com.vlad.imdbdata.basis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EpisodeInfo extends CommonMediaInfo {

    private Integer seasonNum;
    private Integer episodeNum;

    @Column(length = 16)
    private String seriesId;

    public EpisodeInfo(){}
    //constructor which allows to quickly build EpisodeInfo from parent object
    public EpisodeInfo(CommonMediaInfo other) {
        super(other);
    }

    public EpisodeInfo withSeasonNum(Integer seasonNum) {
        this.setSeasonNum(seasonNum);
        return this;
    }
    public EpisodeInfo withEpisodeNum(Integer episodeNum) {
        this.setEpisodeNum(episodeNum);
        return this;
    }
    public EpisodeInfo withSeriesId(String seriesNum) {
        this.setSeriesId(seriesNum);
        return this;
    }

    public void setSeasonNum(Integer seasonNum) {
        this.seasonNum = seasonNum;
    }
    public void setEpisodeNum(Integer episodeNum) {
        this.episodeNum = episodeNum;
    }
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getSeasonNum() {
        return seasonNum;
    }
    public Integer getEpisodeNum() {
        return episodeNum;
    }
    public String getSeriesId() {
        return seriesId;
    }
}
