package com.vlad.imdbdata.basis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SeriesEpisodeInfoEntity extends MediaInfoEntity {

    private Integer seasonNum;
    private Integer episodeNum;

    @Column(length = 16)
    private String seriesId;

    public static SeriesEntityBuilder entityBuilder() {
        return new SeriesEntityBuilder();
    }

    public static class SeriesEntityBuilder extends EntityBuilder {
        //TODO там чет кроме entity еще есть обьект obj
        private SeriesEpisodeInfoEntity entity;
        public SeriesEntityBuilder() {
            entity = new SeriesEpisodeInfoEntity();
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
        public SeriesEpisodeInfoEntity build() {
            return entity;
        }
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
