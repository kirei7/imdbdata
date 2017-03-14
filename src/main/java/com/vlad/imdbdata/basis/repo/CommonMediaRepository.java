package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonMediaRepository extends JpaRepository<CommonMediaInfo, String> {
    CommonMediaInfo findByImdbId(String imdbId);
    List<CommonMediaInfo> findByType(String type);
}
