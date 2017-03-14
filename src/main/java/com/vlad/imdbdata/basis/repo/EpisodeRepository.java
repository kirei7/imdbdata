package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vlad on 13.03.17.
 */
public interface EpisodeRepository extends JpaRepository<EpisodeInfo, String> {
    List<EpisodeInfo> findBySeriesId(String seriesNum);
}
