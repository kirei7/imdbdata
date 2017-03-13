package com.vlad.imdbdata.basis.repo;

import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaInfoRepository extends JpaRepository<MediaInfoEntity, String> {
    MediaInfoEntity findByImdbId(String imdbId);
    List<MediaInfoEntity> findByType(String type);

}
