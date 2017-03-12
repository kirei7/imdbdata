package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaInfoRepository extends JpaRepository<MediaInfoEntity, String> {
    List<MediaInfoEntity> findByImdbId(String imdbId);
}
