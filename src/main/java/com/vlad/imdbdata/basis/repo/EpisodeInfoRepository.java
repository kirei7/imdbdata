package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.SeriesEpisodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vlad on 13.03.17.
 */
public interface MediaInfoRepository extends JpaRepository<SeriesEpisodeInfo, String> {
    List<SeriesEpisodeInfo> findBySeriesId(String seriesNum);
}
