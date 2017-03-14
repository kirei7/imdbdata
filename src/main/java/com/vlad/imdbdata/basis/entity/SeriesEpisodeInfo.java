package com.vlad.imdbdata.basis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SeriesEpisodeInfo extends CommonMediaInfo {

    private Integer seasonNum;
    private Integer episodeNum;

    @Column(length = 16)
    private String seriesId;

    public SeriesEpisodeInfo(){}

    public static SeriesEntityBuilder entityBuilder() {
        return new SeriesEntityBuilder();
    }

    public static class SeriesEntityBuilder extends EntityBuilder {
        //TODO там чет кроме entity еще есть обьект obj
        protected SeriesEpisodeInfo entity;
        public SeriesEntityBuilder() {
            entity = new SeriesEpisodeInfo();
        }
        public SeriesEntityBuilder withSeasonNum(Integer seasonNum) {
            entity.setSeasonNum(seasonNum);
            return this;
        }
        public SeriesEntityBuilder withEpisodeNum(Integer episodeNum) {
            entity.setEpisodeNum(episodeNum);
            return this;
        }
        public SeriesEntityBuilder withSeriesId(String seriesNum) {
            entity.setSeriesId(seriesNum);
            return this;
        }
        public SeriesEpisodeInfo build() {
            return new SeriesEpisodeInfo(super.build());
        }
    }

    public SeriesEpisodeInfo(CommonMediaInfo other) {
        super(other);
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
