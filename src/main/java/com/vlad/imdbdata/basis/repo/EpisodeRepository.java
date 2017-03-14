package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<EpisodeInfo, String> {
    List<EpisodeInfo> findBySeriesId(String seriesNum);
}
