package com.vlad.imdbdata.basis.repos;

import com.vlad.imdbdata.basis.entity.MediaInfoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaInfoRepository extends CrudRepository<MediaInfoEntity, String> {
    List<MediaInfoEntity> findByImdbId(String imdbId);
}
