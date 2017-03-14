package com.vlad.imdbdata.basis.entity;

import javax.persistence.Entity;

@Entity
public class SeriesInfo extends CommonMediaInfo {

    private Integer totalSeasons;

    public SeriesInfo() {}
    public SeriesInfo(CommonMediaInfo other) {super(other);}

    public SeriesInfo withTotalSeasons(Integer totalSeasons) {
        this.setTotalSeasons(totalSeasons);
        return this;
    }

    //Getter/Setter
    public Integer getTotalSeasons() {
        return totalSeasons;
    }
    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
}
