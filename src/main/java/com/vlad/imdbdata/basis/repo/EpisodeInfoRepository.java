package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.SeriesEpisodeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vlad on 13.03.17.
 */
public interface EpisodeInfoRepository extends JpaRepository<SeriesEpisodeInfoEntity, String> {
    List<SeriesEpisodeInfoEntity> findBySeriesId(String seriesNum);
}
