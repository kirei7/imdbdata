package com.vlad.imdbdata.basis.batch.processor;

import org.springframework.stereotype.Component;

/*
* This class is used to pass some values through the batch steps etc
* */
@Component
public class ValueContainer {
    private Long totalSeasons;


    public void setTotalSeasons(Long totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
    public Long getTotalSeasons() {
        return totalSeasons;
    }
}
