package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.SeriesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vlad on 14.03.17.
 */
public interface SeriesRepository extends JpaRepository<SeriesInfo, String> {
}
